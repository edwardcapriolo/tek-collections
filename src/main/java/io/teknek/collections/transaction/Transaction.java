package io.teknek.collections.transaction;

import java.util.concurrent.CopyOnWriteArrayList;

public class Transaction {

    final TransactionManager manager;
    private final Long txId;
    //probably could use a different list type
    private CopyOnWriteArrayList<LocalReference> list = new CopyOnWriteArrayList<>();

    public Transaction(TransactionManager manager, Long txId){
        this.manager = manager;
        this.txId = txId;
    }

    public <T> LocalReference<T> localReference(GlobalReference<T> txRef){
        LocalReference<T> x = new LocalReference<>(this, txRef);
        list.add(x);
        return x;
    }

    public CopyOnWriteArrayList<LocalReference> potentialEditedObjects(){
        return list;
    }
}
