package io.teknek.collections.list;

import io.teknek.collections.MutableIterator;
import io.teknek.collections.MutableSequence;
import io.teknek.collections.RandomWriteAccess;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class MutableArrayList<T> extends BaseArrayList<T> implements RandomWriteAccess<T>, MutableSequence<T> {

    @Override
    public MutableIterator<T> iterator() {
        return new MutableIterator<T>() {

            @Override
            public void remove() {
                throw new UnsupportedOperationException("do this");
            }

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
    public void insert(T t) {
        Object [] referenceToOriginalList = super.data;
        Object [] newList = new Object[referenceToOriginalList.length + 1];
        for (int j=0; j< referenceToOriginalList.length; j++){
            newList[j] = referenceToOriginalList[j];
        }
        newList[newList.length - 1] = t;
        super.data = newList;
    }

    @Override
    public boolean add(T t) {
        insert(t);
        return true;
    }

    @Override
    public boolean add(int index, T t) {
        if (index< 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        Object [] referenceToOriginalList = super.data;
        Object [] newList = new Object[referenceToOriginalList.length + 1];
        if (index < referenceToOriginalList.length -1){
            int copyUpTo = index;
            for (int i=0; i< copyUpTo; i++){
                newList[i] = referenceToOriginalList[i];
            }
            newList[index] = t;
            for (int j = index +1; j< referenceToOriginalList.length+1; j++){
                newList[j] = referenceToOriginalList[j-1];
            }
            super.data = newList;
            return true;
        } else {
            return add(t);
        }
    }

    @Override
    public String toString() {
        return "MutableArrayList{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
