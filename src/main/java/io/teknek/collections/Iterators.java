package io.teknek.collections;


import java.util.Iterator;

public class Iterators {

    static class Pair<L,R> {
        private L l;
        private R r;
        public Pair(L l , R r){
            this.l = l;
            this.r = r;
        }
    }

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
