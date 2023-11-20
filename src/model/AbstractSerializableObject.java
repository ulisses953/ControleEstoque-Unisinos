package model;

import interfaces.SerializableObject;

@SuppressWarnings("unchecked")
public abstract class AbstractSerializableObject<T> implements SerializableObject<T> {
  private SerializableManager<T> manager = null;

  private SerializableManager<T> getManager() {
    if(manager == null) {
      return manager = new SerializableManager<T>();
    }
    return manager;
  }

  public void saveObject() {
    SerializableManager<T> manager = new SerializableManager<T>();
    manager.serialize((T) this);
  }

  public T getSerializedObject() {
    return getManager().deserialize((T) this);
  }
  
  public void deleteSave() {
    getManager().removeSerialization((T) this);
  }
}
