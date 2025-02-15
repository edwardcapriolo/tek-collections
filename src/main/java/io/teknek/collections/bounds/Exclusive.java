package io.teknek.collections.bounds;

public class Exclusive extends Value {
    public static Exclusive Exclusive(long value){
        return new Exclusive(value);
    }
    public static Exclusive of(long value){
        return new Exclusive(value);
    }
    public Exclusive(long value){
        super(value);
    }

}
