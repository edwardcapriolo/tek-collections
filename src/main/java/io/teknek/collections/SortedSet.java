package io.teknek.collections;

import java.util.Optional;

public interface SortedSet<T> {
    T first();
    T last();
    Optional<BaseBiDiirectionalIIterator<T>> iteratorFrom(T from);
    Optional<T> after(T element);
    Optional<T> before(T element);
}
