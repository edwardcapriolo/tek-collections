package io.teknek.collections.evolving.firstorder;



import io.teknek.collections.Set;
import io.teknek.collections.set.TreeSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/** I dont have time to come up with a great name for this class. Lets see where it goes */
public class Helpers {
    public <T> Stream<Pair<Long,T>> enumerateAsStream(Iterable<T> input){
        AtomicLong l = new AtomicLong();
        return StreamSupport.stream(input.spliterator(), false).map(x -> Pair.of(l.getAndIncrement(), x));
    }

    //set, list, sorted
    public <T> Set<T> set(Iterable<T> input){
        //TODO figure this better
        return new TreeSet<T>((Comparator<T>) Comparator.naturalOrder(), (T) input.iterator());

    }

}
