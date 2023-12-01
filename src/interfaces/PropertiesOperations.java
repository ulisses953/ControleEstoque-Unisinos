package interfaces;

import java.io.File;

public interface PropertiesOperations<T> {
  T getPropsObject();

  void saveProps();

  default void deleteProps() {
    String path = System.getProperty("user.dir") + "\\config\\data" + getClass().getName() + ".properties";
    try {
      new File(path).delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  String getPropObject(String propKey);

  void setPropObject(String propKey, String propValue);
}
