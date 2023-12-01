package test.service.serviceConfigClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class SaveObjectTest {

  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
    deleteDir(new File(Config.getInstance().getSerializeRootPath()));
  }

  @Test
  public void saveFile() {
    ServiceConfig serviceConfig = new ServiceConfig();
    serviceConfig.saveObject();
    File file = new File(System.getProperty("user.dir") + "\\serializedData\\model.Config.ser");
    assertTrue(file.exists());
    
    afterEach();
  }

  @Test
  public void savesCorrectly() {
    ServiceConfig serviceConfig = new ServiceConfig();
    serviceConfig.saveObject();
    Config config = serviceConfig.getSavedObject();
    Config c = Config.getInstance();
    assertEquals(c, config);

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
