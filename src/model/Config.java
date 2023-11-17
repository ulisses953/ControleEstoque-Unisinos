package model;

import java.io.*;

public class Config implements Serializable {
  private static Config instance = null;

  private String version = "0.0.1";
  private String serializedRoot = "..\\..\\serializedData\\";
  private boolean serializeEverything = false;
  private boolean serializeStorage = false;
  private boolean serializeStorageInfo = false;

  private Config() {}

  public static Config getInstance() {
    if (instance == null) {
      instance = new Config();
    }    
    return instance;
  }

  //#region get and set 
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getSerializedRoot() {
    return serializedRoot;
  }

  public void setSerializedRoot(String serializedRoot) {
    this.serializedRoot = serializedRoot;
  }

  public boolean isSerializeEverything() {
    return serializeEverything;
  }

  public void setSerializeEverything(boolean serializeEverything) {
    this.serializeEverything = serializeEverything;
  }

  public boolean isSerializeStorage() {
    return serializeStorage;
  }

  public void setSerializeStorage(boolean serializeStorage) {
    this.serializeStorage = serializeStorage;
  }

  public boolean isSerializeStorageInfo() {
    return serializeStorageInfo;
  }

  public void setSerializeStorageInfo(boolean serializeStorageInfo) {
    this.serializeStorageInfo = serializeStorageInfo;
  }
  //#endregion

  @Override
  public String toString() {
    return "\t [CONFIG]:" + '\n'
        + "Version: " + version + '\n'
        + "Save Data Path :" + serializedRoot;
  }
}
