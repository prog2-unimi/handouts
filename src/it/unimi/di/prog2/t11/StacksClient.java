/*

Copyright 2020 Massimo Santini

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

package it.unimi.di.prog2.t11;

import java.util.Iterator;

public class StacksClient {

  public static void main(String[] args) {
    Iterator<Integer> i;

    BoundedIntStackSN bisi = new BoundedIntStackSN(5);
    bisi.push(1);
    bisi.push(2);
    bisi.push(3);
    i = bisi.content();
    while (i.hasNext()) System.out.println(i.next());

    BoundedIntStackI bili = new BoundedIntStackI(5);
    bili.push(4);
    bili.push(5);
    bili.push(6);
    i = bili.content();
    while (i.hasNext()) System.out.println(i.next());
    i = bili.evenContent();
    while (i.hasNext()) System.out.println(i.next());

    BoundedIntStackAI biai = new BoundedIntStackAI(5);
    biai.push(7);
    biai.push(8);
    biai.push(9);
    i = biai.content();
    while (i.hasNext()) System.out.println(i.next());
  }
}
