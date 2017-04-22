package com.ataulm.notes;

class SongFactory {

    private static final ConcurrentNotes C = ConcurrentNotes.create(Fixtures.C4, Fixtures.E4, Fixtures.G4);
    private static final ConcurrentNotes F_2ND_INV = ConcurrentNotes.create(Fixtures.C4, Fixtures.F4, Fixtures.A4);
    private static final ConcurrentNotes G_2ND_INV = ConcurrentNotes.create(Fixtures.D4, Fixtures.G4, Fixtures.B4);
    private static final ConcurrentNotes EM = ConcurrentNotes.create(Fixtures.E4, Fixtures.G4, Fixtures.B4);
    private static final ConcurrentNotes AM_2ND_INV = ConcurrentNotes.create(Fixtures.E4, Fixtures.A4, Fixtures.C5);
    private static final ConcurrentNotes G = ConcurrentNotes.create(Fixtures.G4, Fixtures.B4, Fixtures.D5);
    private static final ConcurrentNotes C_2ND_INV = ConcurrentNotes.create(Fixtures.G4, Fixtures.C5, Fixtures.E5);

    static Sequence test() {
        return Sequence.create(
//                ConcurrentNotes.create(Note.create(60), Note.create(64), Note.create(67)),
//                ConcurrentNotes.create(Note.create(60)),
                ConcurrentNotes.create(Note.create(60), Note.create(57))//, Note.create(52))
//                ConcurrentNotes.create(Note.create(59))
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
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.E4),
                ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.E4), ConcurrentNotes.create(Fixtures.D4), ConcurrentNotes.create(Fixtures.C4)
        );
    }

}
