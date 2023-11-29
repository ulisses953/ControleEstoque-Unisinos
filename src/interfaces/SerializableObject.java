package interfaces;

import java.io.Serializable;

import model.SerializableManager;

@SuppressWarnings("unchecked")
public interface SerializableObject<T> extends Serializable {

  default SerializableManager<T> getManager() {
    return new SerializableManager<T>();
  }

  default void saveObject() {
    getManager().serialize((T) this);
  }

  default T getSavedObject() {
    return getManager().deserialize((T) this);
  }
  
  default void deleteSave() {
    getManager().removeSerialization((T) this);
  }
}