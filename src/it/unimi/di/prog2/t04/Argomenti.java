/*

Copyright 2021 Massimo Santini

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

package it.unimi.di.prog2.t04;

public class Argomenti {

  /*
   * Un esempio di programma che riceve un elenco di interi come argomenti sulla
   * linea di comando e ne emette la somma.
   *
   * Una volta compilato, un esempio di esecuzione è
   *
   *  java Argomenti 1 2 3
   *
   */

  public static void main(String[] args) {
    int somma = 0;

    for (int i = 0; i < args.length; i++) somma += Integer.parseInt(args[i]);

    System.out.println(somma);
  }
}
