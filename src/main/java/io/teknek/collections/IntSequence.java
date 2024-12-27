package io.teknek.collections;

import java.util.NoSuchElementException;
import java.util.Optional;

interface IntIterator {
    int next();
    boolean hasNext();
}

interface IntIterable {
    IntIterator intIterator();
}

interface ElementBasedReadAccess<T> {
    public T elementAt(int index);

    /**
     *
     * @param index element in a singular dimension index to look up
     * @return an optional Some(index) if found else None
     */
    public Optional<T> safeElementAt(int index);
}


public class IntSequence implements ImmutableIterable<Integer>, IntIterable, ImmutableSequence<Integer> {

    private final int [] data;

	public IntSequence(int x) {
        data = new int[] { x };
    }

	public IntSequence(int [] x) {
        data = new int[x.length];
        System.arraycopy(x, 0, data, 0, x.length);
    }

    @Override
    public ImmutableIterator<Integer> iterator() {
        return new ImmutableIterator<Integer>() {

            int i;
            @Override
            public boolean hasNext() {
                if (data.length == 0) {
                    return false;
                }
                return i < data.length;
            }

            @Override
            public Integer next() {

                if (data.length == 0) {
                    throw new NoSuchElementException();
                }
                if (i < data.length) {
                    return data[i++];
                }
                throw new NoSuchElementException();
            }
        };
    }
    @Override
    public IntIterator intIterator() {
        return new IntIterator() {
            int i;
            @Override
            public int next() {
                if (data.length == 0) {
                    throw new NoSuchElementException();
                }
                if (i < data.length) {
                    return data[i++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public boolean hasNext() {
                if (data.length == 0) {
                    return false;
                }
                return i < data.length;
            }
        };
    }

}

