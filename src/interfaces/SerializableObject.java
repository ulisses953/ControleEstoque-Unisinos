package interfaces;

import java.io.Serializable;

public interface SerializableObject<T> extends Serializable {
  void saveObject();
  
  T getSerializedObject();

  void deleteSave();
}
