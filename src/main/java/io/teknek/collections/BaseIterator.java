package io.teknek.collections;

/**
 * The would be parent of java.util.Iterator. The optional remove() method is where the "bleeding" starts with the
 * collection framework.
 * @param <E> type of element iterated
 */
public interface BaseIterator<E> {
        boolean hasNext();
        E next();
}

