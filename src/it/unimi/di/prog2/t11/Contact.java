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

import java.util.ArrayList;
import java.util.List;

public class Contact {

  public class PhoneNumber {
    private final String prefix, number;

    public PhoneNumber(String prefix, String number) {
      this.prefix = prefix;
      this.number = number;
    }

    @Override
    public String toString() {
      return first + " " + last + ": " + prefix + "/" + number;
    }
  }

  private final String first, last;
  private final List<PhoneNumber> phoneNumbers = new ArrayList<>();

  public Contact(String first, String last) {
    this.first = first;
    this.last = last;
  }

  public void addPhoneNumber(String prefix, String number) {
    phoneNumbers.add(new PhoneNumber(prefix, number));
  }

  public PhoneNumber firstWithPrefix(String prefix) {
    for (PhoneNumber p : phoneNumbers) if (p.prefix.equals(prefix)) return p;
    return null;
  }
}
