package io.teknek.collections;

public interface Mutable<T> {
    /** we dont want to check if the element exists the implementation might but we care not. Image a blind write dataabase */
    void insert(T t);

}
