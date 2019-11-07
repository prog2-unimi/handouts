package it.unimi.di.prog2.l05;

import java.util.List;

/**
 * Provides useful standalone procedures for manipulating lists.
 *
 * <p>
 * This is a "modern" implementation of Figure 3.7 code (using {@link List} instead of vectors).
 */
public class Lists {

  /**
   * Removes the duplicate elements from a list.
   *
   * <p>
   * Remsoves all duplicate elements from <code>v</code>; uses {@link Object#equals(Object)} to
   * determine duplicates. The order of remaining elements may change.
   *
   * @param v a list, its elements must be not <code>null</code>; it will be modified in place.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void removeDupls(List v) {
    if (v == null)
      return;
    for (int i = 0; i < v.size(); i++) {

      Object x = v.get(i);
      int j = i + 1;

      // remove all dupls of x from the rest of v
      while (j < v.size())
        if (!x.equals(v.get(j)))
          j++;
        else {
          int last = v.size() - 1;
          v.set(j, v.get(last));
          v.remove(last);
        }
    }
  }
}
