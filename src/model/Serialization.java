package model;

import java.io.*;

import interfaces.Serialize;

public class Serialization<T> implements Serialize<T> {
  private Config config = Config.getInstance();

  public Serialization() {
  }

  public void serialize(T o) {
    try {
      File directory = new File(config.getSerializedRoot());
      if (!directory.exists()) {
        directory.mkdir();
      }

      String path = config.getSerializedRoot() + "\\" + o.getClass().getName() + ".ser";
      FileOutputStream fileOut = new FileOutputStream(path);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(o);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public T deserialize(T object) {
    T o = object;
    try {
      String root = config.getSerializedRoot() + "\\" + o.getClass().getName() + ".ser";
      System.out.println(root);
      FileInputStream fileIn = new FileInputStream(root);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object obj = in.readObject();
      if (o.getClass().isInstance(obj)) {
        o = (T) obj;
      }
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return o;
  }
}