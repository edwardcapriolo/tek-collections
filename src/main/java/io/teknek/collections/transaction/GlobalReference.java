package io.teknek.collections.transaction;

import java.util.concurrent.atomic.AtomicReference;

public class GlobalReference<T> {

    private final AtomicReference<T> originalReference;

    GlobalReference(TransactionManager parent, AtomicReference<T> managed){
        this.originalReference = managed;
    }

    void set(T newReference){
        originalReference.set(newReference);
    }

    T get(){
        return originalReference.get();
    }

    /* not really unsafe but ignores locks. will think about a better name/pattern */
    public T getUnsafe(){
        return originalReference.get();
    }
}
