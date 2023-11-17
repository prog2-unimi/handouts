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

package it.unimi.di.prog2.h15;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iteratori di utilità per stringe. */
public class StringIterators {

  /**
   * Filtra le righe di posto pari.
   *
   * @param it un iteratore di stringhe.
   * @return un iteratore che restituisce le stringhe di posto pari di {@code it}.
   */
  public static Iterator<String> evenIterator(final Iterator<String> it) {
    return new Iterator<>() {
      private String next = null;

      @Override
      public boolean hasNext() {
        if (next != null) return true;
        while (it.hasNext()) {
          next = it.next();
          if (next.length() % 2 == 0) return true;
        }
        return false;
      }

      @Override
      public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        String result = next;
        next = null;
        return result;
      }
    };
  }

  /**
   * Mette in in maiuscole.
   *
   * @param it un iteratore di stringhe.
   * @return un iteratore che restituisce le stringhe di {@code it} in maiuscole.
   */
  public static Iterator<String> uppercase(final Iterator<String> it) {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public String next() {
        return it.next().toUpperCase();
      }
    };
  }
}
