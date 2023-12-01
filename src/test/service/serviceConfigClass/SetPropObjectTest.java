package test.service.serviceConfigClass;

import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class SetPropObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    // deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void changeDefaultProp() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    serviceConfig.setPropObject("version", "0.0.2");
    assertEquals("0.0.2", serviceConfig.getPropObject("version"));
    assertEquals("0.0.2", c.getVersion());
    afterEach();
  }

  @Test
  public void keepsBooleanType() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    serviceConfig.setPropObject("serializeEverything", "true");
    assertTrue(c.isSerializeEverything());

    afterEach();
  }

  public void keepsStringType() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    assertEquals("0.0.2", serviceConfig.getPropObject("version"));
    assertEquals("0.0.2", c.getVersion());
    afterEach();
  }

  @Test
  public void saveNotBooleanValuesAsBoolean() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    serviceConfig.setPropObject("serializeEverything", UUID.randomUUID().toString());
    assertFalse(c.isSerializeEverything());
    assertTrue(serviceConfig.getPropObject("serializeEverything").equals("false"));

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
