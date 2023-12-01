package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import error.SerializedObjectNotFound;
import model.Product;
import service.ServiceConfig;
import service.ServiceStock;

public class MainWindows {

    private static Scanner scanner = new Scanner(System.in);
    private static ServiceConfig serviceConfig = new ServiceConfig();
    private static ServiceStock stock = new ServiceStock(new ArrayList<>());

    // talvez alterar o construtor
    static {
        if(Boolean.parseBoolean((String) serviceConfig.getPropObject("serializedStock"))){
            try {
                stock = stock.getSavedObject();
            } catch(SerializedObjectNotFound e) {
                e.printStackTrace();
            }
        }
    }

    public static void mainWindows() {

        int inputValue;

        do {
            System.out.println("Gerenciador de estoque ");
            System.out.println("1 - sair");
            System.out.println("2 - Adicionar Produto");
            System.out.println("3 - Ver Produtos");
            System.out.println("4 - Editar Produto");
            System.out.println("5 - Ver produtos a baixo da quantidade");
            System.out.println("6 - Adicionar quantidade de produto");
            System.out.println("7 - Remover quantidade de produto");
            System.out.println("8 - Remover Produto");

            inputValue = scanner.nextInt();

            switch (inputValue) {
                case 1:
                    if(Boolean.parseBoolean((String) serviceConfig.getPropObject("serializedStock"))){
                        stock.saveObject();
                    }
                    break;
                case 2:
                    addProductWindows();
                    break;
                case 3:
                    findAllProductsWindows();
                    break;
                case 4:
                    updateProductsWindows();
                    break;
                case 5:
                    seeLowOnStockProductsWindows();
                    break;
                case 6:
                    addToStockWindows();
                    break;
                case 7:
                    removeFromStockWindows();
                    break;
                case 8:
                    deleteWindows();
                    break;
                default:
                    break;
            }

        } while (inputValue != 1);

    }

    private static void addProductWindows() {
        System.out.println("Nome do Prouto : ");
        String name = scanner.next();

        System.out.println("Descrisao do Prouto : ");
        String description = scanner.next();

        System.out.println("Valor do Prouto : ");
        Double price = scanner.nextDouble();

        System.out.println("Quantidade do Prouto : ");
        Integer quantity = scanner.nextInt();

        System.out.println("Quantidade minima do Prouto : ");
        Integer minimumQuantity = scanner.nextInt();

        Product product = new Product(name, description, price, quantity, minimumQuantity);

        System.out.println(product.toString() + "\n");

        stock.save(product);

    }

    private static void updateProductsWindows() {

        try {
            System.out.println("informe o id do prouto : ");
            UUID id = UUID.fromString(scanner.next());

            System.out.println("Nome do Prouto : ");
            String name = scanner.next();

            System.out.println("Descrisao do Prouto : ");
            String description = scanner.next();

            System.out.println("Valor do Prouto : ");
            Double price = scanner.nextDouble();

            System.out.println("Quantidade do Prouto : ");
            Integer quantity = scanner.nextInt();

            System.out.println("Quantidade minima do Prouto : ");
            Integer minimumQuantity = scanner.nextInt();

            stock.update(new Product(name, description, price, quantity, minimumQuantity), id);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static void findAllProductsWindows() {
        System.out.println("Produtos no estoque :");

        System.out.println(stock.findAll());

    }

    private static void seeLowOnStockProductsWindows() {
        System.out.println("Produtos a baixo do estoque : \n");

        System.out.println(stock.seeLowOnStockProducts().toString());
    }

    private static void addToStockWindows() {

        try {
            System.out.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.out.println("Quantidade :");
            int quantity = scanner.nextInt();

            System.out.println("Qunatidade inserida : \n");
            System.out.println(stock.addToStock(id, quantity).toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static void removeFromStockWindows() {
        try {
            System.out.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.out.println("Quantidade :");
            int quantity = scanner.nextInt();

            System.out.println("Qunatidade inserida : \n");
            System.out.println(stock.removeFromStock(id, quantity).toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static void deleteWindows() {
        try {
            System.out.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.out.println(stock.delete(id).toString());
        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

}
