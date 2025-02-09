package io.teknek.collections.bounds;

import java.util.Optional;

public class PositiveIntOrZero extends ClosedInterval {
    public PositiveIntOrZero() {
        super(Inclusive.of(0), Inclusive.of(Integer.MAX_VALUE));
    }
}
