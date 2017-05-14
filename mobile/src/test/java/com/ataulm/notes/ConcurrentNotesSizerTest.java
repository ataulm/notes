package com.ataulm.notes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static com.ataulm.notes.ConcurrentNotes.create;
import static com.ataulm.notes.Fixtures.A_S4;
import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.C_S4;
import static com.ataulm.notes.Fixtures.C_S5;
import static com.ataulm.notes.Fixtures.E4;
import static com.ataulm.notes.Fixtures.F_S4;
import static com.ataulm.notes.Fixtures.G4;
import static com.ataulm.notes.Fixtures.G_S4;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ConcurrentNotesSizerTest {

    private static final Size NOTE_SIZE = Size.create(10, 10);
    private static final Size ACCIDENTAL_SIZE = Size.create(10, 16);
    private List<PositionedMark> positionedMarks;

    @Parameterized.Parameters(name = "Given {1} in Key {0}, expected size: {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Key.C_MAJ, create(C4), Size.create(10, 10)},
                {Key.C_MAJ, create(C_S4), Size.create(20, 16)},
                {Key.C_MAJ, create(C4, E4, G4), Size.create(10, 30)},
                {Key.C_MAJ, create(C4, F_S4, G_S4), Size.create(40, 33)},
                {Key.C_MAJ, create(C_S5, A_S4, F_S4), Size.create(30, 36)} // TODO: this should be 40, 36
        });
    }

    @Parameterized.Parameter(0)
    public Key key;

    @Parameterized.Parameter(1)
    public ConcurrentNotes notes;

    @Parameterized.Parameter(2)
    public Size expectedSize;

    ConcurrentNotesSizer sizer;

    @Before
    public void setUp() {
        MusicalSymbolSizes sizes = new MusicalSymbolSizes(NOTE_SIZE, ACCIDENTAL_SIZE, ACCIDENTAL_SIZE, ACCIDENTAL_SIZE);
        ConcurrentNotesPositionCalculator concurrentNotesPositionCalculator = new ConcurrentNotesPositionCalculator(sizes, new MiddleCeeOffsetCalculator());
        positionedMarks = concurrentNotesPositionCalculator.calculatePositions(key, notes); // lol nice unit test
        sizer = new ConcurrentNotesSizer(sizes);
    }

    @Test
    public void size() {
        Size size = sizer.size(positionedMarks);

        assertThat(size).isEqualTo(expectedSize);
    }

}