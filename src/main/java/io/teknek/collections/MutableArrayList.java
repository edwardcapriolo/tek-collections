package io.teknek.collections;

import java.util.Arrays;
import java.util.List;

public class MutableArrayList<T> extends ArrayList<T> implements RandomWriteAccess<T>{

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
