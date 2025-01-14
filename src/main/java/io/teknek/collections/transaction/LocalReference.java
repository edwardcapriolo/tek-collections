package io.teknek.collections.transaction;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <ul>
 * <li>A "dirty read" is a situation where a transaction reads data that has not been committed.</li>
 * <li>A "non repeatable read" read the same row twice and get different results</li>
 * </ul>
 *
 * <ul>
 * <li>Read uncommitted: allows dirty reads </li>
 * <li>Read committed: only committed data is readable</li>
 * </ul>
 * With the design of LocalReference the transaction can only see Committed data.
 * Changes are visible inside the transaction, invisible to other transactions, and staged for commit
 * @param <T>
 */
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
