package experimental;

import io.teknek.collections.ImmutableSequence;
import io.teknek.collections.MutableOverImmutableSequence;
import io.teknek.collections.list.ImmutableArrayList;
import io.teknek.collections.sequence.MutableOverImmutableSequenceFacade;
import org.junit.jupiter.api.Test;

import java.util.IdentityHashMap;
import java.util.concurrent.Callable;

public class TransactionAttempt {

    @Test
    void test(){
        MutableOverImmutableSequence<Integer> x = new MutableOverImmutableSequenceFacade<>( new ImmutableArrayList(1, 2, 3));
        MutableOverImmutableSequence<Integer> y = new MutableOverImmutableSequenceFacade<>( new ImmutableArrayList(4, 5, 6));

        TransactionManager tm = TransactionManager.beginReadCommitted();
        final TrackedSequence x1 = tm.addSequence(x);
        final TrackedSequence x2 = tm.addSequence(y);
        tm.addSequence(y);
        tm.executeInTransaction( () -> {
                    MutableOverImmutableSequence<Integer> xMutated = x.copyAndInsert(5);
                    MutableOverImmutableSequence<Integer>  yMutated = x.copyAndInsert(2);
                    return null;
                }
        );
    }

}

class TransactionManager {

    IdentityHashMap x = new IdentityHashMap();
    public static TransactionManager beginReadCommitted(){
        TransactionManager tm = new TransactionManager();
        return tm;
    }
    public <T> TrackedSequence<T> addSequence(MutableOverImmutableSequence s){
        TrackedSequence<T> t = new TrackedSequence<>(null, s);
        x.put(t, null);
        return t;
    }
    public void executeInTransaction(Callable<Void> block){
        try {
            block.call();
        } catch (Exception ex){}
    }
}

class TrackedSequence<T> implements MutableOverImmutableSequence<T> {

    private final TrackedSequence<T> parent;
    private final MutableOverImmutableSequence<T> underlying;

    TrackedSequence(TrackedSequence<T> parent, MutableOverImmutableSequence<T> t){
        this.parent = parent;
        this.underlying = t;
    }

    @Override
    public MutableOverImmutableSequence<T> copyAndInsert(T t) {
        //todo should we hold cool read and write locks over "row" t?
        MutableOverImmutableSequence<T> kneww = underlying.copyAndInsert(t);
        return new TrackedSequence<>(this, kneww);
    }
}
