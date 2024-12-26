import io.teknek.collections.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TreeSetTest {

    @Test
    void test(){
        TreeSet<Integer> t = new TreeSet<>( (x,y) -> {
            return x.compareTo(y);
        }, 9,4,5,6,5);
        Assertions.assertFalse(t.contains(1));
        Assertions.assertTrue(t.contains(5));
    }
}
