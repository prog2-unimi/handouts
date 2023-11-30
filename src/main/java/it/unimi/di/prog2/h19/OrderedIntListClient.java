/*

Copyright 2021 Luca Prigioniero, Massimo Santini
Copyright 2023 Massimo Santini

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

package it.unimi.di.prog2.h19;

import java.util.Iterator;
import java.util.Scanner;

public class OrderedIntListClient {

  /**
   * Stampa gli elementi di un iteratore separati da virgole.
   *
   * @param it l'iteratore, non deve essere {@code null}.
   */
  public static void print(Iterator<Integer> it) {
    while (it.hasNext()) {
      System.out.print(it.next());
      if (it.hasNext()) System.out.print(", ");
    }
    System.out.println();
  }

  /**
   * Legge da standard input una sequenza di interi e li inserisce in una lista ordinata; quindi la
   * stampa in ordine crescente e decrescente.
   *
   * @param args non usato.
   */
  public static void main(String[] args) {
    OrderedIntList list = new OrderedIntList();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) list.add(s.nextInt());
    }
    print(list.smallToBig());
    print(list.bigToSmall());
  }
}
