package io.teknek.collections.evolving;

import io.teknek.collections.list.ImmutableArrayList;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Node extends INode {
    int index;
    Object item;
    public Node(Object item, INode parent, int index){
        super(parent);
        this.item = item;
        this.index = index;
    }

    @Override
    public INode left() {
        INode parent = up();
        Branch parentBranch = (Branch) parent;
        if (index == 0){
            return null;
        } else {
            return parentBranch.children().get(index - 1);
        }
    }

    public INode down(){
        throw new RuntimeException("no down here" + toString());
    }

    @Override
    public INode right() {
        INode parent = up();
        Branch parentBranch = (Branch) parent;
        if (index < parentBranch.children().size() -1){
            return parentBranch.children().get(index + 1);
        } else {
            return null;
        }
    }

    @Override
    public Object node() {
        return item;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", item=" + item +
                '}';
    }
}

class Branch extends INode {
    Collection x;
    public Branch(Collection x, INode parent){
        super(parent);
        this.x = x;
    }

    public List<Node> children(){
        AtomicInteger i = new AtomicInteger();
        Stream<Node> stream = x.stream().map( y -> new Node(y, this, i.getAndIncrement()));
        return stream.collect(Collectors.toList());
    }

    @Override
    public INode left() {
        return null;
    }

    public INode down(){
        List<Node> x = children();
        if (x.size() > 0) {
            return x.get(0);
        } else {
            return null;
        }
    }

    @Override
    public INode right() {
        throw new RuntimeException("Can not right a branch");
    }

    @Override
    public Object node() {
        return x;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "x=" + x +
                '}';
    }
}

public class Zippa {

    public static INode of(Collection l){
        return new Branch(l, null);
    }

}

