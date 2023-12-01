package interfaces;

import java.io.Serializable;

import error.SerializedObjectNotFound;
import model.SerializableManager;

@SuppressWarnings("unchecked")
public interface SerializeObject<T> extends Serializable {

  default SerializableManager<T> getManager() {
    return new SerializableManager<T>();
  }

  default void saveObject() {
    getManager().serialize((T) this);
  }

  default T getSavedObject() throws SerializedObjectNotFound{
    return getManager().deserialize((T) this);
  }
  
  default void deleteSave() {
    getManager().removeSerialization((T) this);
  }
}