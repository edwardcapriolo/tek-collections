package io.teknek.collection.evolving;

import io.teknek.collections.*;
import io.teknek.collections.evolving.LazyStructures;
import io.teknek.collections.set.TreeSet;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyStructureTest {
    @Test
    void makeRange() {
        TreeSet<Integer> expected = new TreeSet<>(Comparator.naturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9);
        ImmutableIterable<Integer> z = LazyStructures.range(1, 10, 1);
        assertTrue(Sequence.equals(expected.inOrderIterator(), z.iterator()));
    }

    @Test
    void makeTRange() {
        TreeSet<Integer> expected = new TreeSet<>(Comparator.naturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9);
        ImmutableIterable<Integer> z = LazyStructures.range( 0, (x) -> x + 1, (Integer t) -> t < 10);
        assertTrue(Sequence.verboseEquals(expected.inOrderIterator(), z.iterator()));
    }
}
