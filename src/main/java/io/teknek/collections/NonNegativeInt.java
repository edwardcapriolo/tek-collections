package io.teknek.collections;

public class NonNegativeInt {
    final int underlying;
    public NonNegativeInt(int input){
        if (input <0) {
            throw new ArrayIndexOutOfBoundsException("input was " + input);
        }
        underlying = input;
    }

}
