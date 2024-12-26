package io.teknek.collections;

public class MutableArrayList<T> extends ArrayList<T> implements Mutable<T>{

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
}
