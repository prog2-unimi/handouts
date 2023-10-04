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
 * href="https://github.com/mapio/labprog/blob/master/esercizi/fasce_pegi/Testo.md">testo</a>.
 */
public class FascePEGI {

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      int età = s.nextInt();
      if (età <= 6) System.out.println("fascia 3");
      else if (età <= 11) System.out.println("fascia 7");
      else if (età <= 15) System.out.println("fascia 12");
      else if (età <= 17) System.out.println("fascia 16");
      else System.out.println("fascia 18");
    }
  }
}
