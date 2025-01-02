package io.teknek.collections;

import java.util.Optional;

public interface SortedSet<T> {
   //Todo these will confuse the null semantics we need answer here
    T first();
    T last();
    Optional<BaseBiDiirectionalIIterator<T>> iteratorFrom(T from);
    Optional<T> after(T element);
    Optional<T> before(T element);
}
