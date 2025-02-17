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

    public boolean contains(ClosedInterval other){
        /**
         * Inc(0) Inc(10) 0- 10
         * Excl(0) Ecl(10) 1-9
         */

        boolean leftSideEqualOrInside;
        if (leftEndpoint instanceof Inclusive && other.leftEndpoint instanceof Inclusive){
            leftSideEqualOrInside = leftEndpoint.value <= other.leftEndpoint.value;
        } else if (leftEndpoint instanceof Inclusive && other.leftEndpoint instanceof Exclusive){
            if (leftEndpoint.value == other.leftEndpoint.value){
                leftSideEqualOrInside = true;
            } else {
                leftSideEqualOrInside = other.leftEndpoint.value > leftEndpoint.value;
            }
        } else if (leftEndpoint instanceof Exclusive && other.leftEndpoint instanceof Exclusive){
            leftSideEqualOrInside = leftEndpoint.value <= other.leftEndpoint.value;
        } else if (leftEndpoint instanceof Exclusive && other.leftEndpoint instanceof Inclusive){
            if (leftEndpoint.value == other.leftEndpoint.value){
                leftSideEqualOrInside = false;
            } else {
                leftSideEqualOrInside = leftEndpoint.value < other.leftEndpoint.value;
            }
        } else {
            throw new RuntimeException("unerachable or at least it should be if coded right :)");
        }
        boolean rightSideEqualOrInside;
        if (rightEndpoint instanceof Inclusive && other.rightEndpoint instanceof Inclusive){
            rightSideEqualOrInside = rightEndpoint.value >= other.rightEndpoint.value;
        } else if (rightEndpoint instanceof Inclusive && other.rightEndpoint instanceof Exclusive){
            if (rightEndpoint.value == other.rightEndpoint.value){
                rightSideEqualOrInside = true;
            } else rightSideEqualOrInside = other.rightEndpoint.value < rightEndpoint.value;
        } else if (rightEndpoint instanceof Exclusive && other.rightEndpoint instanceof Exclusive){
            rightSideEqualOrInside = rightEndpoint.value >= other.rightEndpoint.value;
        } else if (rightEndpoint instanceof Exclusive && other.rightEndpoint instanceof Inclusive){
            if (rightEndpoint.value == other.rightEndpoint.value){
                rightSideEqualOrInside = false;
            } else {
                rightSideEqualOrInside = rightEndpoint.value < other.rightEndpoint.value;
            }
        } else {
            throw new RuntimeException("unerachable or at least it should be if coded right :)");
        }
        return leftSideEqualOrInside && rightSideEqualOrInside;

    }

    public Optional<Long> validOrNone(long value){
        return isBetweenBounds(value) ? Optional.of(value) : Optional.empty();
    }
}
