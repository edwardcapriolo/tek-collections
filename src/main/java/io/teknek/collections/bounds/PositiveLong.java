package io.teknek.collections.bounds;

public class PositiveLong extends ClosedInterval {

    private PositiveLong() {
        super(Inclusive.of(1), Inclusive.of(Long.MAX_VALUE));
    }
}
