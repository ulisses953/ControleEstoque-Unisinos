package model;

import java.io.*;

public class Config implements Serializable {
  private static final long serialVersionUID = 1L;
  private static Config instance = null;

  private String version = "0.0.1";
  private static String serializedRoot = "..\\..\\serializedData\\";
  private boolean serializeEverything = false;
  private boolean serializeStorage = false;
  private boolean serializeStorageInfo = false;

  private Config() {
    save();
  }

  public static Config getInstance() {
    if (instance == null) {
      instance = deserialize();
      if (instance == null) {
        instance = new Config();
      }
    }
    return instance;
  }

  public void save() {
    try {
      File directory = new File(serializedRoot);
      if (!directory.exists()) {
        directory.mkdir();
      }

      FileOutputStream fileOut = new FileOutputStream(serializedRoot + "config.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  private static Config deserialize() {
    Config config = null;
    try {
      FileInputStream fileIn = new FileInputStream(serializedRoot + "config.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      config = (Config) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
      return null;
    } catch (ClassNotFoundException c) {
      System.out.println("Config class not found");
      c.printStackTrace();
      return null;
    }
    return config;
  }

  public String getVersion() {
    return version;
  }

  public String getSerializedRoot() {
    return serializedRoot;
  }

  public boolean getSerializeEverything() {
    return serializeEverything;
  }

  public boolean getSerializeStorage() {
    return serializeStorage;
  }

  public boolean getSerializeStorageInfo() {
    return serializeStorageInfo;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setSerializedRoot(String serializedRoot) {
    Config.serializedRoot = serializedRoot;
  }

  public void setSerializeStorageInfo(boolean serializeStorageInfo) {
    this.serializeStorageInfo = serializeStorageInfo;
  }

  public void setSerializeEverything(boolean serializeEverything) {
    this.serializeEverything = serializeEverything;
  }

  public void setSerializeStorage(boolean serializeStorage) {
    this.serializeStorage = serializeStorage;
  }

  public static String getStaticSerializedRoot() {
    if (instance == null) {
      return "..\\..\\serializedData\\"; // valor padrão
    } else {
      return getInstance().getSerializedRoot();
    }
  }

  @Override
  public String toString() {
    return "\t [CONFIG]:" + '\n'
        + "Version: " + version + '\n'
        + "Save Data Path :" + serializedRoot;
  }
}
