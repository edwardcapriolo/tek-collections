package io.teknek.collections;

public interface BaseBiDiirectionalIIterator<T> extends BaseIterator<T> {
    boolean hasPrevious();
    T previous();
}
