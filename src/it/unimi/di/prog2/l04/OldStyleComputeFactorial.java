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

package it.unimi.di.prog2.l04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OldStyleComputeFactorial {

  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter err = new PrintWriter(System.err);
    out.println("Enter an integer: ");
    String s = null;
    try {
      s = in.readLine();
      int n = Integer.parseInt(s);
      if (n > 0) {
        out.print(n);
        out.print("! = ");
        out.println(Num.fact(n));
      } else err.println("input not positive");
    } catch (Exception e) {
      err.println("bad input");
    }
  }
}
