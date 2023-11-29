package interfaces;

public interface PropertiesOperations<T> {
  T getObjectWithSavedProps();

  void saveProps();

  void deleteProps(String propsName);

  Object getPropObject(String propKey);

  void setPropObject(String propKey, String propValue);
}
