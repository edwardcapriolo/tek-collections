package io.teknek.collections.bounds;

public class Inclusive extends Value {
    public static Inclusive of(long value){
        return new Inclusive(value);
    }
    public static Inclusive of(int value){
        return new Inclusive(value);
    }
    public static Inclusive Inclusive(long value){
        return new Inclusive(value);
    }
    public Inclusive(long value){
        super(value);
    }
}
