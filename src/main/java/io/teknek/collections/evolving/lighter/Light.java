package io.teknek.collections.evolving.lighter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Light {
    public static <INPUT, RETURN> List<RETURN> tensor(Function<INPUT,RETURN> fx, INPUT[] input){
        List<RETURN> output = new ArrayList<>();
        for (INPUT datum : input){
            output.add(fx.apply(datum));
        }
        return output;
    }
}
