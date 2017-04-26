package com.ataulm.notes;

import com.ataulm.Optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ConcurrentNotesWidgetSizer {

    private static final Comparator<Note> NOTE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            if (o1.midi() == o2.midi()) {
                return 0;
            }
            if (o1.midi() > o2.midi()) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    private final MusicalSymbolSizes symbolSizes;
    private final MiddleCeeOffsetCalculator offsetCalculator;

    ConcurrentNotesWidgetSizer(MusicalSymbolSizes symbolSizes, MiddleCeeOffsetCalculator offsetCalculator) {
        this.symbolSizes = symbolSizes;
        this.offsetCalculator = offsetCalculator;
    }

    Size size(Key key, ConcurrentNotes concurrentNotes) {
        Map<Note, Position> positions = positions(key, concurrentNotes);

        int width = width(key, concurrentNotes, positions);
        int height = height(key, concurrentNotes, positions);
        return Size.create(width, height);
    }

    private int width(Key key, ConcurrentNotes concurrentNotes, Map<Note, Position> positions) {
        int farthestRight = 0;
        for (Note note : concurrentNotes) {
            Position position = positions.get(note);
            int right = position.x() + width(key, note);
            if (right > farthestRight) {
                farthestRight = right;
            }
        }
        return farthestRight;
    }

    private int width(Key key, Note note) {
        Optional<Accidental> accidental = ChromaticScales.accidental(key, note);
        if (accidental.isPresent()) {
            return width(accidental.get()) + symbolSizes.note.width();
        } else {
            return symbolSizes.note.width();
        }
    }

    private int width(Accidental accidental) {
        if (accidental == Accidental.NATURAL) {
            return symbolSizes.natural.width();
        } else {
            return symbolSizes.sharp.width();
        }
    }

    private int height(Key key, ConcurrentNotes concurrentNotes, Map<Note, Position> positions) {
        int lowestBottom = 0;
        for (Note note : concurrentNotes) {
            Position position = positions.get(note);
            int bottom = position.y() + height(key, note);
            if (bottom > lowestBottom) {
                lowestBottom = bottom;
            }
        }
        return lowestBottom;
    }

    // TODO: this will overlap notes/accidentals that are too close because x is always 0
    private Map<Note, Position> positions(Key key, ConcurrentNotes concurrentNotes) {
        List<Note> sortedNotes = new ArrayList<>(concurrentNotes.notes());
        Collections.sort(sortedNotes, NOTE_COMPARATOR);

        int topPositionsApart = offsetCalculator.calculateOffset(key, sortedNotes.get(0));

        Map<Note, Position> positions = new HashMap<>();

        for (Note note : sortedNotes) {
            int positionsApart = offsetCalculator.calculateOffset(key, note);
            int relativePositionsApart = positionsApart - topPositionsApart;
            positions.put(note, Position.create(0, (int) (-1 * relativePositionsApart * symbolSizes.note.height() * .5)));
        }

        return positions;
    }

    private int height(Key key, Note note) {
        Optional<Accidental> accidental = ChromaticScales.accidental(key, note);
        if (accidental.isPresent()) {
            return height(accidental.get());
        } else {
            return symbolSizes.note.height();
        }
    }

    private int height(Accidental accidental) {
        if (accidental == Accidental.NATURAL) {
            return symbolSizes.natural.height();
        } else {
            return symbolSizes.sharp.height();
        }
    }

}
