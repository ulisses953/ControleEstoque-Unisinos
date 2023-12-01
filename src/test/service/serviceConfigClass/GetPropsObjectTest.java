package test.service.serviceConfigClass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class GetPropsObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void createsPropsByDefault() {
    Config c = Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void createPropsWithDefaultValues() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    Config config = serviceConfig.getPropsObject();

    assertEquals(c, config);

    afterEach();
  }

  @Test
  public void getSettedBooleanProps() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setSerializeStock(true);
    Config.resetInstance();
    serviceConfig.getPropsObject();

    assertTrue(c.isSerializeStock());
    afterEach();
  }

  @Test
  public void getSettedStringProps() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    Config.resetInstance();
    serviceConfig.getPropsObject();

    assertEquals("0.0.2", c.getVersion());

    afterEach();
  }

  @Test
  public void keepsSerialUID() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    c.setSerializeEverything(false);

    Config.resetInstance();
    serviceConfig.getPropsObject();
    
    assertEquals(1L, Config.getSerialversionuid());

    afterEach();
  }

  @Test
  public void createsDefaultFileIfDeleted() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    serviceConfig.deleteProps();
    serviceConfig.getPropsObject();

    File filePath = new File(System.getProperty("user.dir") + "\\config\\data" + c.getClass().getName() + ".properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void doesNotThrow() {
    ServiceConfig serviceConfig = new ServiceConfig();
    assertDoesNotThrow(() -> serviceConfig.getPropsObject());

    afterEach();
  }

  @Test
  public void defaultIsNotNull() {
    Config c = Config.getInstance();
    assertNotNull(c);

    afterEach();
  }

  @Test
  public void settedObjectIsNotNull() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    Config.resetInstance();
    serviceConfig.getPropsObject();

    assertNotNull(c);

    afterEach();
  }

  @Test
  public void propertiesAreEquals() {
    ServiceConfig serviceConfig = new ServiceConfig();
    String pathName = System.getProperty("user.dir") + "\\config\\datamodel.Config.properties";
    Properties properties = new Properties();
    Properties newProperties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream(pathName);
      properties.load(fileInputStream);
      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    Config.resetInstance();
    serviceConfig.getPropsObject();
    
    try {
      FileInputStream fileInputStream = new FileInputStream(pathName);
      newProperties.load(fileInputStream);
      fileInputStream.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    assertEquals(properties, newProperties);
    
    afterEach();
  }

  @Test
  public void getWithDifferentType() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    serviceConfig.setPropObject("serializeEverything", "string");
    serviceConfig.getPropsObject();
    
    assertFalse(c.isSerializeEverything());

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
