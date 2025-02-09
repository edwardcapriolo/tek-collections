package io.teknek.collection.bounds;

import io.teknek.collections.bounds.PositiveIntOrZero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClosedIntervalTest {
    @Test
    void bounds(){
        PositiveIntOrZero z = new PositiveIntOrZero();
        assertFalse(z.isBetweenBounds(-1));
        assertTrue(z.isBetweenBounds(0));
        assertTrue(z.isBetweenBounds(1));
        assertFalse(z.isBetweenBounds(Long.MAX_VALUE));
    }
}
