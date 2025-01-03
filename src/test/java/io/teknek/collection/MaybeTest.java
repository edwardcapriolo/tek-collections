package io.teknek.collection;

import io.teknek.collections.evolving.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaybeTest {

    @Test
    public void nothingShouldNotEqualSomething(){
        Assertions.assertNotEquals(Maybe.nothing(), Maybe.definitely(5));
    }


    @Test
    public void somethingShouldEqualItself(){
        Assertions.assertEquals(Maybe.definitely(5), Maybe.definitely(5));
    }
}
