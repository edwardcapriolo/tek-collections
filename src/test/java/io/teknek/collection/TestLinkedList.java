package io.teknek.collection;

import io.teknek.collections.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLinkedList {
    @Test
    void simpleInanTest(){
        LinkedList l = new LinkedList();
        l.addToFront(8);
        l.addToFront(1);
        l.printIt();
        Assertions.assertFalse(l.search(5));
        //Assertions.assertTrue(l.search(4));

        Assertions.assertEquals(2, l.size());

        boolean ianTest = false;
        while(ianTest){
          l.addToFront(5);
          if (l.size() % 10000==0) {
              System.out.println(l.size());
          }
        }
        Assertions.assertEquals(true,l.search(8));
    }
}
