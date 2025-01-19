package io.teknek.collections;

import java.util.Optional;

/** underlying collection is sorted */
public interface Sorted<T> {
    Optional<BaseBiDiirectionalIIterator<T>> iteratorFrom(T startingPosition);
}
