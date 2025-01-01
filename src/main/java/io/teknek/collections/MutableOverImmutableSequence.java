package io.teknek.collections;

/**
 * Many languages have immutable collections but allow overloading of operators ++ that do not edit the original
 * collection but instead return a new collection. MutableOverImmutable defines these methods with clear names so that
 * the user is not guessing what ++ might do
 * @param <T> type of sequence
 */
public interface MutableOverImmutableSequence<T> {
    ImmutableSequence<T> copyAndInsert(T t);
}
