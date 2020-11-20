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

package it.unimi.di.prog2.t11;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedIntList {

  private boolean isEmpty;
  private int value;
  private OrderedIntList left, right;

  public OrderedIntList() {
    this.isEmpty = true;
  }

  public int size() {
    return isEmpty ? 0 : 1 + left.size() + right.size();
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public boolean contains(int value) {
    if (isEmpty) return false;
    if (value == this.value) return true;
    if (value < this.value) return left.contains(value);
    else return right.contains(value);
  }

  public void add(int value) {
    if (value == this.value) throw new IllegalArgumentException("Duplicate value");
    if (isEmpty) {
      this.value = value;
      isEmpty = false;
      left = new OrderedIntList();
      right = new OrderedIntList();
    } else if (value < this.value) left.add(value);
    else right.add(value);
  }

  private int min() {
    if (isEmpty) throw new NoSuchElementException();
    if (left.isEmpty) return value;
    return left.min();
  }

  public boolean remove(int value) {
    if (isEmpty) return false;
    if (value == this.value) {
      if (left.isEmpty && right.isEmpty) {
        isEmpty = true;
        left = right = null;
      } else if (left.isEmpty) {
        this.value = right.value;
        left = right.left;
        right = right.right;
      } else if (right.isEmpty) {
        this.value = left.value;
        right = left.right;
        left = left.left;
      } else {
        int min = right.min();
        this.value = min;
        right.remove(min);
      }
      return true;
    }
    if (value < this.value) return left.remove(value);
    else return right.remove(value);
  }

  public Iterator<Integer> smallToBig() {
    return new Iterator<>() {

      private boolean used = false;
      private Integer current = null;
      private final Iterator<Integer> leftIterator = isEmpty ? null : left.smallToBig();
      private final Iterator<Integer> rightIterator = isEmpty ? null : right.smallToBig();

      @Override
      public boolean hasNext() {
        if (isEmpty) return false;
        if (current != null) return true;
        if (leftIterator.hasNext()) {
          current = leftIterator.next();
          return true;
        }
        if (used == false) {
          current = value;
          used = true;
          return true;
        }
        if (rightIterator.hasNext()) {
          current = rightIterator.next();
          return true;
        }
        return false;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer ret = current;
        current = null;
        return ret;
      }
    };
  }

  public Iterator<Integer> bigToSmall() {
    return new Iterator<>() {

      private boolean used = false;
      private Integer current = null;
      private final Iterator<Integer> leftIterator = isEmpty ? null : left.bigToSmall();
      private final Iterator<Integer> rightIterator = isEmpty ? null : right.bigToSmall();

      @Override
      public boolean hasNext() {
        if (isEmpty) return false;
        if (current != null) return true;
        if (rightIterator.hasNext()) {
          current = rightIterator.next();
          return true;
        }
        if (used == false) {
          current = value;
          used = true;
          return true;
        }
        if (leftIterator.hasNext()) {
          current = leftIterator.next();
          return true;
        }
        return false;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer ret = current;
        current = null;
        return ret;
      }
    };
  }
}
