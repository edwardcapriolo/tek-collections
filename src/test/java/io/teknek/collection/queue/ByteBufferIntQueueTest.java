package io.teknek.collection.queue;

import io.teknek.collections.queue.ByteBufferIntQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteBufferIntQueueTest {

    @Test
    public void test(){
        ByteBufferIntQueue queue = new ByteBufferIntQueue(10);
        queue.enque(5);
        queue.enque(6);
        Assertions.assertEquals(5, queue.dequeue());
        Assertions.assertEquals(6, queue.dequeue());
    }
}
