package test.model.configClass;

import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import model.Config;

public class GetPropObjectTest {
  
  @AfterEach
  void afterEach() {
    Config.resetInstance();
    deleteDir(new File(System.getProperty("user.dir") + "\\config\\"));
  }

  @Test
  public void getFromDefault() {
    Config c = Config.getInstance();
    assertEquals(c.getPropObject("version"), c.getVersion());
    
    afterEach();
  }

  @Test
  public void getFromSetted() {
    Config c = Config.getInstance();
    c.setVersion("0.0.2");

    Config.resetInstance();

    assertEquals(c.getPropObject("version"), "0.0.2");

    afterEach();
  }

  @Test
  public void getUndefined() {
    Config c = Config.getInstance();
    assertNull(c.getPropObject(UUID.randomUUID().toString()));

    afterEach();
  }

  @Test
  public void getNull() {
    Config c = Config.getInstance();
    assertNull(c.getPropObject(UUID.randomUUID().toString()));
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
