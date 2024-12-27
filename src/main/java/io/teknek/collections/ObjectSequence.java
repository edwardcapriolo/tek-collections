package io.teknek.collections;

public class ObjectSequence<T> implements ImmutableSequence<T> {

	private Object[] t;

	public ObjectSequence(T t){
		this.t = new Object[] {t};
	}

	@Override
	public ImmutableIterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

}