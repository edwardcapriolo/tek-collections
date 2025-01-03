package io.teknek.collections.set;

import io.teknek.collections.BaseBiDiirectionalIIterator;
import io.teknek.collections.Set;

import java.util.Comparator;
import java.util.Optional;

import io.teknek.collections.SortedSet;
import io.teknek.collections.evolving.Maybe;

public class TreeSet<T> implements Set<T>, SortedSet<T> {

    protected TreeNode<T> root;
    protected Comparator<T> comparator;

    public TreeSet(Comparator<T> comparator, T... elements) {
        this.comparator = comparator;
        SortedSet s;

        for (T t : elements) {
            if (root == null) {
                root = new TreeNode<>(t, null, null);
                continue;
            }
            addElement(root, t);
        }
    }

    private void addElement(TreeNode<T> root, T t) {
        int result = comparator.compare(root.datum, t);
        if (result == 0) {
            //consider if this method should return boolean
            return;
        } else if (result > 0) {
            if (root.left == null) {
                root.left = new TreeNode<>(t, null, null);
            } else {
                addElement(root.left, t);
            }
        } else /*if (result < 0) */ {
            if (root.right == null) {
                root.right = new TreeNode<>(t, null, null);
            } else {
                addElement(root.right, t);
            }
        }
    }

    @Override
    public boolean contains(T t) {
        return contains(root, t);
    }

    private boolean contains(TreeNode<T> currentNode, T searchingFor) {
        if (currentNode == null) {
            return false;
        }
        int result = comparator.compare(currentNode.datum, searchingFor);
        if (result == 0) {
            return true;
        } else if (result > 0) {
            return contains(currentNode.left, searchingFor);
        } else {
            return contains(currentNode.right, searchingFor);
        }
    }

    @Override
    public T first() {
        if (root == null){
            return null;
        }
        return lowest(root);
    }

    @Override
    public Maybe<T> firstOrNothing() {
        return null;
    }


    private T lowest(TreeNode<T> node){
        if (node.left == null){
            return node.datum;
        } else {
            return lowest(node.left);
        }
    }

    @Override
    public T last() {
        if (root == null){
            return null;
        }
        return highest(root);
    }

    @Override
    public Maybe<T> lastOrNothing() {
        if (root == null){
            return Maybe.nothing();
        } else {
            return Maybe.possibly(highest(root));
        }
    }

    private T highest(TreeNode<T> node){
        if (node.right == null){
            return node.datum;
        } else {
            return highest(node.right);
        }
    }

    @Override
    public Optional<BaseBiDiirectionalIIterator<T>> iteratorFrom(T from) {
        return Optional.empty();
    }

    @Override
    public Optional<T> after(T element) {
        return Optional.empty();
    }

    @Override
    public Optional<T> before(T element) {
        return Optional.empty();
    }

    static class TreeNode<T> {
        T datum;
        TreeNode<T> left;
        TreeNode<T> right;
        public TreeNode(T datum, TreeNode<T> left, TreeNode<T> right){
            this.datum = datum;
            this.left = left;
            this.right = right;
        }
    }

}


