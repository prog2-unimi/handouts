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

package it.unimi.di.prog2.h04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/giustifica_a_destra/Testo.md">testo</a>,
 * ma leggendo dal flusso d'ingresso (invece che usando gli argomenti sulla linea di comando).
 */
public class GiustificaADestra {
  public static void main(String[] args) {
    List<String> parole = new ArrayList<>();
    int maxLen = 0;
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNext()) {
        String parola = s.next();
        if (parola.length() > maxLen) maxLen = parola.length();
        parole.add(parola);
      }
    }
    for (String parola : parole) {
      int spazi = maxLen - parola.length();
      while (spazi-- > 0) System.out.print(" ");
      System.out.println(parola);
    }
  }
}
