import io.teknek.collections.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArrayList {

    @Test
    void accessTest(){
        ArrayList list = new ArrayList(1,2,3);
        assertEquals(new Integer(2), list.safeElementAt(1).get());
    }

    @Test
    void accessTestOutOfBounds(){
        ArrayList list = new ArrayList(1,2,3);
        assertEquals(Optional.empty(), list.safeElementAt(9));
    }
}
