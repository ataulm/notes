package com.ataulm.notes;

public class OldNote {

    private final Pitch pitch;
    private final Staff staff;

    public OldNote(Pitch pitch, Staff staff) {
        this.pitch = pitch;
        this.staff = staff;
    }

    public Pitch pitch() {
        return pitch;
    }

    public Staff staff() {
        return staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OldNote oldNote = (OldNote) o;

        if (!pitch.equals(oldNote.pitch)) {
            return false;
        }
        return staff == oldNote.staff;

    }

    @Override
    public int hashCode() {
        int result = pitch.hashCode();
        result = 31 * result + staff.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return pitch() + " (" + staff() + ")";
    }
}

