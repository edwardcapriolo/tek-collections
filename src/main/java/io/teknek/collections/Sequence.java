package io.teknek.collections;


import java.util.Objects;

public interface Sequence<T> extends BaseIterable<T> {

   default boolean equals(Sequence<T> other) {
      return equals(this.iterator(), other.iterator());
   }

   static <T, S> boolean equals(BaseIterator<T> a, BaseIterator<S> b) {
      while (a.hasNext() && b.hasNext()) {
         T left = a.next();
         S right = b.next();
         boolean equals = Objects.equals(left, right);
         if (!equals){
            return false;
         }
      }
       // one of the iterators has more elements than the other
       return !a.hasNext() && !b.hasNext();
   }
}

interface ImmutableSequence<T> extends Sequence<T> {

}

interface MutableSequence<T> extends Sequence<T> {

}