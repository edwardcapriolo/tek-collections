import io.teknek.collections.ImmutableArrayList;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArrayList {

    @Test
    void accessTest(){
        ImmutableArrayList list = new ImmutableArrayList(1,2,3);
        assertEquals(new Integer(2), list.safeElementAt(1).get());
    }

    @Test
    void accessTestOutOfBounds(){
        ImmutableArrayList list = new ImmutableArrayList(1,2,3);
        assertEquals(Optional.empty(), list.safeElementAt(9));
    }
}
