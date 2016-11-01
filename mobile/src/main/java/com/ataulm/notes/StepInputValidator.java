package com.ataulm.notes;

class StepInputValidator implements InputValidator {

    @Override
    public boolean matches(Note input, Note target) {
        return input.pitch().step() == target.pitch().step();
    }

}
