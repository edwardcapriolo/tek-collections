package io.teknek.collection.queue;

import io.teknek.collections.evolving.Maybe;
import io.teknek.collections.queue.ByteBufferIntQueue;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBufferIntQueueTest {

    @Test
    public void test() throws InterruptedException {
        ByteBufferIntQueue queue = new ByteBufferIntQueue(10);
        queue.enqueue(5);
        queue.enqueue(6);
        assertEquals(5, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(Maybe.nothing(), queue.dequeue(Duration.ofMillis(1)));
        //This will block forever without a second thread
        //IllegalArgumentException expected = Assertions.assertThrows(IllegalArgumentException.class, queue::dequeue);
    }
}
