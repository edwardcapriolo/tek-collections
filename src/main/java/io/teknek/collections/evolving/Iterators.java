package io.teknek.collections.evolving;

import io.teknek.collections.ImmutableIterable;
import io.teknek.collections.ImmutableIterator;
import io.teknek.collections.MutableIterator;

import java.util.Iterator;

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