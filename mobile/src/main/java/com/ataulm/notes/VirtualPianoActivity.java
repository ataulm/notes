package com.ataulm.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static com.ataulm.notes.SongFactory.test;

public class VirtualPianoActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_piano);

        TrebleStaffWidget trebleStaffWidget = (TrebleStaffWidget) findViewById(R.id.treble_staff_widget);
        trebleStaffWidget.bind(test());
    }

}
