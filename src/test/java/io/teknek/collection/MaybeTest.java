package io.teknek.collection;

import io.teknek.collections.evolving.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaybeTest {

    @Test
    public void nothingShouldNotEqualSomething(){
        Assertions.assertNotEquals(Maybe.nothing(), Maybe.definately(5));
    }


    @Test
    public void somethingShouldEqualItself(){
        Assertions.assertEquals(Maybe.definately(5), Maybe.definately(5));
    }
}
