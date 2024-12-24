
import io.teknek.collections.Sequence;
import io.teknek.collections.Sequences;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObjectSequenceTest {

	@Test
	void primitiveIteratorTest(){
		Sequence<Integer> k = Sequences.of( new int[] { 1,2,3});
		assertEquals(1, k.iterator().next());
	}
}
