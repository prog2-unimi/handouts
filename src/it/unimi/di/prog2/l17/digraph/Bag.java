package it.unimi.di.prog2.l17.digraph;

public interface Bag<T> {
  boolean isEmpty();

  void add(T e);

  T remove();
}
