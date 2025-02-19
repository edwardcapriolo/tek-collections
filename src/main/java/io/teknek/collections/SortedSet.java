package io.teknek.collections;

import io.teknek.collections.evolving.Maybe;

import java.util.Optional;

public interface SortedSet<T> extends Sorted<T> {

    T first();
    /* I have some misgivings around returning a maybe type. I see two separate uses a wrapper to store null and api
    * that needs to say missing. For now we can roll with this. but I will ponder it. */
    Maybe<T> firstOrNothing();
    T last();
    Maybe<T> lastOrNothing();

    Maybe<T> after(T element);
    Maybe<T> before(T element);
}
