/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.l05;

/**
 * This class provides a number of standalone procedures that are useful for manipulating arrays of
 * ints.
 *
 * <p>This version contains just the implementaion of Figure 3.6.
 */
public class Arrays2 {

  /**
   * Sorts the given array.
   *
   * <p>Rearranges the elements of <code>a</code> into ascending order; e.g., if <code>
   * a = [3, 1, 6, 1]</code> before the call, on return <code>a = [1, 1,
   * 3, 6]</code>.
   *
   * @param a the array of elements to be sorted; it will be modified inplace.
   */
  public static void sort(int[] a) {
    if (a == null) return;
    quickSort(a, 0, a.length - 1);
  }

  /**
   * Sorts a subvector in ascending order using the quick sort algorithm.
   *
   * <p>Sorts <code>a[low]</code>, <code>a[low + 1]</code>, …, <code>a[high]</code> into ascending
   * order.
   *
   * @param a the vector containing the subvector to be sorted, must not be <code>null</code>; it
   *     will be modified inplace.
   * @param low the first index of the subvector to sort, 0 &lt;= <code>low</code>.
   * @param high the last index of the subvector to sort, <code>high</code> &lt; <code>a.length
   *     </code>.
   */
  private static void quickSort(int[] a, int low, int high) {
    if (low >= high) return;
    int mid = partition(a, low, high);
    quickSort(a, low, mid);
    quickSort(a, mid + 1, high);
  }

  /**
   * Partitions a subvector.
   *
   * <p>Reorders the elements in a into two contiguous groups, <code>a[i]</code>, …, <code>a[res]
   * </code> and <code>a[res + 1]</code>,…, <code>a[j]</code>, such that each element in the second
   * group is at least as large as each element of the first group.
   *
   * @param a is not <code>null</code>; it will be modified inplace.
   * @param i 0 &lt;= <code>i</code>.
   * @param j <code>j</code> &lt; <code>a.length</code>.
   * @return <code>res</code>.
   */
  private static int partition(int[] a, int i, int j) {
    int x = a[i];
    while (true) {
      while (a[j] > x) j--;
      while (a[i] < x) i++;
      if (i < j) { // need to swap
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        j--;
        i++;
      } else return j;
    }
  }
}
