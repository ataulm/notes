package com.ataulm.notes;

public enum Staff {

    TREBLE,
    BASS;

    public static Staff matching(String staff) {
        for (Staff value : values()) {
            if (value.name().toLowerCase().equals(staff.toLowerCase())) {
                return value;
            }
        }
        throw new IllegalArgumentException("staff not recognised: " + staff);
    }

}
