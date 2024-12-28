package io.teknek.collections.set;

import io.teknek.collections.*;

public class ImmutableHashSet<T> implements Set<T>, ImmutableSequence<T>, DefinitiveSize {

    protected Object[] data;
    private Hashable<T> hasher;
    private final int size;

    public ImmutableHashSet(T ... t){
        this(Object::hashCode, t);

    }
    public ImmutableHashSet(Hashable<T> hasher, T ... t) {
        this.hasher = hasher;
        hasher = Object::hashCode;
        int insertCount = 0;
        int uniqueCount = 0;
        int targetSize = 16;
        do {
            insertCount = 0;
            uniqueCount = 0;
            targetSize = nextPowerOf2(targetSize);
            data = new Object[targetSize];
            for (; insertCount < t.length; insertCount++){
                int index = hasher.hashCode(t[insertCount]);
                if (data[index] == null){
                    data[index] = t[insertCount];
                    uniqueCount++;
                } else if (data[index].equals(t[insertCount])){
                    // duplicate element ignore
                } else {
                    //collision try rehash
                    break;
                }
            }
        } while(insertCount != t.length);
        this.size = uniqueCount;
    }

    @Override
    public boolean contains(T t) {
        int index = Math.abs(hasher.hashCode(t));
        T found = (T) data[index] ;
        if (found == null){
            return false;
        }
        return found.equals(t);
    }

    public static int nextPowerOf2(int num) {
        return num == 1 ? 1 : Integer.highestOneBit(num - 1) * 2;
    }

    @Override
    public BaseIterator<T> iterator() {
        //walk over the array skip null
        //return null;
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }
}
