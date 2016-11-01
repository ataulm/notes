package com.ataulm.notes;

class PitchInputValidator implements InputValidator {

    @Override
    public boolean matches(Note input, Note target) {
        return input.pitch().equals(target.pitch());
    }

}
