package io.teknek.collections.evolving;

public abstract class INode {
    //until we make this fully functional and lazy we need quick hacks for right and left

    private final INode parent;
    public INode(INode parent){
        this.parent = parent;
    }
    public INode up(){
        return parent;
    }

    public abstract INode left();
    public abstract INode down();
    public abstract INode right();
    public abstract Object node();
}
