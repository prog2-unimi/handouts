package it.unimi.di.prog2.l07;

@SuppressWarnings("serial")
public class EmptyException extends RuntimeException {

  public EmptyException() {
    super();
  }

  public EmptyException(String message) {
    super(message);
  }
}
