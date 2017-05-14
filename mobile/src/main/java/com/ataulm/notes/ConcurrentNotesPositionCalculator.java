package com.ataulm.notes;

import android.support.annotation.Px;

import com.ataulm.Optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ataulm.notes.Mark.NATURAL;
import static com.ataulm.notes.Mark.NOTE;
import static com.ataulm.notes.Mark.SHARP;

class ConcurrentNotesPositionCalculator {

    private static final int NUMBER_OF_COLUMNS_ROUGH_X_POSITIONS_NOTES = 2;
    private static final int NOTE_PRIMARY = 0;
    private static final int NOTE_SECONDARY = 1;

    private static final Comparator<Note> HIGH_TO_LOW_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            if (o1.midi() == o2.midi()) {
                return 0;
            }
            if (o1.midi() > o2.midi()) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    private static final int SPACES_BETWEEN_NON_OVERLAPPING_ACCIDENTALS = 7;

    private final MusicalSymbolSizes symbolSizes;
    private final MiddleCeeOffsetCalculator offsetCalculator;

    ConcurrentNotesPositionCalculator(MusicalSymbolSizes symbolSizes, MiddleCeeOffsetCalculator offsetCalculator) {
        this.symbolSizes = symbolSizes;
        this.offsetCalculator = offsetCalculator;
    }

    ConcurrentNotesMarks calculatePositions(Key key, ConcurrentNotes concurrentNotes) {
        List<Note> sortedNotes = new ArrayList<>(concurrentNotes.notes());
        Collections.sort(sortedNotes, HIGH_TO_LOW_COMPARATOR);


        List<PositionedMark> positionedMarks = positionedMarks(key, sortedNotes);
        Optional<Stem> stem = stem(positionedMarks);

        return ConcurrentNotesMarks.create(positionedMarks, stem);
    }

    private Optional<Stem> stem(List<PositionedMark> positionedMarks) {
        Position highestNote = null;
        Position lowestNote = null;
        for (PositionedMark positionedMark : positionedMarks) {
            if (positionedMark.mark() == NOTE) {
                if (highestNote == null || positionedMark.center().y() < highestNote.y()) {
                    highestNote = positionedMark.center();
                }

                if (lowestNote == null || positionedMark.center().y() > lowestNote.y()) {
                    lowestNote = positionedMark.center();
                }
            }
        }
        if (highestNote.equals(lowestNote)) {
            return Optional.absent();
        } else {
            int highestX = highestNote.x();
            int lowestX = lowestNote.x();
            int desiredX;
            if (highestX == lowestX) {
                desiredX = (int) (highestX + (width(NOTE) * 0.5)) - 1;
            } else {
                desiredX = (int) ((lowestX + highestX) * 0.5);
            }
            return Optional.of(
                    Stem.create(
                            Position.create(desiredX, highestNote.y()),
                            Position.create(desiredX, lowestNote.y())
                    )
            );
        }
    }

    @Px
    int topToMiddleCee(Key key, ConcurrentNotes concurrentNotes) {
        List<Note> sortedNotes = new ArrayList<>(concurrentNotes.notes());
        Collections.sort(sortedNotes, HIGH_TO_LOW_COMPARATOR);

        int yOffset = calculateYOffset(key, sortedNotes.get(0));

        int topNoteOffset = offsetCalculator.calculateOffset(key, sortedNotes.get(0));
        return deltaGivenOffset(topNoteOffset) - symbolSizes.note.height() - yOffset;
    }

