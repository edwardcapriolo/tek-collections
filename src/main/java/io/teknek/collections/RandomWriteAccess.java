package io.teknek.collections;

import java.util.Collection;
import java.util.List;

public interface RandomWriteAccess<T> extends Mutable<T> {

    /* return true if the list was altered: mimich java.util.List.add  */
    boolean add(T t);

    boolean add(int index, T t);
}
