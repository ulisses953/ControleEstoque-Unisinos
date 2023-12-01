package test.service.serviceConfigClass;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;
import service.ServiceConfig;

public class DeletePropsTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void deleteAsExpected() {
    ServiceConfig serviceConfig = new ServiceConfig();
    serviceConfig.deleteProps();

    assertFalse(new File(System.getProperty("user.dir") + "\\config\\datamodeL.Config.properties").exists());
    afterEach();
  }

  @Test
  public void notThrowOnDeleteTwiceCall() {
    ServiceConfig serviceConfig = new ServiceConfig();
    serviceConfig.deleteProps();
    assertDoesNotThrow(() -> serviceConfig.deleteProps());
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
