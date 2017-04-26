package com.ataulm.notes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static com.ataulm.notes.ConcurrentNotes.create;
import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.C_S4;
import static com.ataulm.notes.Fixtures.E4;
import static com.ataulm.notes.Fixtures.G4;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ConcurrentNotesWidgetSizerTest {

    private static final Size NOTE_SIZE = Size.create(10, 10);
    private static final Size ACCIDENTAL_SIZE = Size.create(10, 15);

    @Parameterized.Parameters(name = "Given {1} in Key {0}, expected size: {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Key.C_MAJ, create(C4), Size.create(10, 10)},
                {Key.C_MAJ, create(C_S4), Size.create(20, 15)},
                {Key.C_MAJ, create(C4, E4, G4), Size.create(10, 30)},
        });
    }

    @Parameterized.Parameter(0)
    public Key key;

    @Parameterized.Parameter(1)
    public ConcurrentNotes notes;

    @Parameterized.Parameter(2)
    public Size expectedSize;

    ConcurrentNotesWidgetSizer sizer;

    @Before
    public void setUp() {
        MusicalSymbolSizes sizes = new MusicalSymbolSizes(NOTE_SIZE, ACCIDENTAL_SIZE, ACCIDENTAL_SIZE, ACCIDENTAL_SIZE);
        sizer = new ConcurrentNotesWidgetSizer(sizes, new MiddleCeeOffsetCalculator());
    }

    @Test
    public void size() {
        Size size = sizer.size(key, notes);

        assertThat(size).isEqualTo(expectedSize);
    }

}