    private List<PositionedMark> positionedMarks(Key key, List<Note> sortedNotes) {
        ArrayList<PositionedMark> positionedMarks = new ArrayList<>();

        // determine which 'column' marks are placed in, based on notes which are too close vertically and whether they have accidentals
        Mark[][] marksWithRoughXPosition = calculateRoughXPositions(key, sortedNotes);
        Mark[][] trimmedMarksWithRoughXPosition = Marks2DArrayTrimmer.trim(marksWithRoughXPosition);

        // calculate the width of each 'column' (== the width of its largest mark)
        Map<Integer, Integer> columnWidth = new HashMap<>();
        for (int row = 0; row < trimmedMarksWithRoughXPosition.length; row++) {
            for (int column = 0; column < trimmedMarksWithRoughXPosition[row].length; column++) {
                Mark mark = trimmedMarksWithRoughXPosition[row][column];
                if (mark == null) {
                    continue;
                }
                int width = width(mark);
                if (!columnWidth.containsKey(column) || columnWidth.get(column) < width) {
                    columnWidth.put(column, width);
                }
            }
        }

        // calculate the X position for the start of each 'column'
        Map<Integer, Integer> columnLeft = new HashMap<>();
        int cumulativeLeft = 0;
        for (int i = 0; i < columnWidth.size(); i++) {
            if (i != 0) {
                cumulativeLeft += columnWidth.get(i - 1);
            }
            columnLeft.put(i, cumulativeLeft);
        }

        // calculate the center horizontal for each 'column' to use as the X position for each mark
        for (int row = 0; row < trimmedMarksWithRoughXPosition.length; row++) {
            for (int column = 0; column < trimmedMarksWithRoughXPosition[row].length; column++) {
                Mark mark = trimmedMarksWithRoughXPosition[row][column];
                if (mark == null) {
                    continue;
                }
                int left = columnLeft.get(column);
                int width = (int) (columnWidth.get(column) * 0.5);
                int x = left + width;
                positionedMarks.add(PositionedMark.create(mark, x, 0));
            }
        }

        ArrayList<Integer> yPositions = new ArrayList<>();
        Note firstNote = sortedNotes.get(0);
        int highestMiddleCeeOffset = offsetCalculator.calculateOffset(key, firstNote);
        for (int i = 0; i < sortedNotes.size(); i++) {
            Note note = sortedNotes.get(i);
            int y = calculateYPosition(key, note, highestMiddleCeeOffset);
            yPositions.add(y);
            Optional<Accidental> accidental = ChromaticScales.accidental(key, note);
            if (accidental.isPresent()) {
                yPositions.add(y);
            }
        }

        int yOffset = calculateYOffset(key, firstNote);
        for (int i = 0; i < positionedMarks.size(); i++) {
            PositionedMark positionedMark = positionedMarks.get(i);
            positionedMarks.set(i, positionedMark.y(yPositions.get(i) + yOffset));
        }

        return positionedMarks;
    }

    private int calculateYOffset(Key key, Note firstNote) {
        Optional<Accidental> accidental = ChromaticScales.accidental(key, firstNote);
        int yOffset = 0;
        if (accidental.isPresent()) {
            Mark mark = accidental.get() == Accidental.NATURAL ? NATURAL : SHARP;
            yOffset = (int) ((height(mark) - height(NOTE)) * 0.5);
        }
        return yOffset;
    }

    private Mark[][] calculateRoughXPositions(Key key, List<Note> sortedNotes) {
        if (sortedNotes.isEmpty()) {
            return new Mark[0][0];
        }

        Mark[][] roughXPositionsForNotes = calculateRoughXPositionsForNotes(key, sortedNotes);
        Mark[][] roughXPositionsForAccidentals = calculateRoughXPositionsForAccidentals(roughXPositionsForNotes, key, sortedNotes);
        return join(roughXPositionsForAccidentals, roughXPositionsForNotes);
    }

    private Mark[][] join(Mark[][] left, Mark[][] right) {
        Mark[][] joined = new Mark[left.length][left[0].length + right[0].length];
        for (int row = 0; row < left.length; row++) {
            for (int column = 0; column < left[0].length; column++) {
                joined[row][column] = left[row][column];
            }
        }
        for (int row = 0; row < right.length; row++) {
            for (int column = 0; column < right[0].length; column++) {
                joined[row][left[0].length + column] = right[row][column];
            }
        }
        return joined;
    }

    private Optional<Integer> closestOverlappingAccidental(Map<Integer, Integer> accidentalPositions, int nextAccidental) {
        for (int i = 1; i < SPACES_BETWEEN_NON_OVERLAPPING_ACCIDENTALS; i++) {
            int overlappingAccidental = nextAccidental - i;
            Integer integer = accidentalPositions.get(overlappingAccidental);
            if (integer != null) {
                return Optional.of(overlappingAccidental);
            }
        }
        return Optional.absent();
    }

