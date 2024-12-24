package io.teknek.collections;


import java.util.Iterator;
import java.util.NoSuchElementException;

interface IntIterator {
    int next();
    boolean hasNext();
}

interface IntIterable {
    IntIterator intIterator();
}

public class IntSequence implements Iterable<Integer>, IntIterable, Sequence<Integer> {

    private final int [] data;

	public IntSequence(int x) {
        data = new int[] { x };
    }

	public IntSequence(int [] x) {
        data = new int[x.length];
        System.arraycopy(x, 0, data, 0, x.length);
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

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

            @Override
            public void remove() {

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