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
    ServiceConfig serviceConfig = new ServiceConfig();
    assertEquals("0.0.1", serviceConfig.getPropObject("version"));

    afterEach();
  }

  @Test
  public void getFromSetted() {
    ServiceConfig serviceConfig = new ServiceConfig();
    serviceConfig.setPropObject("version", "0.0.2");

    Config.resetInstance();

    assertEquals("0.0.2", serviceConfig.getPropObject("version"));

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
    if (!dir.isDirectory())
      return;

    for (File file : dir.listFiles()) {
      if (file.isDirectory())
        deleteDir(file);
      file.delete();
    }

    dir.delete();
  }
}
