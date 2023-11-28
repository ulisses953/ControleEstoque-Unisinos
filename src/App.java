import model.Config;

public class App {
  public static void main(String[] args) {
    Config c = Config.getInstance();

    c.saveObject();
  }
}
