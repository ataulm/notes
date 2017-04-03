package com.ataulm.notes;

class StepInputValidator implements InputValidator {

    @Override
    public boolean matches(OldNote input, OldNote target) {
        return input.pitch().step() == target.pitch().step();
    }

}
