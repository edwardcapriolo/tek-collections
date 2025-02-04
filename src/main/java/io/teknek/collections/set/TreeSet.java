package io.teknek.collections.set;

import io.teknek.collections.*;

import java.util.Comparator;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import io.teknek.collections.evolving.Maybe;

public class TreeSet<T> implements Set<T>, SortedSet<T>, DefinitiveSize, TreeTraversable<T> {

    protected TreeNode root;
    protected Comparator comparator;

    public TreeSet(Comparator<T> comparator, T ... elements) {
        this.comparator = MaybeComparator.of(comparator);
        for (T t : elements) {
            if (root == null) {
                root = new TreeNode<>(nullToNothing(t), null, null);
                continue;
            }
            addElement(root, (T) nullToNothing(t));
        }
    }

    public TreeSet(Comparator<T> comparator, Iterable<T> input) {
        this.comparator = comparator;
        for (T t : input) {
            if (root == null) {
                root = new TreeNode<>(nullToNothing(t), null, null);
                continue;
            }
            addElement(root, (T) nullToNothing(t));
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
        Object nothingOrResult = lowest(root);
        return Maybe.nothing() == nothingOrResult ? null : (T) nothingOrResult;
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
        Object nothingOrResult = highest(root);
        return Maybe.nothing() == nothingOrResult ? null : (T) nothingOrResult;
    }

    @Override
    public Maybe<T> lastOrNothing() {
        if (root == null){
            return Maybe.nothing();
        } else {
            Object nothingORResult =  highest(root);
            return Maybe.nothing() == nothingORResult ? Maybe.nullValue() : Maybe.definitely( (T) nothingORResult);
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
        if (split == 0){
            return node.right == null ? Maybe.nothing() : Maybe.definitely(node.right.datum);
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
        final TreeNode start = this.root;
        if (start == null){
            return (ImmutableIterator<T>) ImmutableIterator.<T>empty();
        }
        ImmutableIterator<T> it = new ImmutableIterator<T>() {
            Object current;
            boolean first = true;
            @Override
            public boolean hasNext() {
                if (first){
                    return true;
                }
                T seak = current == Maybe.nothing() ? null : (T) current;
                Maybe<T> next = after(seak);
                return next != Maybe.nothing();
            }

            @Override
            public T next() {
                if (first) {
                    Object toReturn = root.datum;
                    current = root.datum;
                    first = false;
                    return toReturn == Maybe.nothing() ? null : (T) current;
                } else {
                    Object toReturn = current;
                    T converted = objectToCouldBeT(toReturn);
                    Maybe<T> next = after(converted);
                    if (next == Maybe.nothing()){
                        throw new IllegalArgumentException("Next passed end");
                    }
                    current = converted;
                    return converted;
                }
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
        consumer.accept( node.datum == Maybe.nothing() ? null: node.datum);
        inOrderConsumer(consumer, node.right);
    }

    private static <X> Object nullToNothing(X t){
        return t == null? Maybe.nothing() : t;
    }
    private static <X> X objectToCouldBeT(Object o){
        return o == Maybe.nothing() ? null : (X) o;
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


