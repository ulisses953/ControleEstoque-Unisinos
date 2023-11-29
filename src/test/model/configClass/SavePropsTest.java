package test.model.configClass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;

public class SavePropsTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }
  
  @Test
  public void saveOnDefault() {
    Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\dataConfig.properties");
    assertTrue(filePath.exists());

    afterEach();    
  }

  @Test
  public void saveSettedFile() {
    Config c = Config.getInstance();
    c.setSerializeEverything(true);
    c.setVersion("0.0.2");
    c.saveProps();

    File filePath = new File(System.getProperty("user.dir") + "\\config\\dataConfig.properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void propsAreSaved() {
    Config c = Config.getInstance();
    String pathName = System.getProperty("user.dir") + "\\config\\dataConfig.properties";
    Properties properties = new Properties();
    Properties newProperties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream(pathName);
      properties.load(fileInputStream);

      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    c.setVersion("0.0.2");
    c.saveProps();
    
    try {
      FileInputStream fileInputStream = new FileInputStream(pathName);
      newProperties.load(fileInputStream);

      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    assertNotEquals(properties.getProperty("version"), newProperties.getProperty("version"));

    afterEach();
  }

  @Test
  public void ignoreFields() {
    Config c = Config.getInstance();
    String pathName = System.getProperty("user.dir") + "\\config\\dataConfig.properties";
    Properties properties = new Properties();

    c.setVersion("0.0.2");
    c.saveProps();

    try {
      FileInputStream fileInputStream = new FileInputStream(pathName);
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
