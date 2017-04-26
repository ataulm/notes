package com.ataulm.notes;

import com.ataulm.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.C_S4;
import static com.ataulm.notes.Fixtures.F5;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ChromaticScalesTest {

    @Parameterized.Parameters(name = "Given {1} in Key {0}, expected accidental: {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Key.C_MAJ, C4, Optional.absent()},
                {Key.C_MAJ, C_S4, Optional.of(Accidental.SHARP)},
                {Key.G_MAJ, F5, Optional.of(Accidental.NATURAL)},
        });
    }

    @Parameterized.Parameter(0)
    public Key key;

    @Parameterized.Parameter(1)
    public Note note;

    @Parameterized.Parameter(2)
    public Optional<Accidental> expectedAccidental;

    @Test
    public void accidental() {
        Optional<Accidental> accidental = ChromaticScales.accidental(key, note);

        assertThat(accidental).isEqualTo(expectedAccidental);
    }

}