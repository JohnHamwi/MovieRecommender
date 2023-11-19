package datastructures.tree;

/**
 * Interface for a generic tree structure.
 */
public interface TreeInterface<T>
{
  /**
   * Gets the root data from the tree.
   *
   * @return the data stored at the root.
   * @throws EmptyTreeException if the tree is empty.
   */
   public T getRootData() throws EmptyTreeException;

   /**
    * Gets the height of the tree.
    *
    * @return the height of the tree (integer greater than 0).
    */
    public int getHeight();

    /**
     * Gets the number of nodes in the tree.
     *
     * @return an integer greater than or equal to 0.
     */
     public int getNumberOfNodes();

     /**
      * Determines if the tree is empty.
      *
      * @return true if the tree is empty; otherwise, false.
      */
      public boolean isEmpty();

      /**
       * Removes all entries from the tree.
       */
       public void clear();
}
