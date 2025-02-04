package io.teknek.collections.set;

import io.teknek.collections.evolving.Maybe;

import java.util.Comparator;
import java.util.Objects;

/**
 * A comparator to be used in datastructures that support Maybe. The data
 *  must not contain null.
 *
 * @param <T>
 */
public class MaybeComparator<T> {
    public static <T> Comparator<T> of(Comparator<T> original){
        return new Comparator<T>(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == Maybe.nothing() && o2 == Maybe.nothing()){
                    return 0;
                }
                if (o1 == Maybe.nothing()){
                    return -1;
                }
                if (o2 == Maybe.nothing()){
                    return 1;
                }
                return Objects.compare(o1, o2, (Comparator) original);
            }
        };
    }
}
