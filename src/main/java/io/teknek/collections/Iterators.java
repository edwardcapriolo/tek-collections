package io.teknek.collections;


import io.teknek.collections.evolving.firstorder.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Iterators {

    public static <T> java.util.Iterator<T> toJavaIterator(ImmutableIterator<T> immutableIterable){
         return new java.util.Iterator<T>(){

             @Override
             public boolean hasNext() {
                 return immutableIterable.hasNext();
             }

             @Override
             public T next() {
                 return immutableIterable.next();
             }
         };
     }

     public static <T> java.util.Iterator<Pair<Integer, T>> iteratorWithIndex(Iterator<T> iterator){
        int i =0;
         return new java.util.Iterator<Pair<Integer,T>>(){

             @Override
             public boolean hasNext() {
                 return iterator.hasNext();
             }

             @Override
             public Pair<Integer,T> next() {
                 return new Pair<>(i, iterator.next());
             }
         };
     }

     public static <T> List<T> enumerate(Iterable<T> iterable){

        Iterator<T> items = iterable.iterator();
        Iterator<Pair<Integer, T>> x = iteratorWithIndex(items);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(items,0), false)
                .collect(Collectors.toList());
     }

    public static <T> java.util.Iterator<T> toJavaIterator(MutableIterator<T> mutableIterator){
        return new java.util.Iterator<T>(){

            @Override
            public boolean hasNext() {
                return mutableIterator.hasNext();
            }

            @Override
            public T next() {
                return mutableIterator.next();
            }

            @Override
            public void remove() {
                mutableIterator.remove();
            }
        };
    }
}
