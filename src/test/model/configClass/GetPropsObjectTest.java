package test.model.configClass;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;

public class GetPropsObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void createsPropsByDefault() {
    Config.getInstance();
    File filePath = new File(System.getProperty("user.dir") + "\\config\\dataConfig.properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void createPropsWithDefaultValues() {
    Config c = Config.getInstance();
    Config config = c.getPropsObject();

    assertEquals(c, config);

    afterEach();
  }

  @Test
  public void getSettedBooleanProps() {
    Config c = Config.getInstance();
    c.setSerializeStock(true);
    Config.resetInstance();
    c.getPropsObject();

    assertTrue(c.isSerializeStock());
    afterEach();
  }

  @Test
  public void getSettedStringProps() {
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    Config.resetInstance();
    c.getPropsObject();

    assertEquals("0.0.2", c.getVersion());

    afterEach();
  }

  @Test
  public void keepsSerialUID() {
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    c.setSerializeEverything(false);

    Config.resetInstance();
    c.getPropsObject();
    
    assertEquals(1L, Config.getSerialversionuid());

    afterEach();
  }

  @Test
  public void createsDefaultFileIfDeleted() {
    Config c = Config.getInstance();
    c.deleteProps("dataConfig.properties");
    c.getPropsObject();

    File filePath = new File(System.getProperty("user.dir") + "\\config\\dataConfig.properties");
    assertTrue(filePath.exists());

    afterEach();
  }

  @Test
  public void doesNotThrow() {
    Config c = Config.getInstance();
    assertDoesNotThrow(() -> c.getPropsObject());

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
    Config c = Config.getInstance();
    c.setVersion("0.0.2");
    Config.resetInstance();
    c.getPropsObject();

    assertNotNull(c);

    afterEach();
  }

  @Test
  public void propertiesAreEquals() {
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

    Config.resetInstance();
    c.getPropsObject();
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
    Config c = Config.getInstance();
    c.setPropObject("serializeEverything", "string");
    c.getPropsObject();
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
