package it.unimi.di.prog2.l14.oi.rs;

public class Main {
  public static void main(String[] args) {
    Histogram hist = new Histogram();
    Rectangle r1 = new Rectangle(4, 4), r2 = new Rectangle(3, 3);
    hist.add(r1);
    hist.add(r2);
    System.out.println(hist);
    hist.changeBase(r2, 6);
    System.out.println(hist);
  }
}
