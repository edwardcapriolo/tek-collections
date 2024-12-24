package io.teknek.collections;

public class Sequences {

	public static Sequence of(Object o) {
		return new ObjectSequence(o);
	}
	public static <T> Sequence<T> ofTyped(T t) {
		return new ObjectSequence<T>(t);
	}
	public static Sequence<Integer> of(int [] x){
		return new IntSequence(x);
	}

}