/*

Copyright 2024 Massimo Santini

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
 * Soluzione dell'esercizio <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/palindroma_r/Testo.md">Palindroma</a>(versione
 * con cicli).
 */
public class Palindroma {

  /** . */
  private Palindroma() {}

  /**
   * Il metodo principale.
   *
   * @param args gli argomenti della riga di comando
   */
  public static void main(String[] args) {
    String parola;
    try (Scanner s = new Scanner(System.in)) {
      parola = s.next();
    }
    int len = parola.length();
    for (int i = 0; i < len / 2; i++) if (parola.charAt(i) != parola.charAt(len - i - 1)) return;
    System.out.println("sì");
  }
}
