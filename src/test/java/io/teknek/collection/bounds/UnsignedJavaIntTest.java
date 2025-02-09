package io.teknek.collection.bounds;

import io.teknek.collections.bounds.UnsignedInt;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnsignedJavaIntTest {
    @Test
    void simpleTest(){
        assertEquals(5, UnsignedInt.unsignedIntOrNone(5).get().getValue());
        assertEquals(Optional.empty(), UnsignedInt.unsignedIntOrNone(-2));
    }
}
