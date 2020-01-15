/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l14.ci;

import it.unimi.di.prog2.l14.ci.ec.Adder;
import it.unimi.di.prog2.l14.ci.ec.LogAdderComp;
import java.util.List;

public class MainECC {
  public static void main(String[] args) {
    Adder a = new Adder();
    a.add(List.of(1, 2, 3));
    System.out.println(a.result());

    LogAdderComp lac = new LogAdderComp();
    lac.add(List.of(1, 2, 3));
    System.out.println(lac.result());
    System.out.println(lac.log());
  }
}
