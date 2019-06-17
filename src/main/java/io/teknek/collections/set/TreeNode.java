package io.teknek.collections.set;

public class TreeNode<T> {
    Object datum;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T datum, TreeNode<T> left, TreeNode<T> right){
        this.datum = TreeSet.nullToNothing(datum);
        this.left = left;
        this.right = right;
    }

    public T asT(){
        return (T) datum;
    }
}
