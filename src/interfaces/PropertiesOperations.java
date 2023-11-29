package interfaces;

public interface PropertiesOperations<T> {
  T getObjectWithSavedProps();

  void saveProps();

  void deleteProps(String propsName);
}
