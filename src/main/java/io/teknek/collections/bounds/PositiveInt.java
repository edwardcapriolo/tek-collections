package io.teknek.collections.bounds;



class PositiveInt extends ClosedInterval {

    public PositiveInt() {
        super(Inclusive.of(1), Inclusive.of(Integer.MAX_VALUE));
    }
}
