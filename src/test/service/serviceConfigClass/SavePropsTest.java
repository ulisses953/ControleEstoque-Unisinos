package test.service.serviceConfigClass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class SavePropsTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }
  
  @Test
  public void saveOnDefault() {
    Config c = Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    assertTrue(filePath.exists());

    afterEach();    
  }

  @Test
  public void saveSettedFile() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setSerializeEverything(true);
    c.setVersion("0.0.2");
    serviceConfig.saveProps();

    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void propsAreSaved() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    Properties properties = new Properties();
    Properties newProperties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      properties.load(fileInputStream);

      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    serviceConfig.setPropObject("version", "0.0.2");
    serviceConfig.saveProps();
    
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      newProperties.load(fileInputStream);

      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    assertEquals(properties.getProperty("version"), newProperties.getProperty("version"));

    afterEach();
  }

  @Test
  public void ignoreFields() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    Properties properties = new Properties();

    c.setVersion("0.0.2");
    serviceConfig.saveProps();

    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      properties.load(fileInputStream);

      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    assertNull(properties.getProperty("serialVersionUID"));
    assertNull(properties.getProperty("instance"));
    assertNull(properties.getProperty("propertiesPath"));

    afterEach();
  }

  public void deleteDir(File dir) {
    if(!dir.isDirectory()) return;

    for(File file : dir.listFiles()) {
      if(file.isDirectory()) deleteDir(file);
      file.delete();
    }

    dir.delete();
  }
}
