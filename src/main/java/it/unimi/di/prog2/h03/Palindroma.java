/*

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

package it.unimi.di.prog2.h03;

import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/palindroma_r/Testo.md">testo</a>,
 * eliminando la richiesta: "senza fare uso di cicli".
 */
public class Palindroma {

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      String parola = s.next();
      int i, len = parola.length();
      for (i = 0; i < len / 2; i++) if (parola.charAt(i) != parola.charAt(len - i - 1)) break;
      if (i == len / 2) System.out.println("si");
    }
  }
}
