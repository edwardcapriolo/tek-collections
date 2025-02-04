package io.teknek.collection.set;

import io.teknek.collections.evolving.Maybe;
import io.teknek.collections.set.MaybeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaybeComparatorTest {

    @Test
    public void comparator(){
        Comparator comparer = MaybeComparator.of(Integer::compareTo);
        assertEquals(0, comparer.compare(5, 5));
        assertEquals(-1, comparer.compare(4, 5));
        assertEquals(1, comparer.compare(5, 4));
        assertEquals(-1, comparer.compare(Maybe.nothing(), 4));
        assertEquals(1, comparer.compare(4, Maybe.nothing()));
    }
}
