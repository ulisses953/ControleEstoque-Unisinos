import java.io.Serializable;

import model.Produto;
import model.Serialization;

public class App implements Serializable {
    public static void main(String[] args) {
        Serialization<Produto> ser = new Serialization<>();
        Produto p = new Produto();
        ser.serialize(p);
    }
}
