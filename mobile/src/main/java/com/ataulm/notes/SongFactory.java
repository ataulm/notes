package com.ataulm.notes;

import static com.ataulm.notes.Fixtures.A4;
import static com.ataulm.notes.Fixtures.B4;
import static com.ataulm.notes.Fixtures.C4;
import static com.ataulm.notes.Fixtures.C5;
import static com.ataulm.notes.Fixtures.D4;
import static com.ataulm.notes.Fixtures.D5;
import static com.ataulm.notes.Fixtures.E4;
import static com.ataulm.notes.Fixtures.E5;
import static com.ataulm.notes.Fixtures.F4;
import static com.ataulm.notes.Fixtures.F_S4;
import static com.ataulm.notes.Fixtures.G4;
import static com.ataulm.notes.Fixtures.G_S4;

class SongFactory {

    private static final ConcurrentNotes C = ConcurrentNotes.create(C4, E4, G4);
    private static final ConcurrentNotes F_2ND_INV = ConcurrentNotes.create(C4, F4, A4);
    private static final ConcurrentNotes G_2ND_INV = ConcurrentNotes.create(D4, G4, B4);
    private static final ConcurrentNotes EM = ConcurrentNotes.create(E4, G4, B4);
    private static final ConcurrentNotes AM_2ND_INV = ConcurrentNotes.create(E4, A4, C5);
    private static final ConcurrentNotes G = ConcurrentNotes.create(G4, B4, D5);
    private static final ConcurrentNotes C_2ND_INV = ConcurrentNotes.create(G4, C5, E5);

    static Sequence test() {
        return Sequence.create(
                ConcurrentNotes.create(F_S4),
                ConcurrentNotes.create(F_S4, C5, D5),
                ConcurrentNotes.create(G4, C5, E5),
                ConcurrentNotes.create(C4, F_S4, G_S4)
        );
    }

    static Sequence pachelbelsCanon() {
        return Sequence.create(
                C_2ND_INV, G, AM_2ND_INV, EM,
                F_2ND_INV, C, F_2ND_INV, G_2ND_INV,
                C_2ND_INV, G, AM_2ND_INV, EM,
                F_2ND_INV, C, F_2ND_INV, G_2ND_INV,
                C_2ND_INV, G, AM_2ND_INV, EM,
                F_2ND_INV, C, F_2ND_INV, G_2ND_INV, C
        );
    }

    static Sequence maryHadALittleLamb() {
        return Sequence.create(
                ConcurrentNotes.create(E4), ConcurrentNotes.create(E4), ConcurrentNotes.create(D4), ConcurrentNotes.create(C4), ConcurrentNotes.create(D4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4),
                ConcurrentNotes.create(D4), ConcurrentNotes.create(D4), ConcurrentNotes.create(D4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4),
                ConcurrentNotes.create(E4), ConcurrentNotes.create(D4), ConcurrentNotes.create(C4), ConcurrentNotes.create(D4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4), ConcurrentNotes.create(E4),
                ConcurrentNotes.create(E4), ConcurrentNotes.create(D4), ConcurrentNotes.create(D4), ConcurrentNotes.create(E4), ConcurrentNotes.create(D4), ConcurrentNotes.create(C4)
        );
    }

}
