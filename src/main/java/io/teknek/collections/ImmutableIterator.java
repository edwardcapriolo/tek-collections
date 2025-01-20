package io.teknek.collections;

/**
 * Iterators implementing must not allow mutating the struture
 *
 * @param <E>
 */
public interface ImmutableIterator<E> extends BaseIterator<E> {

    static <X> ImmutableIterable<X> empty(){
        return () -> new ImmutableIterator<X>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public X next() {
                //Right thing here?
                return null;
            }
        };
    }
}
