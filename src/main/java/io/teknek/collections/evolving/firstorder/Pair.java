package io.teknek.collections.evolving.firstorder;

public class Pair<L, R> {
    public static <T,U> Pair<T,U> of(T t, U u){
        return new Pair<>(t,u);
    }

    protected L l;
    protected R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public void setL(L l) {
        this.l = l;
    }
}
