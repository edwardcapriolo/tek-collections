package io.teknek.collections.evolving;

import java.util.Objects;

public class Something<T> extends Maybe<T> {
    final T something;
    public Something(T t){
        this.something = t;
    }

    public T get() {
        return something;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Something<?> something1 = (Something<?>) o;
        return Objects.equals(something, something1.something);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(something);
    }

    @Override
    public String toString() {
        return "Something{" +
                "something=" + something +
                '}';
    }
}
