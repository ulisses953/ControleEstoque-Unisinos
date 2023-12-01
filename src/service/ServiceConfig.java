package service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

import interfaces.PropertiesOperations;
import interfaces.SerializeObject;
import model.Config;
import model.SerializableManager;

public class ServiceConfig implements PropertiesOperations<Config>, SerializeObject<Config>{
  private Config config = Config.getInstance();

  public ServiceConfig() {
  }

  private Properties getProperties() throws IOException {
    Properties properties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream(config.PROPS_PATH + "data" + config.getClass().getName() + ".properties");
      properties.load(fileInputStream);
      fileInputStream.close();
    } catch(FileNotFoundException e) {
      throw e;
    } catch(IOException e) {
      throw e;
    }
    return properties;
  }

  @Override
  public Config getPropsObject() {
    Config config = Config.getInstance();
    try {
      Properties properties = getProperties();
      Field[] fields = config.getClass().getDeclaredFields();

      for(Field field : fields) {
        String fieldName = field.getName();

        boolean ignoredFields = fieldName.equals("serialVersionUID") || 
                                fieldName.equals("instance") || 
                                fieldName.equals("propertiesPath") ||
                                fieldName.equals("serviceConfig");
        if(ignoredFields) continue;

        field.setAccessible(true);
        String value = properties.getProperty(fieldName);

        if (field.getType() == boolean.class) {
          String newValue = value == "true" || value == "false" ? value : "false";
          properties.setProperty(fieldName, newValue);
          field.set(config, Boolean.parseBoolean(newValue));

        } else if (field.getType() == int.class) {
          field.set(config, Integer.parseInt(value));

        } else if (field.getType() == double.class) {
          field.set(config, Double.parseDouble(value));

        } else {
          field.set(config, value);
        }
      }
    } catch (FileNotFoundException fileNotFoundException) {
      saveProps();
    } catch(Exception e) {
      e.printStackTrace();
    }

    return config;
  }

  @Override
  public void saveProps() {
    try {
      Properties properties = new Properties();

      File file = new File(config.PROPS_PATH);
      if (!file.exists()) {
        file.mkdir();
      }

      Field[] fields = config.getClass().getDeclaredFields();

      for(Field field : fields) {
        String fieldName = field.getName();

        boolean ignoredFields = fieldName.equals("serialVersionUID") ||
                                fieldName.equals("instance") ||
                                fieldName.equals("propertiesPath") ||
                                fieldName.equals("serviceConfig");
        if(ignoredFields) continue;

        field.setAccessible(true);
        Object value = field.get(config);
        properties.put(fieldName, value.toString());
      }

      FileOutputStream outputStream = new FileOutputStream(config.PROPS_PATH + "data" + config.getClass().getName() + ".properties");
      properties.store(outputStream, null);
      outputStream.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public Object getPropObject(String propKey) {
    try {
      Properties properties = getProperties();
      return properties.getProperty(propKey);
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void setPropObject(String propKey, String propValue) {
    try {
      Properties properties = getProperties();

      Field[] fields = config.getClass().getDeclaredFields();
      for(Field field : fields) {
        field.setAccessible(true);

        if(field.getName().equals(propKey)) {

          if (field.getType() == boolean.class) {
            String value = propValue == "true" || propValue == "false" ? propValue : "false";
            properties.setProperty(propKey, value);
            field.set(config, Boolean.parseBoolean(propValue));

          } else if (field.getType() == int.class) {
            properties.setProperty(propKey, propValue);
            field.set(config, Integer.parseInt(propValue));

          } else if (field.getType() == double.class) {
            properties.setProperty(propKey, propValue);
            field.set(config, Double.parseDouble(propValue));

          } else {
            properties.setProperty(propKey, propValue);
            field.set(config, propValue);
          }

          break;
        }
      }

      FileOutputStream fileOutputStream = new FileOutputStream(config.PROPS_PATH + "data" + config.getClass().getName() + ".properties");
      properties.store(fileOutputStream, null);
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveObject() {
    SerializableManager<Config> serializableManager = new SerializableManager<>();
    serializableManager.serialize(config);
  }

  @Override
  @Deprecated
  public Config getSavedObject() {
    Config obj = null;
    try {
      String path = config.getSerializeRootPath() + config.getClass().getName() + ".ser";
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
}
