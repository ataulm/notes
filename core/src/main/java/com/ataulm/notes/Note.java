package com.ataulm.notes;

public class Note {

    private final Pitch pitch;
    private final Staff staff;

    public Note(Pitch pitch, Staff staff) {
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

        Note note = (Note) o;

        if (!pitch.equals(note.pitch)) {
            return false;
        }
        return staff == note.staff;

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

