package io.teknek.collections;

/* This interface marks a class as having a definite size. If we were to iterate it would end. It probably means it is not
* lazy but since we havent spent any time on lazy support yet we cant say for sure !  */
public interface DefinitiveSize {
    int size();
}
