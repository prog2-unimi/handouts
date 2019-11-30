package it.unimi.di.prog2.l14.adder;

import java.util.List;

public interface Adder {
  public void add(int x);

  public void add(List<Integer> l);

  public int result();
}
