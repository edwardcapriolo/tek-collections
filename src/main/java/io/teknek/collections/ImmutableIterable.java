package io.teknek.collections;

interface BaseIterable<T> {
    BaseIterator<T> iterator();
}
public interface ImmutableIterable<T> extends BaseIterable<T>{
    ImmutableIterator<T> iterator();
}

interface MutableIterable<T> extends BaseIterable<T> {
    MutableIterator<T> iterator();
}