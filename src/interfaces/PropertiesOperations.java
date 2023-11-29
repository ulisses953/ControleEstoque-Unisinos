package interfaces;

public interface PropertiesOperations<T> {
  T getPropsObject();

  void saveProps();

  void deleteProps(String propsName);

  Object getPropObject(String propKey);

  void setPropObject(String propKey, String propValue);
}
