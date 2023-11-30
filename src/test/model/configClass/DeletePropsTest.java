package test.model.configClass;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;

public class DeletePropsTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void deleteAsExpected() {
    Config c = Config.getInstance();
    File dir = new File(System.getProperty("user.dir") + "\\config\\");
    boolean value = dir.exists();
    
    c.deleteProps();
    for(File file : dir.listFiles()) {
      if(file.getName().equals("dataConfig.properties")) {
        value = false;
      };
    }

    assertTrue(value);
    afterEach();
  }

  @Test
  public void notThrowOnDeleteTwiceCall() {
    Config c = Config.getInstance();    
    c.deleteProps();
    assertDoesNotThrow(() -> c.deleteProps());
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
