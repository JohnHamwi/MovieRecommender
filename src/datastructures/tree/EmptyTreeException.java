package datastructures.tree;

/**
 * A simple empty tree exception.
 */
public class EmptyTreeException extends Exception
{
  /**
   * Just provide the default constructor. We don't want
   * to allow the user to choose the message associated
   * with the exception.
   */
  public EmptyTreeException()
  {
    super("Tree is empty.");
  }
}
