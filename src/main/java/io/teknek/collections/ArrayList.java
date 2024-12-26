package io.teknek.collections;

import java.util.Iterator;
import java.util.Optional;

public class ArrayList<T> implements Sequence<T>, RandomReadAccess<T> {
    protected Object[] data;

    public ArrayList(T ... t){
        data = new Object[t.length];
        System.arraycopy(t, 0, data, 0, t.length);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T elementAt(int index) {
        return (T) data[index];
    }

    @Override
    public Optional<T> safeElementAt(int index) {
        if ( index >= data.length){
            return Optional.empty();
        }
        if ( index < 0){
            return Optional.empty();
        }
        return (Optional<T>) Optional.of(data[index]);
    }
}
