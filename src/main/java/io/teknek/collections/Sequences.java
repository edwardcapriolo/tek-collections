package io.teknek.collections;

import io.teknek.collections.primitive.IntSequence;
import io.teknek.collections.sequence.ObjectSequence;

import java.util.Collection;

public class Sequences {

	public static ImmutableSequence of(Object o) {
		return new ObjectSequence(o);
	}
	public static <T> ImmutableSequence<T> ofTyped(T t) {
		return new ObjectSequence<T>(t);
	}
	public static ImmutableSequence<Integer> of(int [] x){
		return new IntSequence(x);
	}

	public static <T> Sequence<T> of(Collection<T> t){
		return null;
	}
}