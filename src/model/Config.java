package model;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

import interfaces.PropertiesOperations;
import interfaces.SerializableObject;

public class Config implements PropertiesOperations<Config>, SerializableObject<Config> {
  private static Config instance = new Config();
  private final String PROPS_PATH = System.getProperty("user.dir") + "\\config\\";
  private static final long serialVersionUID = 1L;

  private String version = "0.0.1";
  private String serializeRootPath = System.getProperty("user.dir") + "\\serializedData\\";
  private boolean serializeEverything = false;
  private boolean serializedStock = false;

  private Config() {
    instance = getPropsObject();
  }
  
  public static Config getInstance() {
    if(instance == null)
      instance = new Config();
    return instance;
  }

  // #region get and set
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    setPropObject("version", version);
    this.version = version;
  }

  public String getSerializeRootPath() {
    return serializeRootPath;
  }

  public void setSerializeRootPath(String serializedRoot) {
    setPropObject("serializeRootPath", serializedRoot);
  }

  public boolean isSerializeEverything() {
    return serializeEverything;
  }

  public void setSerializeEverything(boolean serializeEverything) {
    setPropObject("serializeEverything", Boolean.toString(serializeEverything));
  }

  public boolean isSerializeStock() {
    return serializedStock;
  }
  
  public void setSerializeStock(boolean serializeStorage) {
    setPropObject("serializedStock", Boolean.toString(serializeStorage));
  }
  // #endregion

  @Override
  public Config getPropsObject() {
    try {
      Properties properties = new Properties();
      FileInputStream inputStream = new FileInputStream(PROPS_PATH + "dataConfig.properties");
      properties.load(inputStream);
      inputStream.close();

      Field[] fields = this.getClass().getDeclaredFields();

      for(Field field : fields) {
        String fieldName = field.getName();

        boolean ignoredFields = fieldName.equals("serialVersionUID") || 
                                fieldName.equals("instance") || 
                                fieldName.equals("propertiesPath");
        if(ignoredFields) continue;

        field.setAccessible(true);
        String value = properties.getProperty(fieldName);

        if (field.getType() == boolean.class) {
          field.set(this, Boolean.parseBoolean(value));
        } else if (field.getType() == int.class) {
          field.set(this, Integer.parseInt(value));
        } else if (field.getType() == double.class) {
          field.set(this, Double.parseDouble(value));
        } else {
          field.set(this, value);
        }
        break;
      }
    } catch (FileNotFoundException fileNotFoundException) {
      saveProps();
    } catch(Exception e) {
      e.printStackTrace();
    }

    return this;
  }

  @Override
  public void saveProps() {
    try {
      Properties properties = new Properties();

      File file = new File(PROPS_PATH);
      if (!file.exists()) {
        file.mkdir();
      }

      Field[] fields = this.getClass().getDeclaredFields();

      for(Field field : fields) {
        String fieldName = field.getName();

        boolean ignoredFields = fieldName.equals("serialVersionUID") ||
                                fieldName.equals("instance") ||
                                fieldName.equals("propertiesPath");
        if(ignoredFields) continue;

        field.setAccessible(true);
        Object value = field.get(this);
        properties.put(fieldName, value.toString());
      }

      FileOutputStream outputStream = new FileOutputStream(PROPS_PATH + "data" + getClass().getName() + ".properties");
      properties.store(outputStream, null);
      outputStream.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public Object getPropObject(String propKey) {
    try {
      Properties properties = new Properties();
      FileInputStream inputStream = new FileInputStream(PROPS_PATH + "data" + getClass().getName() + ".properties");
      properties.load(inputStream);
      inputStream.close();

      return properties.getProperty(propKey);
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void setPropObject(String propKey, String propValue) {
    try {
      Properties properties = new Properties();
      FileInputStream fileInputStream = new FileInputStream(PROPS_PATH + "data" + getClass().getName() + ".properties");
      properties.load(fileInputStream);
      fileInputStream.close();

      Field[] fields = this.getClass().getDeclaredFields();
      for(Field field : fields) {
        field.setAccessible(true);

        if(field.getName().equals(propKey)) {

          if (field.getType() == boolean.class) {
            String value = propValue == "true" || propValue == "false" ? propValue : "false";
            properties.setProperty(propKey, value);
            field.set(this, Boolean.parseBoolean(propValue));

          } else if (field.getType() == int.class) {
            properties.setProperty(propKey, propValue);
            field.set(this, Integer.parseInt(propValue));

          } else if (field.getType() == double.class) {
            properties.setProperty(propKey, propValue);
            field.set(this, Double.parseDouble(propValue));

          } else {
            properties.setProperty(propKey, propValue);
            field.set(this, propValue);
          }
          
          break;
        }
      }

      FileOutputStream fileOutputStream = new FileOutputStream(PROPS_PATH + "data" + getClass().getName() + ".properties");
      properties.store(fileOutputStream, null);
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  @Deprecated
  public Config getSavedObject() {
    Config obj = null;
    try {
      String path = serializeRootPath + this.getClass().getName() + ".ser";
      FileInputStream fileIn = new FileInputStream(path);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      obj = (Config) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return obj;
  }
  
  public static void resetInstance() {
    instance = null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Config other = (Config) obj;
    if (serializeEverything != other.serializeEverything)
      return false;
    if (serializedStock != other.serializedStock)
      return false;
    if (version == null) {
      if (other.version != null)
        return false;
    } else if (!version.equals(other.version))
      return false;
    if (serializeRootPath == null) {
      if (other.serializeRootPath != null)
        return false;
    } else if (!serializeRootPath.equals(other.serializeRootPath))
        return false;
    return true;
  }
  

  @Override
  public String toString() {
    return "Config [version=" + version + 
        ", serializedRoot=" + serializeRootPath + 
        ", serializeEverything=" + serializeEverything +
        ", serializeStorage=" + serializedStock + "]";
  }
}
