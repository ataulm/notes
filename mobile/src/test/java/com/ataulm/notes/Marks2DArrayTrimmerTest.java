package com.ataulm.notes;

import org.junit.Test;

import static com.ataulm.notes.Mark.NOTE;
import static com.ataulm.notes.Mark.SHARP;
import static org.fest.assertions.api.Assertions.assertThat;

public class Marks2DArrayTrimmerTest {

    /*
    [ ][#][ ][0]  ->  [#][ ][0]
    [ ][ ][0][ ]  ->  [ ][0][ ]
    [ ][ ][ ][0]  ->  [ ][ ][0]
    [ ][ ][0][ ]  ->  [ ][0][ ]
    */
    @Test
    public void trimFirstColumn() {
        Mark[][] input = new Mark[][]{
                new Mark[]{null, SHARP, null, NOTE},
                new Mark[]{null, null, NOTE, null},
                new Mark[]{null, null, null, NOTE},
                new Mark[]{null, null, NOTE, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(input);

        Mark[][] expected = new Mark[][]{
                new Mark[]{SHARP, null, NOTE},
                new Mark[]{null, NOTE, null},
                new Mark[]{null, null, NOTE},
                new Mark[]{null, NOTE, null}
        };
        assertThat(trimmed).isEqualTo(expected);
    }

    /*
    [ ][ ][#][ ][0]  ->  [#][ ][0]
    [ ][ ][ ][0][ ]  ->  [ ][0][ ]
    [ ][ ][ ][ ][0]  ->  [ ][ ][0]
    [ ][ ][ ][0][ ]  ->  [ ][0][ ]
    */
    @Test
    public void trimMultipleFirstColumns() {
        Mark[][] input = new Mark[][]{
                new Mark[]{null, null, SHARP, null, NOTE},
                new Mark[]{null, null, null, NOTE, null},
                new Mark[]{null, null, null, null, NOTE},
                new Mark[]{null, null, null, NOTE, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(input);

        Mark[][] expected = new Mark[][]{
                new Mark[]{SHARP, null, NOTE},
                new Mark[]{null, NOTE, null},
                new Mark[]{null, null, NOTE},
                new Mark[]{null, NOTE, null}
        };
        assertThat(trimmed).isEqualTo(expected);
    }

    /*
    [#][ ]  ->  [#][ ]
    [ ][0]  ->  [ ][0]
    [ ][ ]  ->  [ ][ ]
    */
    @Test
    public void doesNotTrimRows() {
        Mark[][] input = new Mark[][]{
                new Mark[]{SHARP, null},
                new Mark[]{null, NOTE},
                new Mark[]{null, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(input);

        Mark[][] expected = input;
        assertThat(trimmed).isEqualTo(expected);
    }

    /*
    [#][0][ ]  ->  [#][0]
    [ ][ ][ ]  ->  [ ][ ]
    [ ][0][ ]  ->  [ ][0]
    */
    @Test
    public void trimLastColumn() {
        Mark[][] input = new Mark[][]{
                new Mark[]{SHARP, NOTE, null},
                new Mark[]{null, null, null},
                new Mark[]{null, NOTE, null},
                new Mark[]{null, null, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(input);

        Mark[][] expected = new Mark[][]{
                new Mark[]{SHARP, NOTE},
                new Mark[]{null, null},
                new Mark[]{null, NOTE},
                new Mark[]{null, null}
        };
        assertThat(trimmed).isEqualTo(expected);
    }

    /*
    [#][0][ ][ ]  ->  [#][0]
    [ ][ ][ ][ ]  ->  [ ][ ]
    [ ][0][ ][ ]  ->  [ ][0]
    */
    @Test
    public void trimMultipleLastColumns() {
        Mark[][] input = new Mark[][]{
                new Mark[]{SHARP, NOTE, null, null},
                new Mark[]{null, null, null, null},
                new Mark[]{null, NOTE, null, null},
                new Mark[]{null, null, null, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(input);

        Mark[][] expected = new Mark[][]{
                new Mark[]{SHARP, NOTE},
                new Mark[]{null, null},
                new Mark[]{null, NOTE},
                new Mark[]{null, null}
        };
        assertThat(trimmed).isEqualTo(expected);
    }

    /*
    [ ][#][0][ ]  ->  [#][0]
    [ ][ ][ ][ ]  ->  [ ][ ]
    [ ][ ][0][ ]  ->  [ ][0]
    */
    @Test
    public void trimBothSides() {
        Mark[][] foo = new Mark[][]{
                new Mark[]{null, SHARP, NOTE, null},
                new Mark[]{null, null, null, null},
                new Mark[]{null, null, NOTE, null}
        };

        Mark[][] trimmed = Marks2DArrayTrimmer.trim(foo);

        Mark[][] expected = new Mark[][]{
                new Mark[]{SHARP, NOTE},
                new Mark[]{null, null},
                new Mark[]{null, NOTE}
        };
        assertThat(trimmed).isEqualTo(expected);
    }

}