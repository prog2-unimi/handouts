/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

package it.unimi.di.prog2.l14.oi;

import it.unimi.di.prog2.l14.oi.sr.Histogram;
import it.unimi.di.prog2.l14.oi.sr.Rectangle;
import it.unimi.di.prog2.l14.oi.sr.Square;

public class MainSR {

  public static void main(String[] args) {

    Histogram hist1 = new Histogram();
    Rectangle r1 = new Rectangle(4, 4), r2 = new Rectangle(3, 3);
    hist1.add(r1);
    hist1.add(r2);
    System.out.println(hist1);
    hist1.changeBase(r2, 6);
    System.out.println(hist1);

    Histogram hist2 = new Histogram();
    Square s1 = new Square(4), s2 = new Square(3);
    hist2.add(s1);
    hist2.add(s2);
    System.out.println(hist2);
    hist2.changeBase(s2, 6);
    System.out.println(hist2);
  }
}
