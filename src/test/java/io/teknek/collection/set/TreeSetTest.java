package io.teknek.collection.set;

import io.teknek.collections.set.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeSetTest {

    @Test
    void testContains(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 9, 4, 5, 6, 5);
        Assertions.assertFalse(t.contains(1));
        Assertions.assertTrue(t.contains(5));
    }

    @Test
    void testFirst(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        Assertions.assertEquals(2, t.first());
    }


    @Test
    void testLast(){
        TreeSet<Integer> t = new TreeSet<>(Integer::compareTo, 2, 4, 5, 6, 5);
        Assertions.assertEquals(6, t.last());
    }
}
