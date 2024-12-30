package io.teknek.collections.evolving;

import io.teknek.collections.BaseIterator;
import io.teknek.collections.ImmutableSequence;
import io.teknek.collections.MutableSequence;
import io.teknek.collections.list.MutableArrayList;

import java.util.Iterator;

public class MutableOverImmutableSequenceFacade<T> implements MutableOverImmutableSequence<T> {

    private ImmutableSequence<T> data;
    MutableOverImmutableSequenceFacade(ImmutableSequence<T> mutable){
        this.data = mutable;
    }
    @Override
    public ImmutableSequence<T> insertReturningNew(T t) {
        MutableArrayList<T> stage = new MutableArrayList<>();
        for (Iterator<T> iter = Iterators.toJavaIterator(stage.iterator()); iter.hasNext(); ) {
            T it = iter.next();
            stage.add(it);
        }
        stage.add(t);
        return of(stage);
    }

    private static <X> ImmutableSequence<X> of(MutableSequence<X> input){
        return input::iterator;
    }
}
