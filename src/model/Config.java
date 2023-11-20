package model;

import java.io.File;

public class Config extends AbstractSerializableObject<Config> {
  private static Config instance = null;

  private String version = "0.0.1";
  private String serializedRoot = System.getProperty("user.dir") + "\\serializedData";
  private boolean serializeEverything = false;
  private boolean serializeStorage = false;
  private boolean serializeStorageInfo = false;

  private Config() {
    desserializeConfig();
    instance.saveObject();
  }
  
  private void desserializeConfig() {
    File arquivo = new File(this.serializedRoot + "\\" +  Config.class.getName() + ".ser");
    if (arquivo.exists()) {
      Config serializedConfig = new SerializableManager<Config>().deserializeByName(Config.class.getName());
      if (serializedConfig != null) {
        copyValuesFrom(serializedConfig);
      }
    }
    instance = this;
  }

  private void copyValuesFrom(Config config) {
    this.version = config.version;
    this.serializedRoot = config.serializedRoot;
    this.serializeEverything = config.serializeEverything;
    this.serializeStorage = config.serializeStorage;
    this.serializeStorageInfo = config.serializeStorageInfo;
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
  // #endregion

  @Override
  public String toString() {
    return "Config [version=" + version + ", serializedRoot=" + serializedRoot + ", serializeEverything="
        + serializeEverything + ", serializeStorage=" + serializeStorage + ", serializeStorageInfo="
        + serializeStorageInfo + "]";
  }

}
