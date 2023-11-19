package datastructures.tree;

/**
 * A simple generic binary tree interface.
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T>
{
    /**
     * Sets the root data of the tree.
     *
     * @param item the data to place in the root.
     */
     public void setRootData(T item);

       /**
        * Sets the binary tree to a new binary tree with
        * the named left and right subtrees.
        *
        * @param item the data to store in the root.
        * @param leftSubTree the new left subtree.
        * @param rightSubTree the new right subtree.
        */
        public void setTree(T item, BinaryTreeInterface<T> leftSubTree,
            BinaryTreeInterface<T> rightSubTree);
}
