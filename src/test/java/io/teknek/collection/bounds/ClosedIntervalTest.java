package io.teknek.collection.bounds;

import io.teknek.collections.bounds.ClosedInterval;
import io.teknek.collections.bounds.Inclusive;
import io.teknek.collections.bounds.PositiveIntOrZero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.teknek.collections.bounds.Inclusive.*;

public class ClosedIntervalTest {
    @Test
    void bounds(){
        PositiveIntOrZero z = new PositiveIntOrZero();
        assertFalse(z.isBetweenBounds(-1));
        assertTrue(z.isBetweenBounds(0));
        assertTrue(z.isBetweenBounds(1));
        assertFalse(z.isBetweenBounds(Long.MAX_VALUE));
    }

    @Test
    void inclusiveTests(){
        ClosedInterval larger = new ClosedInterval(Inclusive.of(0), Inclusive(10));
        ClosedInterval smaller = new ClosedInterval( new Inclusive(5), new Inclusive(7));
        assertTrue(larger.contains(smaller));
        assertFalse(smaller.contains(larger));
        assertTrue(larger.contains(larger));
    }
}
