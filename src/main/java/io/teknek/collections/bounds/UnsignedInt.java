package io.teknek.collections.bounds;

import java.util.Optional;

/**
 * The value within must be a positive number in the range of (0 to Integer.MAX_VALUE)
 */
public class UnsignedInt {
    private static final PositiveIntOrZero instance = new PositiveIntOrZero();
    public static Optional<UnsignedInt> unsignedIntOrNone(int value){
        return instance.isBetweenBounds(value) ? Optional.of(new UnsignedInt(value)) : Optional.empty();
    }
    private final int value;
    /* we wish to protect the user from creating one of these directly */
    UnsignedInt(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
