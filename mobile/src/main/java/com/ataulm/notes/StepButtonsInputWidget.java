package com.ataulm.notes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class StepButtonsInputWidget extends LinearLayout implements InputView {

    private final Map<Step, View> buttons = new HashMap<>(Step.values().length);

    public StepButtonsInputWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_steps_buttons_input_widget, this);

        buttons.put(Step.C, ButterKnife.findById(this, R.id.steps_buttons_input_c));
        buttons.put(Step.D, ButterKnife.findById(this, R.id.steps_buttons_input_d));
        buttons.put(Step.E, ButterKnife.findById(this, R.id.steps_buttons_input_e));
        buttons.put(Step.F, ButterKnife.findById(this, R.id.steps_buttons_input_f));
        buttons.put(Step.G, ButterKnife.findById(this, R.id.steps_buttons_input_g));
        buttons.put(Step.A, ButterKnife.findById(this, R.id.steps_buttons_input_a));
        buttons.put(Step.B, ButterKnife.findById(this, R.id.steps_buttons_input_b));
    }

    @Override
    public void update(final OnClickNoteListener onClickNoteListener) {
        for (Map.Entry<Step, View> stepViewEntry : buttons.entrySet()) {
            final OldNote oldNote = getNote(stepViewEntry);
            View view = stepViewEntry.getValue();

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickNoteListener.onClick(oldNote);
                }
            });
        }
    }

    private OldNote getNote(Map.Entry<Step, View> stepViewEntry) {
        return new OldNote(new Pitch(stepViewEntry.getKey(), Octave.ONE_LINE), Staff.TREBLE);
    }

}
