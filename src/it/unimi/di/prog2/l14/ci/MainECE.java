package it.unimi.di.prog2.l14.ci;

import java.util.List;
import it.unimi.di.prog2.l14.ci.ec.Adder;
import it.unimi.di.prog2.l14.ci.ec.LogAdderExt;

public class MainECE {
  public static void main(String[] args) {
    Adder a = new Adder();
    a.add(List.of(1, 2, 3));
    System.out.println(a.result());

    LogAdderExt lae = new LogAdderExt();
    lae.add(List.of(1, 2, 3));
    System.out.println(lae.result());
    System.out.println(lae.log());
  }
}
