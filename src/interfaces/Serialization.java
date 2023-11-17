package interfaces;

public class Serialization<T> {
  private static Serialization instance = null;

  private Serialization() {}

  public static Serialization getInstance() {
    if(instance == null) {
      instance = new Serialization<>();
    }
    return instance;
  }
}