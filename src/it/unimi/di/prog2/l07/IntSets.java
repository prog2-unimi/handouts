package it.unimi.di.prog2.l07;

import it.unimi.di.prog2.l08.IntSet;

/** A collection of methods for {@link IntSet}s. */
public class IntSets {

  // See EJ 2.4
  private IntSets() {}

  /**
   * Builds a set from an array of elements.
   *
   * @param a and array of integer elements.
   * @return the set containing an entry for every distinct element of {@code a}.
   * @throws NullPointerException if {@code a} is {@code null}.
   */
  public static IntSet getElements(int[] a) throws NullPointerException {
    IntSet s = new IntSet();
    for (int i = 0; i < a.length; i++)
      s.insert(a[i]);
    return s;
  }
}
