package io.teknek.collections.transaction;

public interface TransactionCallback {
    void doSync(Transaction txReference);
}
