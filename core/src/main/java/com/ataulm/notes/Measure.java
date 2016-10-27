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

    public int notes() {
        int notes = 0;
        for (Bucket bucket : buckets) {
            notes += bucket.size();
        }
        return notes;
    }

    public int size() {
        return buckets.size();
    }

    public Bucket get(int position) {
        return buckets.get(position);
    }
}
