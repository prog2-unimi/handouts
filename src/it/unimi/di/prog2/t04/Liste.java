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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Liste {

  /*
    Supponiamo di dover immagazzinare i quadrati dei primi 10 numeri interi
    positivi e quindi di doverli sommare.
  */

  public static void main(String[] args) {

    int somma;

    /*
      Usando i generici e il boxing/unboxing automatico (disponibili nelle
      versioni recenti di Java)
    */

    List<Integer> quadrati = new ArrayList<>();

    somma = 0;
    for (int i = 1; i <= 10; i++) quadrati.add(i * i);

    for (int q : quadrati) somma += q;

    System.out.println(somma);

    /*
      Usando Vector, casting e convertendo esplicitamente da/a i tipi di
      avvolgimento dei tipi primitivi (secondo quando disponibile quando è stato
      redattto il libro di testo).
    */

    Vector quadratiOldSchool = new Vector();

    for (int i = 1; i <= 10; i++) quadratiOldSchool.add(Integer.valueOf(i * i));

    somma = 0;
    for (int i = 0; i < quadratiOldSchool.size(); i++)
      somma += ((Integer) quadratiOldSchool.get(i)).intValue();

    System.out.println(somma);
  }
}
