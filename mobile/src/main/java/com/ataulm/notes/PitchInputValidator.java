package com.ataulm.notes;

class PitchInputValidator implements InputValidator {

    @Override
    public boolean matches(OldNote input, OldNote target) {
        return input.pitch().equals(target.pitch());
    }

}
