package interfaces;

import java.io.Serializable;

public interface Serialize<T> extends Serializable {
  void serialize(T object);

  T deserialize(T object);
}
