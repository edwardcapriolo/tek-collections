package io.teknek.collection.sequence;

import io.teknek.collections.ImmutableSequence;
import io.teknek.collections.Sequence;
import io.teknek.collections.Sequences;
import io.teknek.collections.list.ImmutableArrayList;
import io.teknek.collections.sequence.MutableOverImmutableSequenceFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestMutableOverImmutable {

    @Test
    void addOriginalUnalterted(){
        ImmutableArrayList a = new ImmutableArrayList(1,2,3);
        MutableOverImmutableSequenceFacade<Integer> f = new MutableOverImmutableSequenceFacade(a);
        ImmutableSequence<Integer> x = f.copyAndInsert(4);
System.out.println(a);
        System.out.println(x);
    }
}
