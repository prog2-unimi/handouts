package it.unimi.di.prog2.l05;

/**
 * This class provides a number of standalone procedures that are useful for manipulating arrays of
 * ints.
 *
 * <p>This version contains just the implementaion of Figure 3.5.
 */
public class Arrays1 {

  /**
   * Searches for an element in a sorted array.
   *
   * @param a the array of elements to be searched, sorted in ascending order.
   * @param x the element to search in <code>a</code>.
   * @return an index where <code>x</code> is stored <code>x</code> is in <code>a</code>, otherwise
   *     returns -1.
   */
  public static int searchSorted(int[] a, int x) {
    if (a == null) return -1;
    for (int i = 0; i < a.length; i++)
      if (a[i] == x) return i;
      else if (a[i] > x) return -1;
    return -1;
  }
}
