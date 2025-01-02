package io.teknek.collections.evolving;

import java.util.Objects;

/**
 * For collections the null problem is pervasive. Some sets take a null, others do not. Other methods might return a null
 * is that a not found or a real value. here we burrow and old concept 1b mistake null does not exist, we are left
 * with a Union of (Something | None ) but NULL needs to be a real value, ina sorted set it has so sort, in a hashmap
 * it needs to be a key. Thus Maybe is three valued
 * @param <T>
 */
public abstract class Maybe<T> {

    public static <T> Something<T> definately(T t){
        return new Something<>(Objects.requireNonNull(t, "this must be supplied"));
    }
    public static <T> Null<T> nullValue(){ return Null.INSTANCE; }
    public static <T> Nothing<T> nothing() { return Nothing.INSTANCE; }
    public static <T> Maybe<T> possibly(T t){
        if (t == null){
            return Null.INSTANCE;
        } else {
            return new Something<>(t);
        }
    }
    public static <T> Null<T> assuredlyNull(T t){
        if (t == null){
            return Null.INSTANCE;
        }
        else throw new RuntimeException("This must be null");
    }

}

/**
 * The equivilent of None, a databstructure could never hold nothing, but a method could return it to signify the result was
 * not found
 * @param <T>
 */
class Nothing<T> extends Maybe<T> {
    public static final Nothing INSTANCE = new Nothing();

    @Override
    public String toString() {
        return "Nothing{}";
    }
}

/* this is a wrapper for the null for a datastructure that can not hold a null it could then hold this */
class Null<T> extends Maybe<T> {
    public static final Null INSTANCE = new Null();

    @Override
    public int hashCode() {
        return 15;
    }

    /** usually null != null but in the scope of placing a null value in a collection in needs a true equality.
     * We can revisit this late when we flesh out datastructures */
    @Override
    public boolean equals(Object obj) {
        return this == INSTANCE;
    }

    @Override
    public String toString() {
        return "Null{}";
    }
}
