package model;

import java.io.*;

import service.ServiceConfig;

public class Config implements Serializable {
  private static Config instance = null;
  public final String PROPS_PATH = System.getProperty("user.dir") + "\\config\\";
  private static final long serialVersionUID = 1L;

  private String version = "0.0.1";
  private String serializeRootPath = System.getProperty("user.dir") + "\\serializedData\\";
  private boolean serializeEverything = false;
  private boolean serializedStock = false;

  private ServiceConfig serviceConfig;

  //#region constructor
  private Config() {
  }
  
  public static Config getInstance() {
    if(instance == null){
      instance = new Config();
      instance.serviceConfig = new ServiceConfig();
      instance.serviceConfig.getPropsObject();
    }      
    return instance;
  }
  //#endregion constructor

  // #region get and set
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    serviceConfig.setPropObject("version", version);
  }

  public String getSerializeRootPath() {
    return serializeRootPath;
  }

  public void setSerializeRootPath(String serializedRoot) {
    serviceConfig.setPropObject("serializeRootPath", serializedRoot);
  }

  public boolean isSerializeEverything() {
    return serializeEverything;
  }

  public void setSerializeEverything(boolean serializeEverything) {
    serviceConfig.setPropObject("serializeEverything", Boolean.toString(serializeEverything));
  }

  public boolean isSerializeStock() {
    return serializedStock;
  }
  
  public void setSerializeStock(boolean serializeStorage) {
    serviceConfig.setPropObject("serializedStock", Boolean.toString(serializeStorage));
  }
  // #endregion
  
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
