package io.teknek.collection;

import io.teknek.collections.transaction.GlobalReference;
import io.teknek.collections.transaction.LocalReference;
import io.teknek.collections.transaction.TransactionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

import java.util.Arrays;

public class TransactionTest {

    @Test
    public void aTest(){
        TransactionManager transactionManager = new TransactionManager();
        final GlobalReference<Long> count = transactionManager.register(0L);
        final GlobalReference<List<Integer>> all = transactionManager.register(Arrays.asList(1, 2, 3));

        transactionManager.doSync((txReference)->{
            LocalReference<Long> localReference = txReference.localReference(count);
            localReference.set(7L);
            txReference.localReference(all).set(Arrays.asList(3, 4));
        } );

        Assertions.assertEquals(7l, count.getUnsafe());
        Assertions.assertEquals(Arrays.asList(3, 4), all.getUnsafe());

        transactionManager.doSync((txReference)->{
            LocalReference<Long> localReference = txReference.localReference(count);
            localReference.set(9999L);
            if (true) {
                throw new RuntimeException("you fail");
            }
            txReference.localReference(all).set(Arrays.asList(9, 10));
        } );

        Assertions.assertEquals(7l, count.getUnsafe());
        Assertions.assertEquals(Arrays.asList(3, 4), all.getUnsafe());

    }
}
