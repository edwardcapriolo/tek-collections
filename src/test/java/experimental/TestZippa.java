package experimental;

import io.teknek.collections.evolving.INode;
import io.teknek.collections.evolving.IZipperNode;
import io.teknek.collections.evolving.Zippa;
import io.teknek.collections.evolving.Zipper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* https://grishaev.me/en/clojure-zippers/#part-1-the-basics-of-navigation */
public class TestZippa {

    @Test
    void test(){
        List<Integer> l = Arrays.asList(1, 2, 3);
        INode z1 = Zippa.of(l);
        assertEquals(2 , z1.down().right().node());
    }

    @Test
    void testLeft(){
        List<Integer> l = Arrays.asList(1, 2, 3);
        INode z1 = Zippa.of(l);
        assertEquals(null , z1.down().left());
        //in clojure would be Nil and left of nil is nil (not sure if this is a principal we need to account for
    }

    @Disabled
    void testNested(){
        List<Object> l = Arrays.asList(1, Arrays.asList(2, 3), 4);
        INode z1 = Zippa.of(l);
        assertEquals(3, z1.down().right().down().right().node());
    }

    @Test
    void testNewFunctionalZipper(){
        List<Integer> l = Arrays.asList(1, 2, 3);
        IZipperNode z = Zipper.nestedListZipper().create(l);
        assertEquals(1, z.down().value());
    }

}
