package io.teknek.collections.evolving;

import io.teknek.collections.ImmutableIterable;
import io.teknek.collections.ImmutableIterator;

import java.util.function.Function;
import java.util.function.Predicate;

public class LazyStructures {

    public static ImmutableIterable<Integer> range(int from, int to, int increment){
        return new IntRange(from, to, increment);
    }

    public static<T> ImmutableIterable<T> range(T from, Function<T,T> increment, Predicate<T> goOn){
        return new TRange<>(from, increment, goOn);
    }
}

class TRange<T> implements ImmutableIterable<T> {

    T from;
    Function<T,T> modify;
    Predicate<T> goOn;
    private volatile T current;

    public TRange(T from, Function<T,T> modify, Predicate<T> goOn){
        this.from= from;
        this.modify = modify;
        this.goOn = goOn;
        current = from;
    }
    @Override
    public ImmutableIterator<T> iterator() {
        return new ImmutableIterator<T>() {

            @Override
            public boolean hasNext() {
                return goOn.test(current);
            }

            @Override
            public T next() {
                T result = modify.apply(current);
                current = result;
                return result;
            }
        };
    }
}

class IntRange implements ImmutableIterable<Integer> {

    private final int from;
    private final int to;
    private final int increment;
    private volatile int current;

    IntRange(int from, int to, int increment){
        this.from = from;
        this.to = to;
        this.increment = increment;
        current = from;
    }
    @Override
    public ImmutableIterator<Integer> iterator() {

        return new ImmutableIterator<Integer>() {
            @Override
            public boolean hasNext() {
                return current + increment < to;
            }

            @Override
            public Integer next() {
                int ret = current;
                current = current + increment;
                return ret;
            }
        };
    }
}