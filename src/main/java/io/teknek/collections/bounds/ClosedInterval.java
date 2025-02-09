package io.teknek.collections.bounds;

import java.util.Optional;

public class ClosedInterval {
    private final Value leftEndpoint;
    private final Value rightEndpoint;

    public ClosedInterval(Value lowest, Value highest){
        this.leftEndpoint = lowest;
        this.rightEndpoint = highest;
    }

    public boolean isBetweenBounds(long value){
        boolean left = leftEndpoint instanceof Inclusive ? value >= leftEndpoint.value : value > leftEndpoint.value;
        boolean right = rightEndpoint instanceof Inclusive ? value <= rightEndpoint.value : value < rightEndpoint.value;
        return left && right;
    }

    public Optional<Long> validOrNone(long value){
        return isBetweenBounds(value) ? Optional.of(value) : Optional.empty();
    }
}
