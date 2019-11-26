package it.unimi.di.prog2.l13;

import java.util.Iterator;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers.
 *
 * <p>
 * A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public interface IntSet extends Iterable<Integer> {

  /**
   * An exception thrown in case operations that expect the set not to be empty are instead called
   * on an empty set.
   */
  @SuppressWarnings("serial")
  public static class EmptyException extends RuntimeException {

    public EmptyException() {
      super();
    }

    public EmptyException(String message) {
      super(message);
    }
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>
   * Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size();

  /**
   * Adds the given element to this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public void insert(int x);

  /**
   * Removes the given element from this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public void remove(int x);

  /**
   * Tells if the given element is in this set.
   *
   * <p>
   * Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  default public boolean isIn(int x) {
    for (Integer i : this)
      if (x == i)
        return true;
    return false;
  }

  /**
   * Tells if a given set is a subset of this set.
   *
   * @param s the set to check.
   * @return whether the given set is a subset of this set, or {@code false} if it is {@code null}.
   */
  default public boolean subset(IntSet s) {
    if (s == null)
      return false;
    for (Integer i : this)
      if (!s.isIn(i))
        return false;
    return true;
  }

  /**
   * Returns an element from this set.
   *
   * @return an arbitrary element from this set.
   * @throws EmptyException if this set is empty.
   */
  default public int choose() throws EmptyException {
    final Iterator<Integer> it = iterator();
    if (!it.hasNext())
      throw new EmptyException();
    final int x = it.next();
    remove(x);
    return x;
  }

  public boolean repok();

}
