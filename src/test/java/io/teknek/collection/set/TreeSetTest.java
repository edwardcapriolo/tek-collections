package io.teknek.collection.set;

import io.teknek.collections.Sequence;
import io.teknek.collections.evolving.Maybe;
import io.teknek.collections.list.ImmutableArrayList;
import io.teknek.collections.list.MutableArrayList;
import io.teknek.collections.sequence.ObjectSequence;
import io.teknek.collections.set.TreeSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTest {

    @Test
    void testContains(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 9, 4, 5, 6, 5);
        assertFalse(t.contains(1));
        assertTrue(t.contains(5));
    }

    @Test
    void testFirst(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        assertEquals(2, t.first());
    }

    @Test
    void testLast(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        assertEquals(6, t.last());
    }

    @Test
    void testLastOrNone(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        assertEquals(Maybe.definitely(6) , t.lastOrNothing());
        assertEquals(4, t.size());
    }

    @Test
    void testLastOrNothing(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, new Integer[0]);
        assertEquals(Maybe.nothing(), t.lastOrNothing());
    }

    @Test
    void inOrderTraversal(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        MutableArrayList<Integer> assertList = new MutableArrayList<>();
        t.inOrderVisitor(assertList::add);
        Sequence.equals(assertList.iterator(), new ImmutableArrayList<>(2, 4, 5, 6).iterator());
    }

}
