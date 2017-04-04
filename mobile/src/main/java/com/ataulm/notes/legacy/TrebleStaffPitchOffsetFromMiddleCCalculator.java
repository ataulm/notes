package com.ataulm.notes.legacy;

import com.ataulm.notes.legacy.Octave;
import com.ataulm.notes.legacy.Pitch;

class TrebleStaffPitchOffsetFromMiddleCCalculator {

    private final int distanceBetweenStepsPx;
    private final int distanceBetweenOctavesPx;

    TrebleStaffPitchOffsetFromMiddleCCalculator(int distanceBetweenStepsPx) {
        this.distanceBetweenStepsPx = distanceBetweenStepsPx;
        this.distanceBetweenOctavesPx = distanceBetweenStepsPx * 7;
    }

    public int canvasOffsetY(Pitch pitch) {
        if (unsupported(pitch.octave())) {
            throw new IllegalArgumentException("only violin supported for now");
        }

        int octaveOffset = distanceBetweenOctavesPx * (pitch.octave().scientificNotation() - 4);
        int stepOffset = distanceBetweenStepsPx * pitch.step().indexInOctave();

        return -1 * (octaveOffset + stepOffset);
    }

    private boolean unsupported(Octave octave) {
        return !supported(octave);
    }

    private boolean supported(Octave octave) {
        return octave == Octave.SMALL
                || octave == Octave.ONE_LINE
                || octave == Octave.TWO_LINE
                || octave == Octave.THREE_LINE;
    }

}
