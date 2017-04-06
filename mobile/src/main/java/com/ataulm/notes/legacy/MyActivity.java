package com.ataulm.notes.legacy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ataulm.notes.R;

import butterknife.ButterKnife;

public class MyActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // TODO: read user prefs to understand which views to display
        NotesView notesView = ButterKnife.findById(this, R.id.notes_view);
        InputView inputView = ButterKnife.findById(this, R.id.input_view);

        NotesPresenter notesPresenter = new NotesPresenter(
                notesView,
                inputView,
                new NotesFetcher(),
                new NoteViewModelsFactory(),
                new StepInputValidator()
        );
        notesPresenter.present();
    }

}
