package io.teknek.collections;

import java.util.function.Consumer;

public interface TreeTraversable<T> {
    ImmutableIterator<T> inOrderIterator();

    /**
     * A consumer that will visit each node in a tree inorder
     * @param consumer called for each entry in the tree
     */
    void inOrderVisitor(Consumer<T> consumer);
}
