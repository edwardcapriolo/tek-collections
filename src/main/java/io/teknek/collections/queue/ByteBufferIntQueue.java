package io.teknek.collections.queue;

import java.nio.ByteBuffer;

public class ByteBufferIntQueue {

        private ByteBuffer bb;
        int tail;
        int head;

        public ByteBufferIntQueue(int capacity){
            bb = ByteBuffer.allocate(capacity * 4);
        }

        public void enque(int i){
            //check that the queue is not full
            bb.putInt(tail* 4, i);
            tail = (tail + 1);
        }

        public int dequeue(){
            int b = bb.getInt(head *  4);
            head = (head + 1) ;
            return b;
        }

}
