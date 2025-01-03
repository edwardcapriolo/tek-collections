package io.teknek.collections.set;

import io.teknek.collections.evolving.Maybe;
import io.teknek.collections.evolving.Something;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class NullRetroFitSortedTest<T> implements SortedSet<T>{
    SortedSet<Maybe<T>> underlying;

    public NullRetroFitSortedTest(){
        underlying = new TreeSet<>(new Comparator<Maybe<T>>() {
            @Override
            public int compare(Maybe<T> o1, Maybe<T> o2) {
                if (o1 == o2){
                    return 0;
                }
                if (o1 == Maybe.nullValue()) {
                    return -1;
                }
                if (o2 == Maybe.nullValue()){
                    return 1;
                }
                Comparable c1 = (Comparable<T>) ((Something) o1).get();
                Comparable c2 = (Comparable<T>) ((Something) o2).get();
                return c1.compareTo(c2);
            }
        });
    }

    @Override
    public Comparator<? super T> comparator() {
        return (Comparator<? super T>) underlying.comparator();
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return null;
    }

    @Override
    public T first() {
        Maybe<T> l = underlying.first();
        if (Maybe.nullValue() == l){
            return null;
        } else {
            Something<T> s = (Something<T>) l;
            return s.get();
        }
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public int size() {
        return underlying.size();
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return underlying.contains(Maybe.possibly(o));
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return underlying.add(Maybe.possibly(t));
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        underlying.clear();
    }
}
