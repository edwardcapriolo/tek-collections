import io.teknek.collections.MutableArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MutableArrayListTest {

    @Test
    void mutableArrayList(){
        MutableArrayList<Integer> a = new MutableArrayList<>();
        a.insert(5);
        Assertions.assertEquals(5, a.elementAt(0));
    }
}
