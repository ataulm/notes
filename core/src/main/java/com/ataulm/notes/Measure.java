package com.ataulm.notes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Assume key of C major with 4/4 time sig
 */
class Measure implements Iterable<Bucket> {

    private final List<Bucket> buckets;

    Measure(Bucket... buckets) {
        this(Arrays.asList(buckets));
    }

    private Measure(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    @Override
    public Iterator<Bucket> iterator() {
        return buckets.iterator();
    }

}
