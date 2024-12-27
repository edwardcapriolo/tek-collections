package io.teknek.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

abstract class BaseArrayList<T> implements ElementBasedReadAccess<T> {
    protected Object[] data;

    public BaseArrayList(T ... t){
        data = new Object[t.length];
        System.arraycopy(t, 0, data, 0, t.length);
    }
    @Override
    public T elementAt(int index) {
        return (T) data[index];
    }

    @Override
    public Optional<T> safeElementAt(int index) {
        if (index >= data.length){
            return Optional.empty();
        }
        if (index < 0){
            return Optional.empty();
        }
        return (Optional<T>) Optional.of(data[index]);
    }
}

public class ImmutableArrayList<T> extends BaseArrayList<T> implements ImmutableSequence<T> {

    public ImmutableArrayList(T ... t){
        super(t);
    }

    @Override
    public ImmutableIterator<T> iterator() {
        return new ImmutableIterator<T>() {

            int i;
            @Override
            public boolean hasNext() {
                if (data.length == 0) {
                    return false;
                }
                return i < data.length;
            }

            @Override
            public T next() {

                if (data.length == 0) {
                    throw new NoSuchElementException();
                }
                if (i < data.length) {
                    return (T) data[i++];
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
