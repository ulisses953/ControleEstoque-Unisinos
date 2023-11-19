package interfaces;

import java.io.Serializable;

public interface SerializableObject<T> extends Serializable {
  void saveObject();
  
  T getObject();

  void deleteSave();
}
