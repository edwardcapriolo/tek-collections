package io.teknek.collections;

import io.teknek.collections.primitive.IntSequence;
import io.teknek.collections.sequence.ObjectSequence;

import java.util.ArrayList;
import java.util.Collection;

public class Sequences {


	public static ImmutableSequence<Integer> of(int [] x){
		return new IntSequence(x);
	}

}