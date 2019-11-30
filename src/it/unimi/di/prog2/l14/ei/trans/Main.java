package it.unimi.di.prog2.l14.ei.trans;

public class Main {
  public static void main(String[] args) {
    S s = new S(1, 2);
    T t = new T(1);
    S u = new S(1, 3);
    System.out.println(s.equals(t));
    System.out.println(t.equals(u));
    System.out.println(s.equals(u));
  }
}


