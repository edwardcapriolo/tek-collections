package io.teknek.collections.set;

import io.teknek.collections.*;

import java.util.Comparator;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;


import io.teknek.collections.evolving.Maybe;
import io.teknek.collections.evolving.Something;

public class TreeSet<T> implements Set<T>, SortedSet<T>, DefinitiveSize, TreeTraversable<T> {

    protected TreeNode<T> root;
    protected Comparator<T> comparator;

    public TreeSet(Comparator<T> comparator, T... elements) {
        this.comparator = comparator;
        for (T t : elements) {
            if (root == null) {
                root = new TreeNode<>(t, null, null);
                continue;
            }
            addElement(root, t);
        }
    }

    public TreeSet(Comparator<T> comparator, Iterable<T> input) {
        this.comparator = comparator;
        for (T t : input) {
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
    public Maybe<T> after(T element) {
        return after(element, this.root);
    }
    private Maybe<T> after(T searchFor, TreeNode<T> node){
        if(node == null){
            return Maybe.nothing();
        }
        int split = comparator.compare(node.datum, searchFor);
        if (split == 0 ){
            return node.right== null ? Maybe.nothing() : Maybe.definitely(node.right.datum);
        } else if(split > 0){
            return after(searchFor, node.left);
        } else {
            return after(searchFor, node.right);
        }
    }

    @Override
    public Maybe<T> before(T element) {
        return Maybe.nothing();
    }

    @Override
    public int size() {
        AtomicLong count = new AtomicLong();
        inOrderConsumer( (x ) -> count.getAndIncrement(), this.root);
        return (int) count.get();
    }

    @Override
    public ImmutableIterator<T> inOrderIterator() {
        //https://stackoverflow.com/questions/4581576/implementing-an-iterator-over-a-binary-search-tree
        final TreeNode<T> start = this.root;
        if (start == null){
            return (ImmutableIterator<T>) ImmutableIterator.<T>empty();
        }
        ImmutableIterator<T> it = new ImmutableIterator<T>() {
            T current;
            @Override
            public boolean hasNext() {
                if (current == null){
                    return true;
                }
                Maybe<T> next = after(current);
                return next != Maybe.nothing();
            }

            @Override
            public T next() {
                if (current == null) {
                    T toReturn = root.datum;
                    current = root.datum;
                    return toReturn;
                } else {
                    T toReturn = current;
                    Maybe<T> next = after(current);
                    if (next == Maybe.nothing()){
                      //cant make current null need some other special value
                    }
                    if (next instanceof Something){
                        Something<T> some = (Something<T>) next;
                        current = some.get();
                        return toReturn;
                    }
                }

                throw new UnsupportedOperationException("logic error");
            }
        };
        return it;
    }

    @Override
    public void inOrderVisitor(Consumer<T> consumer) {
        inOrderConsumer(consumer, root);
    }

    private void preOrderConsumer(Consumer<T> consumer, TreeNode<T> node) {
        if (node == null){
            return;
        }
        consumer.accept(node.datum);
        preOrderConsumer(consumer, node.left);
        preOrderConsumer(consumer, node.right);
    }

    private void inOrderConsumer(Consumer<T> consumer, TreeNode<T> node){
        if (node == null){
            return;
        }
        inOrderConsumer(consumer, node.left);
        consumer.accept(node.datum);
        inOrderConsumer(consumer, node.right);
    }
}

class TreeNode<T> {
    T datum;
    TreeNode<T> left;
    TreeNode<T> right;
    public TreeNode(T datum, TreeNode<T> left, TreeNode<T> right){
        this.datum = datum;
        this.left = left;
        this.right = right;
    }
}


