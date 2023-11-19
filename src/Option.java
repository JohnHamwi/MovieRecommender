/**
 * Represents a title with its similarity
 */
 public class Option implements Comparable<Option>
 {
   private String title;
   private double similarity;

   /**
    * Creates a option with title {@code title} and similarity {@code similarity}.
    *
    * @param title the title the option represents.
    * @param similarity the similarity of the title to the the title being
    * searched for.
    */
   public Option(String title, double similarity)
   {
     this.title = title;
     this.similarity = similarity;
   }

   /**
    * Gets the title associated with this option.
    *
    * @return the title associated with this option.
    */
   public String getTitle()
   {
     return this.title;
   }

   /**
    * Gets the similarity associated with this option.
    *
    * @return the similarity of the title.
    */
   public double getSimilarity()
   {
     return this.similarity;
   }

   /**
    * Compares two options.
    *
    * @param o the option to compare against.
    * @return -1 if this option is greater than the option, 0 if the
    * options are equal, and -1 otherwise.
    */
   public int compareTo(Option o)
   {
     double diff = this.similarity - o.similarity;

     if (diff < 0)
      return 1;
     else if (diff > 0)
      return -1;
     else
      return 0;
   }

   /**
    * Determines if the two objects are equal.
    *
    * @param o the object to compare to.
    * @return true the this and the object are equal; otherwise false.
    */
    @Override
    public boolean equals(Object o)
    {
      // If they are the same memory address they are equal.
      if (this == o)
        return true;

      // If the object is not a option they can't be equal.
      if (!(o instanceof Option))
        return false;

      return (this.compareTo((Option) o) == 0);
    }

    /**
     * Creates a string form of the option.
     *
     * @return the option as a string.
     */
     @Override
     public String toString()
     {
         return title + " (" + String.format("%.2f%%", Double.isNaN(similarity) ? 0 : similarity) + ")";
     }

 }
