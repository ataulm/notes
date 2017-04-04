package com.ataulm.notes.legacy;

class PitchInputValidator implements InputValidator {

    @Override
    public boolean matches(Note input, Note target) {
        return input.pitch().equals(target.pitch());
    }

}
