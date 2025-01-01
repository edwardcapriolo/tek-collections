package io.teknek.collections.set;

import io.teknek.collections.Set;

import java.util.Comparator;
import java.util.SortedSet;

public class TreeSet<T> implements Set<T> {

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
        } else if (result < 0) {
            if (root.left == null) {
                root.left = new TreeNode<>(t, null, null);
            } else {
                addElement(root.left, t);
            }
        } else /*if (result > 0) */ {
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
        } else if (result < 0) {
            return contains(currentNode.left, searchingFor);
        } else {
            return contains(currentNode.right, searchingFor);
        }
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


