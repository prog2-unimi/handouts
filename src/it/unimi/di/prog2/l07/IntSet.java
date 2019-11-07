package it.unimi.di.prog2.l07;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers.
 *
 * <p>
 * A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public class IntSet {

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>
   * Builds the set \( S = \varnothing \).
   */
  public IntSet() {}

  // Methods

  /**
   * Adds the given element to this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public void insert(int x) {}

  /**
   * Removes the given element from this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public void remove(int x) {}

  /**
   * Tells if the given element is in this set.
   *
   * <p>
   * Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  public boolean isIn(int x) {
    return false;
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>
   * Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size() {
    return 0;
  }

  /**
   * Returns an element chosen at random from this set.
   *
   * @return an arbitrary element from this set.
   * @throws EmptyException if this set is empty.
   */
  public int choose() throws EmptyException {
    return 0;
  }
}
