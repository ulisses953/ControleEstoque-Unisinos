package model;

import java.io.*;

import interfaces.SerializableOperations;

public class SerializableManager<T> implements SerializableOperations<T> {
  private Config config = Config.getInstance();

  public SerializableManager () {
  }

  private String getFilePath(T object) {
    return config.getSerializedRoot() + "\\" + object.getClass().getName() + ".ser";
  }

  public void serialize(T object) {
    try {
      File directory = new File(config.getSerializedRoot());
      if (!directory.exists()) {
        directory.mkdir();
      }

      String path = getFilePath(object);
      FileOutputStream fileOut = new FileOutputStream(path);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(object);
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
      String path = getFilePath(object);
      System.out.println(path);
      FileInputStream fileIn = new FileInputStream(path);
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

  public void removeSerialization(T object) {
    String path = getFilePath(object);
    try {
      File file = new File(path);
      if (file.delete()) {
          System.out.println("Arquivo deletado com sucesso");
      } else {
          System.out.println("Falha ao deletar o arquivo");
      }
    } catch (Exception e) {
        System.out.println("Ocorreu um erro ao tentar deletar o arquivo");
        e.printStackTrace();
    }
  }
}