package io.teknek.collections.list;

import io.teknek.collections.DefinitiveSize;
import io.teknek.collections.ElementBasedReadAccess;
import io.teknek.collections.ImmutableIterator;
import io.teknek.collections.ImmutableSequence;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

abstract class BaseArrayList<T> implements ElementBasedReadAccess<T>, DefinitiveSize {
    protected Object[] data;

    public BaseArrayList(T ... t){
        data = new Object[t.length];
        System.arraycopy(t, 0, data, 0, t.length);
    }

    public BaseArrayList(MutableArrayList copy){
        data = new Object[copy.rawData().length];
        for (int i=0;i< data.length;i++){
            data[i] =copy.rawData()[i];
        }
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

    @Override
    public int size(){
        return data.length;
    }

    public Object[] rawData(){
        return data;
    }
}

public class ImmutableArrayList<T> extends BaseArrayList<T> implements ImmutableSequence<T> {

    public ImmutableArrayList(T ... t){
        super(t);
    }

    public ImmutableArrayList(MutableArrayList copy){
        super(copy);
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
