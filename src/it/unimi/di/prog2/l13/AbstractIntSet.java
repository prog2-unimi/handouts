package it.unimi.di.prog2.l13;

import java.util.Iterator;

public abstract class AbstractIntSet implements IntSet {

  /** The size of this set. */
  protected int size;

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>
   * Builds the set \( S = \varnothing \).
   */
  public AbstractIntSet() {
    size = 0;
  }

  /**
   * A <em>copy constructor</em>.
   *
   * @param other the {@code IntSet} to copy from.
   */
  public AbstractIntSet(IntSet other) {
    for (Integer i : other)
      insert(i);
  }

  // Methods

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    String result = this.getClass().getSimpleName() + ": ";
    if (size == 0)
      return result + "{}";
    Iterator<Integer> it = iterator();
    result += "{ " + it.next();
    while (it.hasNext())
      result += ", " + it.next();
    return result + " }";
  }

}
