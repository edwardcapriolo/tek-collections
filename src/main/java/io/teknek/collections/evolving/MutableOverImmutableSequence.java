package io.teknek.collections.evolving;

import io.teknek.collections.ImmutableSequence;

public interface MutableOverImmutableSequence<T> {
    ImmutableSequence<T> insertReturningNew(T t);
}
