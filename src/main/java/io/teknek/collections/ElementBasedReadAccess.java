package io.teknek.collections;

import java.util.Optional;

public interface ElementBasedReadAccess<T> {
    public T elementAt(int index);

    /**
     * @param index element in a singular dimension index to look up
     * @return an optional Some(index) if found else None
     */
    public Optional<T> safeElementAt(int index);
}
