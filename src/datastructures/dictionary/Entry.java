package datastructures.dictionary;

/**
 * Represents a basic key-value entry.
 *
 * @param K the key type.
 * @param V the value type.
 *
 */
 public class Entry<K, V>
 {
   private K key;
   private V val;
   private Entry<K, V> next;


   /**
    * Constructs an empty node.
    */
   public Entry()
   {
     this(null, null, null);
   }

   /**
    * Constructs a new entry node with key {@code key} and value {@code val}.
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    */
   public Entry(K key, V val)
   {
     this(key, val, null);
   }

   /**
    * Constructs a new entry node with key {@code key} and value {@code val}
    * and next reference {@code next}.
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    * @param next the reference to the next node in the chain.
    */
   public Entry(K key, V val, Entry<K, V> next)
   {
     this.key = key;
     this.val = val;
     this.next = next;
   }

   /**
    * Gets the key associated with this node.
    *
    * @return the key associated with the node.
    */
   public K getKey()
   {
     return this.key;
   }

   /**
    * Gets the value associated with this node.
    *
    * @return the value associated with the node.
    */
    public V getValue()
    {
      return this.val;
    }

      /**
       * Set the value to {@code val}.
       *
       * @param val the value used for setting this value.
       */
      public void setValue(V val)
      {
        this.val = val;
      }

      /**
       * Set the value of the next reference.
       *
       * @param next the reference to the next node in the chain.
       */
      public void setNext(Entry<K, V> next)
      {
        this.next = next;
      }

      /**
       * Return the reference to the next node in the chain.
       *
       * @return the reference to the next node in the chain.
       */
      public Entry<K, V> getNext()
      {
        return this.next;
      }

 }
