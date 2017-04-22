package com.ataulm.notes;

import org.junit.Before;
import org.junit.Test;

import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.G4;
import static org.fest.assertions.api.Assertions.assertThat;

public class ConcurrentNotesWidgetHelperTest {

    private static final int NOTE_WIDTH = 10;
    private static final int NOTE_HEIGHT = 10;

    private static final int SHARP_WIDTH = 10;
    private static final int SHARP_HEIGHT = 10;

    private static final int FLAT_WIDTH = 10;
    private static final int FLAT_HEIGHT = 10;

    private static final int NATURAL_WIDTH = 10;
    private static final int NATURAL_HEIGHT = 10;

    ConcurrentNotesWidgetHelper calculator;

    @Before
    public void setUp() {
        MusicalSymbolSizes symbolSizes = new MusicalSymbolSizes(
                Size.create(NOTE_WIDTH, NOTE_HEIGHT),
                Size.create(SHARP_WIDTH, SHARP_HEIGHT),
                Size.create(FLAT_WIDTH, FLAT_HEIGHT),
                Size.create(NATURAL_WIDTH, NATURAL_HEIGHT)
        );
        calculator = new ConcurrentNotesWidgetHelper(symbolSizes, new MiddleCeeOffsetCalculator());
    }

    @Test
    public void whenSizingSingleNote_thenSizeMatchesSingleNote() {
        ConcurrentNotes notes = ConcurrentNotes.create(C4);

        ConcurrentNotesWidgetHelper.Output output = calculator.size(Key.C_MAJ, notes);

        assertThat(output.widgetSize()).isEqualTo(Size.create(NOTE_WIDTH, NOTE_HEIGHT));
    }

    @Test
    public void whenSizingFooNote_thenSizeMatchesSingleNote() {
        ConcurrentNotes notes = ConcurrentNotes.create(C4, G4);

        ConcurrentNotesWidgetHelper.Output output = calculator.size(Key.C_MAJ, notes);

        assertThat(output.widgetSize().height()).isEqualTo(3 * NOTE_HEIGHT);
    }

}