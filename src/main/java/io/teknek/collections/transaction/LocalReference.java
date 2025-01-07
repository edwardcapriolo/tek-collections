package io.teknek.collections.transaction;

import java.util.concurrent.atomic.AtomicReference;

public class LocalReference<T> {

    private final AtomicReference<T> localCopy = new AtomicReference<>(null);
    private final Transaction txReference;
    final GlobalReference<T> refer;

    public LocalReference(Transaction txReference, GlobalReference<T> txRef) {
        this.txReference = txReference;
        this.refer = txRef;
    }

    public void set(T t){
        localCopy.set(t);
    }
    public T get(){
        if (localCopy.get() != null){
            return localCopy.get();
        } else {
            return refer.get();
        }
    }
}
