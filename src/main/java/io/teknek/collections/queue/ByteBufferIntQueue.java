package io.teknek.collections.queue;

import io.teknek.collections.evolving.Maybe;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ByteBufferIntQueue {

    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private final int capacity;
    //https://stackoverflow.com/questions/43069910/volatile-and-arrayblockingqueue-and-perhaps-other-concurrent-objects
    private final ByteBuffer underlyingBuffer;
    private int tail;
    private int head;

    public ByteBufferIntQueue(int capacity) {
        this.capacity = capacity;
        underlyingBuffer = ByteBuffer.allocate(capacity * 4);
    }

    public void enqueue(int i) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while(tail == capacity){
                notFull.await();
            }
            underlyingBuffer.putInt(tail * 4, i);
            tail = (tail + 1) % underlyingBuffer.capacity();
            notEmpty.signal();
        } finally{
            lock.unlock();
        }
    }

    public Maybe<Integer> dequeue(Duration toWait) throws InterruptedException {
        long nanos = toWait.toNanos();
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (head == tail){
                if (nanos <= 0L){
                    return Maybe.nothing();
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return Maybe.possibly(underlyingDequeueAndSignal());
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (head == tail){
                notEmpty.await();
            }
            return underlyingDequeueAndSignal();
        } finally {
            lock.unlock();
        }
    }

    private int underlyingDequeueAndSignal(){
        int b = underlyingBuffer.getInt(head * 4);
        head = (head + 1) % underlyingBuffer.capacity();
        notFull.signal();
        return b;
    }

    @Override
    public String toString() {
        return "ByteBufferIntQueue{" +
                "bb=" + underlyingBuffer.capacity() +
                ", tail=" + tail +
                ", head=" + head +
                '}';
    }
}
