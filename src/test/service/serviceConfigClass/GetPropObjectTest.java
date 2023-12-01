package test.service.serviceConfigClass;

import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class GetPropObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void getFromDefault() {
    Config c = Config.getInstance();
    ServiceConfig serviceConfig = new ServiceConfig();
    assertEquals(serviceConfig.getPropObject("version"), c.getVersion());
    
    afterEach();
  }

  @Test
  public void getFromSetted() {
    ServiceConfig serviceConfig = new ServiceConfig();
    Config c = Config.getInstance();
    c.setVersion("0.0.2");

    Config.resetInstance();

    assertEquals(serviceConfig.getPropObject("version"), "0.0.2");

    afterEach();
  }

  @Test
  public void getUndefined() {
    ServiceConfig serviceConfig = new ServiceConfig();
    assertNull(serviceConfig.getPropObject(UUID.randomUUID().toString()));

    afterEach();
  }

  @Test
  public void getNull() {
    ServiceConfig serviceConfig = new ServiceConfig();
    assertNull(serviceConfig.getPropObject(UUID.randomUUID().toString()));
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
