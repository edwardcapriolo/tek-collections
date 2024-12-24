package io.teknek.collections;

import java.util.Iterator;

public class ObjectSequence<T> implements Iterable<T>, Sequence<T> {

	private Object[] t;
	public ObjectSequence(T t){
		this.t = new Object[] {t};
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

}