package model;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

import interfaces.SaveableObject;

public class Config implements SaveableObject<Config> {
  private static Config instance = new Config();
  private static final long serialVersionUID = 1L;

  private String version = "0.0.1";
  private String serializeRootPath = System.getProperty("user.dir") + "\\serializedData";
  private boolean serializeEverything = false;
  private boolean serializedStock = false;

  private Config() {
    desserializeConfig();
    instance.saveObject();
  }
  
  public static Config getInstance() {
    if(instance == null)
      instance = new Config();

    return instance;
  }

  // #region get and set
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getSerializeRootPath() {
    return serializeRootPath;
  }

  public void setSerializeRootPath(String serializedRoot) {
    this.serializeRootPath = serializedRoot;
  }

  public boolean isSerializeEverything() {
    return serializeEverything;
  }

  public void setSerializeEverything(boolean serializeEverything) {
    this.serializeEverything = serializeEverything;
  }

  public boolean isSerializeStock() {
    return serializedStock;
  }
  
  public void setSerializeStock(boolean serializeStorage) {
    this.serializedStock = serializeStorage;
  }
  // #endregion
  
  private void desserializeConfig() {
    File arquivo = new File(this.serializeRootPath + "\\" +  Config.class.getName() + ".ser");
    if (arquivo.exists()) {
      Config serializedConfig = this.getSavedObject();
      if (serializedConfig != null) {
        copyValuesFrom(serializedConfig);
      }
    }
    instance = this;
  }
  
  private void copyValuesFrom(Config config) {
    this.version = config.version;
    this.serializeRootPath = config.serializeRootPath;
    this.serializeEverything = config.serializeEverything;
    this.serializedStock = config.serializedStock;
  }

  @Override
  public Config getSavedObject() {
    Config obj = null;
    try {
      String path = serializeRootPath + "\\" + this.getClass().getName() + ".ser";
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

  @Override
  public void saveObject() {
    try {
      Properties properties = new Properties();
      FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "\\config\\dataConfig.properties");
      Field[] fields = this.getClass().getDeclaredFields();

      for(Field field : fields) {
        if(field.getName() == "serialVersionUID" || field.getName() == "instance") continue;

        field.setAccessible(true);
        Object value = field.get(this);
        properties.put(field.getName(), value.toString());
      }

      properties.store(outputStream, null);
    } catch(Exception e){
      System.out.println(e.fillInStackTrace());
    }
  }

  @Override
  public String toString() {
    return "Config [version=" + version + ", serializedRoot=" + serializeRootPath + ", serializeEverything="
        + serializeEverything + ", serializeStorage=" + serializedStock + "]";
  }
}
