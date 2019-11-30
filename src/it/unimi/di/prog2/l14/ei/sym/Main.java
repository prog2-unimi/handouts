package it.unimi.di.prog2.l14.ei.sym;

public class Main {
  public static void main(String[] args) {
    S s = new S(1, 2);
    T t = new T(1);
    System.out.println(t.equals(s));
    System.out.println(s.equals(t));
  }
}


