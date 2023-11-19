package datastructures.tree;

/**
 * A generic search tree interface. Make sure T implements the Comparable
 * interface on itself or its superclass.
 */
 public interface SearchTreeInterface<T extends Comparable<? super T>> extends
  TreeInterface<T>
 {
   /**
    * Searches the tree for a specific item {@code item}
    *
    * @param item the item to search for in the tree.
    * @return true if {@code item} is found in the tree; otherwise, false.
    */
    public boolean contains(T item);

    /**
     * Retrieves a specific item in the tree.
     *
     * @param item the item to be found.
     * @return either the object found in the tree or null if not found.
     */
     public T getEntry(T item);

     /**
      * Adds an entry to the tree.
      *
      * @param item the item to add to the tree.
      * @return null if {@code item} was added to the tree did not contain the
      * item or the existing entry that matched item is returned (it is replaced
      * by {@code item}).
      */
      public T add(T item);

      /**
       * Removes an item from the tree.
       *
       * @param item the item to remove from the tree.
       */
       public void remove(T item);
 }
