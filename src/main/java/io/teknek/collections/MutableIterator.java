package io.teknek.collections;

/**
 * Iterators implementing allow mutating the underlying stucture
 *
 * @param <E>
 */
public interface MutableIterator<E> extends BaseIterator<E> {
    void remove();
}
