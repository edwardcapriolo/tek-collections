package io.teknek.collection.queue;

import io.teknek.collections.bounds.UnsignedInt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> {

    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock. newCondition();
    final Condition notEmpty = lock. newCondition();

    java.util.concurrent.ArrayBlockingQueue<T> t;
    private Object[] data;
    public ArrayBlockingQueue(UnsignedInt size){
        data = new Object[size.getValue()];
    }
}


