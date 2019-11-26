package it.unimi.di.prog2.l13;

/**
 * {@code SortedIntSet}s are mutable, unbounded, and ordered, sets of integers.
 *
 * <p>
 * A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public interface SortedIntSet extends IntSet {

  /**
   * Returns the maximum element from this set.
   *
   * @return the maximum element from this set, if not empty.
   * @throws EmptyException if this set is empty.
   */
  public int max() throws EmptyException;

  public boolean repok();

}
