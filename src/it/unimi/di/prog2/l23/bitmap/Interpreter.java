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

package it.unimi.di.prog2.l23.bitmap;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/** A command interpreter. */
public class Interpreter {

  private Interpreter() {}

  public static void run(final InputStream in, final PrintStream out) {
    Bitmap bitmap = new Bitmap(1, 1);
    final Stamp[] stamps = new Stamp[1000];
    @SuppressWarnings("resource")
    final Scanner s = new Scanner(in);
    int b, h, l, r, c, m;
    int[][] t;
    while (s.hasNext()) {
      final char command = s.next().charAt(0);
      switch (command) {
        case 'n':
          b = s.nextInt();
          h = s.nextInt();
          if (b < 0 || b > 1000 || h < 0 || h > 1000) continue;
          bitmap = new Bitmap(b, h);
          break;

        case 'c':
          bitmap.clear();
          break;

        case 'i':
          bitmap.invert();
          break;

        case 'x':
          r = s.nextInt();
          c = s.nextInt();
          bitmap.turnOn(new Coord(r, c));
          break;

        case 'o':
          r = s.nextInt();
          c = s.nextInt();
          bitmap.turnOff(new Coord(r, c));
          break;

        case 'h':
          l = s.nextInt();
          r = s.nextInt();
          c = s.nextInt();
          new Segment(new Coord(r, c), l, false).draw(bitmap);
          break;

        case 'v':
          h = s.nextInt();
          r = s.nextInt();
          c = s.nextInt();
          new Segment(new Coord(r, c), h, true).draw(bitmap);
          break;

        case 'r':
          h = s.nextInt();
          l = s.nextInt();
          r = s.nextInt();
          c = s.nextInt();
          new Rectangle(new Coord(r, c), l, h).draw(bitmap);
          break;

        case 's':
          m = s.nextInt();
          r = s.nextInt();
          c = s.nextInt();
          if (r < 0 || c < 0) throw new IllegalArgumentException("stamp r and c can't be negative");
          if (m < 0 || m >= 1000 || r >= 1000 || c >= 1000) {
            // discard the rest of the line
            for (int i = 0; i < r * c; i++) s.nextInt();
            continue;
          }
          t = new int[r][c];
          for (int i = 0; i < r; i++) for (int j = 0; j < c; j++) t[i][j] = s.nextInt();
          stamps[m] = new Stamp(t);
          break;

        case 'd':
          m = s.nextInt();
          r = s.nextInt();
          c = s.nextInt();
          if (0 <= m && m < 1000 && stamps[m] != null) stamps[m].draw(bitmap, r, c);
          break;

        case 'p':
          out.print(bitmap.render());
          break;
      }
    }
  }

  public static void main(String[] args) {
    run(System.in, System.out);
  }
}