    private Mark[][] calculateRoughXPositionsForAccidentals(Mark[][] roughXPositionsForNotes, Key key, List<Note> sortedNotes) {
        if (roughXPositionsForNotes.length != sortedNotes.size()) {
            throw new IllegalStateException("invalid Mark[][] for notes");
        }

        Map<Integer, Integer> accidentalPositions = new HashMap<>();
        for (int i = 0; i < sortedNotes.size(); i++) {
            Note note = sortedNotes.get(i);
            Optional<Accidental> accidental = ChromaticScales.accidental(key, note);
            if (accidental.isPresent()) {
                Optional<Integer> closestOverlappingAccidental = closestOverlappingAccidental(accidentalPositions, i);
                if (closestOverlappingAccidental.isPresent()) {
                    int roughXPositionClosestOverlappingAccidental = accidentalPositions.get(closestOverlappingAccidental.get());
                    accidentalPositions.put(i, roughXPositionClosestOverlappingAccidental + 1);
                } else {
                    accidentalPositions.put(i, 0);
                }
            }
        }

        int highestColumnIndex = 0;
        for (int roughXPosition : accidentalPositions.values()) {
            if (roughXPosition > highestColumnIndex) {
                highestColumnIndex = roughXPosition;
            }
        }

        Mark[][] positionsArray = new Mark[sortedNotes.size()][highestColumnIndex + 1];
        for (Map.Entry<Integer, Integer> accidentalPosition : accidentalPositions.entrySet()) {
            int row = accidentalPosition.getKey();
            Optional<Accidental> accidental = ChromaticScales.accidental(key, sortedNotes.get(row));
            Mark mark = accidental.get() == Accidental.NATURAL ? NATURAL : Mark.SHARP;
            positionsArray[row][accidentalPosition.getValue()] = mark;
        }

        return positionsArray;
    }

    private Mark[][] calculateRoughXPositionsForNotes(Key key, List<Note> sortedNotes) {
        if (sortedNotes.isEmpty()) {
            return new Mark[0][0];
        }

        if (sortedNotes.size() == 1) {
            Mark[][] positionsArray = new Mark[1][NUMBER_OF_COLUMNS_ROUGH_X_POSITIONS_NOTES];
            positionsArray[0][NOTE_PRIMARY] = Mark.NOTE;
            return positionsArray;
        }

        Mark[][] positionsArray = new Mark[sortedNotes.size()][NUMBER_OF_COLUMNS_ROUGH_X_POSITIONS_NOTES];
        for (int i = 0; i < sortedNotes.size(); i++) {
            Note note = sortedNotes.get(i);
            if (i == 0) { // this is the first note of multiple notes so we just check next
                Note next = sortedNotes.get(i + 1);
                if (overlapsVertically(key, note, next)) {
                    positionsArray[i][NOTE_SECONDARY] = Mark.NOTE;
                } else {
                    positionsArray[i][NOTE_PRIMARY] = Mark.NOTE;
                }
            } else {
                Note previous = sortedNotes.get(i - 1);
                if (overlapsVertically(key, previous, note)) {
                    Mark[] previousValue = positionsArray[i - 1];
                    if (previousValue[NOTE_PRIMARY] != null) {
                        positionsArray[i][NOTE_SECONDARY] = Mark.NOTE;
                    } else {
                        positionsArray[i][NOTE_PRIMARY] = Mark.NOTE;
                    }
                } else {
                    positionsArray[i][NOTE_PRIMARY] = Mark.NOTE;
                }
            }
        }
        return positionsArray;
    }

    private boolean overlapsVertically(Key key, Note note, Note otherNote) {
        int i = offsetCalculator.calculateOffset(key, note);
        int j = offsetCalculator.calculateOffset(key, otherNote);
        return Math.abs(i - j) < 2;
    }

    private int width(Mark mark) {
        switch (mark) {
            case NATURAL:
                return symbolSizes.natural.width();
            case SHARP:
                return symbolSizes.sharp.width();
            case NOTE:
                return symbolSizes.note.width();
            default:
                throw new IllegalArgumentException("dunno width for mark: " + mark);
        }
    }

    private int height(Mark mark) {
        switch (mark) {
            case NATURAL:
                return symbolSizes.natural.height();
            case SHARP:
                return symbolSizes.sharp.height();
            case NOTE:
                return symbolSizes.note.height();
            default:
                throw new IllegalArgumentException("dunno height for mark: " + mark);
        }
    }

    private int calculateYPosition(Key key, Note note, int highestMiddleCeeOffset) {
        int middleCeeOffset = offsetCalculator.calculateOffset(key, note);
        int relativeOffset = middleCeeOffset - highestMiddleCeeOffset;
        return deltaGivenOffset(relativeOffset);
    }

    @Px
    private int deltaGivenOffset(int offset) {
        return (int) (((offset * 0.5) + 0.5) * symbolSizes.note.height());
    }


}
