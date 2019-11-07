package it.unimi.di.prog2.l05;

public class JavadocExample {

  /**
   * Finds the maximum value in an array.
   *
   * <p>
   * This function scans the elements of an array of integers and returns the maximum value found.
   *
   * @param x a (possibly empty) array of values
   * @return the maximum value found in <code>x</code>
   */
  public static int max(int[] x) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < x.length; i++)
      if (min < x[i])
        min = x[i];
    return min;
  }
}
