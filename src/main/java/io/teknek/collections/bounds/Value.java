package io.teknek.collections.bounds;

import java.util.Objects;

public abstract class Value  {
    public long value;

    public Value(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return value == value1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}


