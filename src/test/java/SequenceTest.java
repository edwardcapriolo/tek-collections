import io.teknek.collections.list.ImmutableArrayList;
import io.teknek.collections.list.MutableArrayList;
import io.teknek.collections.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SequenceTest {

    @Test
    void sequenceEqualsTest(){
        ImmutableArrayList a = new ImmutableArrayList(1, 2, 3);
        MutableArrayList b = new MutableArrayList();
        b.add(1);
        b.add(2);
        b.add(3);
        Assertions.assertTrue( Sequence.equals(a.iterator(), b.iterator() ));
    }
}
