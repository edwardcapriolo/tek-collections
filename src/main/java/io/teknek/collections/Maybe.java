package io.teknek.collections;

import java.util.Objects;

public abstract class Maybe<T> {
    public static <T> Something<T> definately(T t){
        return new Something<>(Objects.requireNonNull(t));
    }
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

    public abstract T get();
}

class Something<T> extends Maybe<T> {
    private final T t;
    Something(T t){
        this.t = t;
    }
    T something;

    @Override
    public T get() {
        return something;
    }

}

class Nothing<T> extends Maybe<T> {
    public static final Nothing INSTANCE = new Nothing();

    @Override
    public T get() {
        return null;
    }
}

class Null<T> extends Maybe<T> {
    public static final Null INSTANCE = new Null();

    @Override
    public T get() {
        return null;
    }
}
