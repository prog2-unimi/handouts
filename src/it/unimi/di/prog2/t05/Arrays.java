/*

Copyright 2020 Massimo Santini

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

package it.unimi.di.prog2.t05;

/**
 * A class that provides a number of standalone procedures that are useful for manipulating arrays
 * of ints.
 */
public class Arrays {

  private Arrays() {} // so that no object of this class can be instantiated

  /**
   * Searches the specified element in the array.
   *
   * @param a an array.
   * @param x the element to search.
   * @return an index <code>i</code> such that <code>a[i] == x</code> if <code>x</code> is in <code>
   *     a</code>, -1 otherwise.
   */
  public static int search(int[] a, int x) {
    if (a == null) return -1;
    for (int i = 0; i < a.length; i++) if (a[i] == x) return i;
    return -1;
  }

  /**
   * Searches the specified element in the array.
   *
   * @param a a <em>sorted</em> array.
   * @param x the element to search.
   * @return an index <code>i</code> such that <code>a[i] == x</code> if <code>x</code> is in <code>
   *     a</code>, -1 otherwise.
   */
  public static int searchSorted(int[] a, int x) {
    if (a == null) return -1;
    int low = 0;
    int high = a.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (x == a[mid]) return mid;
      if (x < a[mid]) high = mid - 1;
      else low = mid + 1;
    }
    return -1;
  }

  /**
   * Rearranges the elements of a into ascending order.
   *
   * <p>For example, if <code>a = [3, 1, 6, 1]</code> before the call, on return <code>
   * a = [1, 1, 3, 6]</code>.
   *
   * @param a an array.
   */
  public static void sort(int[] a) {
    if (a == null) return;
    quickSort(a, 0, a.length - 1);
  }

  /**
   * Sorts the given subarray.
   *
   * <p>More precisely, reorders <code>a[low], a[low+1], …, a[high]</code> in ascending order.
   *
   * @param a a <em>non null</em> array.
   * @param low the lower limit, must be non negative.
   * @param high the upper limit, must be less than the length of <code>a</code>.
   */
  private static void quickSort(int[] a, int low, int high) {
    if (low >= high) return;
    int mid = partition(a, low, high);
    quickSort(a, low, mid);
    quickSort(a, mid + 1, high);
  }

  /**
   * Partitions the elements of the given subarray.
   *
   * <p>More precisely reorders the elements into two contiguous groups, <code>a[i],…, a[res]</code>
   * and <code>a[res+1],…, a[j]</code>, such that each element in the second group is at least as
   * large as each element of the first group, where <code>res</code> is the returned value.
   *
   * @param a a <em>non null</em> array.
   * @param i the lower limit, must be non negative and less than <code>j</code>.
   * @param j the upper limit, must be greater than <code>i</code> and less than the array length.
   * @return the upper limit of the first partition.
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
