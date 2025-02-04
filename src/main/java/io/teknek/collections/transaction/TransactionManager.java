package io.teknek.collections.transaction;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Modeled after clojure doSync() which allows all-or-none changes to any reference touched by a function/closure
 */

public class TransactionManager {
    private ReadWriteLock lock =  new ReentrantReadWriteLock();

    private final AtomicLong transactionId = new AtomicLong(0);

    public TransactionManager(){

    }
    public <T> GlobalReference<T> register(T t){
        return new GlobalReference<>(this, new AtomicReference<>(t));
    }

    /**
     * This is a very heavy lock affectively locking everything
     * @param callback
     * @return
     */
    public boolean doSync(TransactionCallback callback) {
        final Long thisId = transactionId.getAndIncrement();
        Lock l = lock.writeLock();
        try {
            l.lock();
            Transaction t = new Transaction(this, thisId);
            callback.doSync(t);
            t.potentialEditedObjects().forEach(localReference -> {
                if (localReference.get() != null) {
                    localReference.refer.set(localReference.get());
                }
            });
            return true;
        } catch (Exception e) {
            // m.entrySet().forEach(x -> x.getValue().rollback());
        } finally {
            l.unlock();
        }
        return false;
    }

    public static void main(String [] args){
        TransactionManager transactionManager = new TransactionManager();
        final GlobalReference<Long> count = transactionManager.register(0L);
        TreeMap<String, Long> aMap = new TreeMap<>();
        final GlobalReference<Map> mapReference = transactionManager.register(aMap);

        transactionManager.doSync((txReference)->{
            LocalReference<Long> localReference = txReference.localReference(count);
            localReference.set(7L);

           // MapReference localMap = txReference.trackedMap(mapReference);
           // mapReference.get().getKey(x) ;// locks a key for read  without locking entire map
        } );

    }
}
