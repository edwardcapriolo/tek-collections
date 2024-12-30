package io.teknek.collections.evolving;

class ReforatorDatum<T> {
    T element;
    boolean done;

}
public interface Referator<T> {

    ReforatorDatum<T> advance();

}
