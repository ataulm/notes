package com.ataulm.notes;

import java.util.List;

class ConcurrentNotesSizer {

    private final MusicalSymbolSizes symbolSizes;

    ConcurrentNotesSizer(MusicalSymbolSizes symbolSizes) {
        this.symbolSizes = symbolSizes;
    }

    Size size(List<PositionedMark> positionedMarks) {
        int width = width(positionedMarks);
        int height = height(positionedMarks);

        return Size.create(width, height);
    }

    private int width(List<PositionedMark> positionedMarks) {
        int rightestRight = 0;
        for (PositionedMark positionedMark : positionedMarks) {
            int width = width(positionedMark.mark());
            int x = positionedMark.center().x();
            int halfWidth = (int) (width * 0.5);
            int right = x + halfWidth;
            if (right > rightestRight) {
                rightestRight = right;
            }
        }
        return rightestRight;
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

    private int height(List<PositionedMark> positionedMarks) {
        int highestTop = 0;
        int lowestBottom = 0;
        for (PositionedMark positionedMark : positionedMarks) {
            int height = height(positionedMark.mark());
            int y = positionedMark.center().y();
            int halfHeight = (int) (height * 0.5);
            int top = y - halfHeight;
            if (top < highestTop) {
                highestTop = top;
            }
            int bottom = y + halfHeight;
            if (bottom > lowestBottom) {
                lowestBottom = bottom;
            }
        }
        return lowestBottom - highestTop;
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

}
