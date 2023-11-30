package test.model.configClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;

public class SaveObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\serializedData\\"));
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void saveFile() {
    Config c = Config.getInstance();
    c.saveObject();
    File file = new File(System.getProperty("user.dir") + "\\serializedData\\model.Config.ser");
    assertTrue(file.exists());
    
    afterEach();
  }

  @Test
  public void savesCorrectly() {
    Config c = Config.getInstance();
    c.saveObject();
    Config config = c.getSavedObject();
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
