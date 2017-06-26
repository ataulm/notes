package com.ataulm.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.google.gson.Gson;

public class DebugActivity extends AppCompatActivity {

    private static final String TEST_JSON = "[\n" +
            "    [{midi: 60, staff: treble},{midi: 60, staff: bass}],\n" +
            "    [{midi: 60, staff: treble}]" +
            "]";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        NoteWidget simpleWidget = NoteWidget.simple(this);
        simpleWidget.setBackgroundResource(android.R.color.holo_orange_light);
        viewGroup.addView(simpleWidget);

        NoteWidget sharpWidget = NoteWidget.sharp(this);
        sharpWidget.setBackgroundResource(android.R.color.holo_blue_light);
        viewGroup.addView(sharpWidget);

        NoteWidget flatWidget = NoteWidget.flat(this);
        flatWidget.setBackgroundResource(android.R.color.holo_green_light);
        viewGroup.addView(flatWidget);

        NoteWidget naturalWidget = NoteWidget.natural(this);
        naturalWidget.setBackgroundResource(android.R.color.holo_red_light);
        viewGroup.addView(naturalWidget);

        SequenceJsonConverter sequenceJsonConverter = new SequenceJsonConverter(new Gson());
        Sequence sequence = sequenceJsonConverter.convert(TEST_JSON);
        Log.d("!!!", "onCreate: " + sequence);
    }

}
