package io.teknek.collections;

public interface ElementBasedWriteAccess<T> extends Mutable<T> {

    /* return true if the list was altered: mimich java.util.List.add  */
    boolean add(T t);

    boolean add(int index, T t);

    default boolean withItem(T t){
        return add(t);
    }
}
