package com.ataulm.notes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static com.ataulm.notes.Fixtures.A3;
import static com.ataulm.notes.Fixtures.A4;
import static com.ataulm.notes.Fixtures.A_S3;
import static com.ataulm.notes.Fixtures.A_S4;
import static com.ataulm.notes.Fixtures.B3;
import static com.ataulm.notes.Fixtures.B4;
import static com.ataulm.notes.Fixtures.B5;
import static com.ataulm.notes.Fixtures.C3;
import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.C5;
import static com.ataulm.notes.Fixtures.C_S4;
import static com.ataulm.notes.Fixtures.D4;
import static com.ataulm.notes.Fixtures.D_S4;
import static com.ataulm.notes.Fixtures.E4;
import static com.ataulm.notes.Fixtures.F4;
import static com.ataulm.notes.Fixtures.F_S4;
import static com.ataulm.notes.Fixtures.G4;
import static com.ataulm.notes.Fixtures.G_S4;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PositionsApartFromMiddleCCalculatorTest {

    @Parameterized.Parameters(name = "Given {1} in Key {0}, expected expectedPositionFromMiddleC: {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Key.C_MAJ, C3, 7},
                {Key.C_MAJ, A3, 2},
                {Key.C_MAJ, A_S3, 2},
                {Key.C_MAJ, B3, 1},
                {Key.C_MAJ, C4, 0},
                {Key.C_MAJ, C_S4, 0},
                {Key.C_MAJ, D4, -1},
                {Key.C_MAJ, D_S4, -1},
                {Key.C_MAJ, E4, -2},
                {Key.C_MAJ, F4, -3},
                {Key.C_MAJ, F_S4, -3},
                {Key.C_MAJ, G4, -4},
                {Key.C_MAJ, G_S4, -4},
                {Key.C_MAJ, A4, -5},
                {Key.C_MAJ, A_S4, -5},
                {Key.C_MAJ, B4, -6},
                {Key.C_MAJ, C5, -7},
                {Key.C_MAJ, B5, -13},

                {Key.G_MAJ, C3, 7},
                {Key.G_MAJ, A3, 2},
                {Key.G_MAJ, A_S3, 2},
                {Key.G_MAJ, B3, 1},
                {Key.G_MAJ, C4, 0},
                {Key.G_MAJ, C_S4, 0},
                {Key.G_MAJ, D4, -1},
                {Key.G_MAJ, D_S4, -1},
                {Key.G_MAJ, E4, -2},
                {Key.G_MAJ, F4, -3},
                {Key.G_MAJ, F_S4, -3},
                {Key.G_MAJ, G4, -4},
                {Key.G_MAJ, G_S4, -4},
                {Key.G_MAJ, A4, -5},
                {Key.G_MAJ, A_S4, -5},
                {Key.G_MAJ, B4, -6},
                {Key.G_MAJ, C5, -7},
                {Key.G_MAJ, B5, -13},

                {Key.F_MAJ, C3, 7},
                {Key.F_MAJ, A3, 2},
                {Key.F_MAJ, A_S3, 1},
                {Key.F_MAJ, B3, 1},
                {Key.F_MAJ, C4, 0},
                {Key.F_MAJ, C_S4, 0},
                {Key.F_MAJ, D4, -1},
                {Key.F_MAJ, D_S4, -1},
                {Key.F_MAJ, E4, -2},
                {Key.F_MAJ, F4, -3},
                {Key.F_MAJ, F_S4, -3},
                {Key.F_MAJ, G4, -4},
                {Key.F_MAJ, G_S4, -4},
                {Key.F_MAJ, A4, -5},
                {Key.F_MAJ, A_S4, -6},
                {Key.F_MAJ, B4, -6},
                {Key.F_MAJ, C5, -7},
                {Key.F_MAJ, B5, -13}
        });
    }

    @Parameterized.Parameter(0)
    public Key key;

    @Parameterized.Parameter(1)
    public Note note;

    @Parameterized.Parameter(2)
    public int expectedPositionFromMiddleC;

    PositionsApartFromMiddleCCalculator calculator = new PositionsApartFromMiddleCCalculator();

    @Test
    public void positionFromMiddleC() {
        int positionFromMiddleC = calculator.positionsApartFromMiddleC(key, note);

        assertThat(positionFromMiddleC).isEqualTo(expectedPositionFromMiddleC);
    }

}