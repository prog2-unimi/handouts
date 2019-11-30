package it.unimi.di.prog2.l14.adder;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    AdderImpl a = new AdderImpl();
    a.add(List.of(1, 2, 3));
    System.out.println(a.result());

    LogAdderImpl la = new LogAdderImpl();
    la.add(List.of(1, 2, 3));
    System.out.println(la.result());
    System.out.println(la.log());
  }
}
