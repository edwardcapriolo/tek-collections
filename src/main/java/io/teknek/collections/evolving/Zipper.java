package io.teknek.collections.evolving;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ZipperNode implements IZipperNode {

    private Object o;
    ZipperNode(Object o){
        this.o = o;
    }
    @Override
    public IZipperNode left() {
        return null;
    }

    @Override
    public IZipperNode right() {
        return null;
    }

    @Override
    public IZipperNode down() {
        return null;
    }

    @Override
    public IZipperNode up() {
        return null;
    }

    public Object value(){
        return o;
    }
}

class ZipperBranch implements IZipperNode {
    List l;
    ZipperBranch(List l){
        this.l = l;
    }

    @Override
    public IZipperNode left() {
        return null;
    }

    @Override
    public IZipperNode right() {
        return null;
    }

    @Override
    public IZipperNode down() {
        if (l.size() >0){
            return (IZipperNode) l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public IZipperNode up() {
        return null;
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public String toString() {
        return "ZipperBranch{" +
                "l=" + l +
                '}';
    }
}

interface HasChildren{
    public List getChildren(Object input);
}
public class Zipper {
    Predicate<Object> isBranch;
    HasChildren hasChildren;
    Zipper(Predicate<Object> isBranch, HasChildren hasChildren){
        this.isBranch = isBranch;
        this.hasChildren = hasChildren;
    }
    public static Zipper nestedListZipper(){
        Predicate<Object> isList = o -> o instanceof List;
        HasChildren hasC = o -> {
            List l = (List) o;
            return l;
        };
        return new Zipper(isList, hasC);
    };

    public IZipperNode create(Object o){
        boolean x = isBranch.test(0);
        if (x) {
            List l = hasChildren.getChildren(o);
            System.out.println(l);
            //List k = l.stream().map( y -> { return create(y); }).collect(Collectors.toList());
            Stream<IZipperNode> stream =l.stream().map(this::create);

            return new ZipperBranch(stream.collect(Collectors.toList()));
        } else {
            return new ZipperNode(o);
        }

    }
}
