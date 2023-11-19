package model;

import interfaces.SerializableObject;

@SuppressWarnings("unchecked")
public abstract class AbstractSerializableObject<T> implements SerializableObject<T> {
  private SerializableManager<T> manager = null;

  private SerializableManager<T> getManager() {
    if(manager == null) {
      return manager = new SerializableManager<>();
    }
    return manager;
  }

  public void saveObject() {
    getManager().serialize((T) this);
  }

  public T getObject() {
    return getManager().deserialize((T) this);
  }
  
  public void deleteSave() {
    getManager().removeSerialization((T) this);
  }
}
