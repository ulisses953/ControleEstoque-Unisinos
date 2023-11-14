import model.Config;

public class App {
    public static void main(String[] args) {
        // Obter a instância Config
        Config config = Config.getInstance();

        System.out.println(config);

        // Usar os métodos setter
        config.setVersion("0.0.2");
        config.setSerializeEverything(true);
        // ...

        System.out.println(config);

        // Salvar as alterações
        config.save();
    }
}
