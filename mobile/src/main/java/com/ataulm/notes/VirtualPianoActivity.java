package com.ataulm.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class VirtualPianoActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_piano);

        TrebleStaffWidget trebleStaffWidget = (TrebleStaffWidget) findViewById(R.id.treble_staff_widget);
        trebleStaffWidget.bind(maryHadALittleLamb());
    }

    public Sequence maryHadALittleLamb() {
        return Sequence.create(
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4)
        );
    }

}
