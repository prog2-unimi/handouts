/*

Copyright 2022 Massimo Santini

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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Esempio di programma che riceve un elenco di parole nel flusso di ingresso
 * standard e una gustificazione (a scelta tra R, L e C) come argomento sulla
 * linea di comando ed emette l'elenco di parole lette racchiuse in una cornice
 * e giustificate come richiesto.
 */

public class ParoleIncorniciate {
  public static void main(String[] args) {
    char giustificazione = args[0].charAt(0);
    List<String> parole = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    int maxLen = 0;
    while (s.hasNext()) {
      String parola = s.next();
      if (parola.length() > maxLen) maxLen = parola.length();
      parole.add(parola);
    }

    for (int i = 0; i < maxLen + 4; i++) System.out.print('*');
    System.out.println();

    for (String parola : parole) {
      int prima = 0, dopo = 0, spazi = maxLen - parola.length();
      switch (giustificazione) {
        case 'L':
          dopo = spazi;
          break;
        case 'C':
          prima = spazi / 2;
          dopo = spazi - prima;
          break;
        case 'R':
          prima = spazi;
          break;
      }
      System.out.print("* ");
      for (int j = 0; j < prima; j++) System.out.print(' ');
      System.out.print(parola);
      for (int j = 0; j < dopo; j++) System.out.print(' ');
      System.out.println(" *");
    }

    for (int i = 0; i < maxLen + 4; i++) System.out.print('*');
    System.out.println();
  }
}
