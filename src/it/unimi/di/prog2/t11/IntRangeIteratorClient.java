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

import java.util.Scanner;

public class IntRangeIteratorClient {

  /* Merges two IntRangeIterator wrapping them in a NextOrNullIteratorWrapper. */
  public static void main(String[] args) {
    NextOrNullIteratorWrapper<Integer> ai, bi;
    try (Scanner s = new Scanner(System.in)) {
      ai =
          new NextOrNullIteratorWrapper<Integer>(
              new IntRangeIterator(s.nextInt(), s.nextInt(), s.nextInt()));
      bi =
          new NextOrNullIteratorWrapper<Integer>(
              new IntRangeIterator(s.nextInt(), s.nextInt(), s.nextInt()));
    }
    Integer x = ai.next(), y = bi.next();
    while (x != null && y != null)
      if (x < y) {
        System.out.println("A" + x);
        x = ai.next();
      } else if (x > y) {
        System.out.println("B" + y);
        y = bi.next();
      } else {
        System.out.println("AB" + x);
        x = ai.next();
        y = bi.next();
      }
    while (x != null) {
      System.out.println("A" + x);
      x = ai.next();
    }
    while (y != null) {
      System.out.println("B" + y);
      y = bi.next();
    }
  }
}
