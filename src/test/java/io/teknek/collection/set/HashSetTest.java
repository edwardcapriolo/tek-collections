package io.teknek.collection.set;

import io.teknek.collections.set.ImmutableHashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    @Test
    void test(){
        ImmutableHashSet<Integer> a = new ImmutableHashSet<>(1, 2);
        assertTrue(a.contains(1));
        assertFalse(a.contains(9));
        assertEquals(2, a.size());
    }
}
