package io.teknek.collections.sequence;

import io.teknek.collections.*;
import io.teknek.collections.list.ImmutableArrayList;
import io.teknek.collections.list.MutableArrayList;

import java.util.Iterator;

/** this will be a nice testbed once we get zippers sorted */
public class MutableOverImmutableSequenceFacade<T> implements MutableOverImmutableSequence<T> {

    private final ImmutableSequence<T> data;

    public MutableOverImmutableSequenceFacade(ImmutableSequence<T> mutable){
        this.data = mutable;
    }

    @Override
    public MutableOverImmutableSequence<T> copyAndInsert(T t) {
        MutableArrayList<T> stage = new MutableArrayList<>();
        for (Iterator<T> iter = Iterators.toJavaIterator(stage.iterator()); iter.hasNext(); ) {
            T it = iter.next();
            stage.add(it);
        }
        stage.add(t);

        return new MutableOverImmutableSequenceFacade<>( new ImmutableArrayList<T>(stage));
    }



    @Override
    public String toString() {
        return "MutableOverImmutableSequenceFacade{" +
                "data=" + data +
                '}';
    }
}
