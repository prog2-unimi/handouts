package it.unimi.di.prog2.l14.ci;

import java.util.List;
import it.unimi.di.prog2.l14.ci.ec.Adder;
import it.unimi.di.prog2.l14.ci.ec.LogAdderComp;

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
