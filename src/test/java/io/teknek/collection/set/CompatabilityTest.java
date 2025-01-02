package io.teknek.collection.set;

import io.teknek.collections.set.NullRetroFitSortedTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.MethodSource;

public class CompatabilityTest {

    @ParameterizedTest
    @MethodSource("nullSupportingSets")
    void test(java.util.Set s){
        s.add(null);
        Assertions.assertTrue(s.contains(null));
    }
    private static Stream<Set> nullSupportingSets(){
        //TreeSet here would fail but not 
        NullRetroFitSortedTest t = new NullRetroFitSortedTest();
        java.util.HashSet s = new HashSet();
       return Stream.of(t, s);
    }
}
