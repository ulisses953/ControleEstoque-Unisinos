package view;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import error.SerializedObjectNotFound;
import model.Config;
import model.Product;
import service.ServiceConfig;
import service.ServiceStock;

public class MainWindows {

    private static Scanner scanner = new Scanner(System.in);
    private static ServiceConfig serviceConfig = new ServiceConfig();
    private static Config config = Config.getInstance();
    private static ServiceStock stock = new ServiceStock(new ArrayList<>());

    static {
        if (Boolean.parseBoolean(serviceConfig.getPropObject("serializedStock"))) {
            try {
                stock = stock.getSavedObject();
            } catch (SerializedObjectNotFound e) {
                e.printStackTrace();
            }
        }
    }

    public static void mainWindows() {

        int inputValue;

        do {
            System.err.println("Gerenciador de estoque ");
            System.err.println("1 - sair");
            System.err.println("2 - Adicionar Produto");
            System.err.println("3 - Ver Produtos");
            System.err.println("4 - Editar Produto");
            System.err.println("5 - Ver produtos a baixo da quantidade");
            System.err.println("6 - Adicionar quantidade de produto");
            System.err.println("7 - Remover quantidade de produto");
            System.err.println("8 - Remover Produto");
            System.out.println("9 - Abrir configurações");

            inputValue = scanner.nextInt();

            switch (inputValue) {
                case 1:
                    if (Boolean.parseBoolean(serviceConfig.getPropObject("serializedStock"))) {
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
                case 9:
                    openConfig();
                default:
                    break;
            }

        } while (inputValue != 1);

    }

    private static void addProductWindows() {
        System.err.println("Nome do Prouto : ");
        String name = scanner.next();

        System.err.println("Descrisao do Prouto : ");
        String description = scanner.next();

        System.err.println("Valor do Prouto : ");
        Double price = scanner.nextDouble();

        System.err.println("Quantidade do Prouto : ");
        Integer quantity = scanner.nextInt();

        System.err.println("Quantidade minima do Prouto : ");
        Integer minimumQuantity = scanner.nextInt();

        Product product = new Product(name, description, price, quantity, minimumQuantity);

        System.err.println(product.toString() + "\n");

        stock.save(product);

    }

    private static void updateProductsWindows() {

        try {
            System.out.println("informe o id do prouto : ");
            UUID id = UUID.fromString(scanner.next());

            System.err.println("Nome do Prouto : ");
            String name = scanner.next();

            System.err.println("Descrisao do Prouto : ");
            String description = scanner.next();

            System.err.println("Valor do Prouto : ");
            Double price = scanner.nextDouble();

            System.err.println("Quantidade do Prouto : ");
            Integer quantity = scanner.nextInt();

            System.err.println("Quantidade minima do Prouto : ");
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
        System.err.println("Produtos a baixo do estoque : \n");

        System.err.println(stock.seeLowOnStockProducts().toString());
    }

    private static void addToStockWindows() {

        try {
            System.err.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.err.println("Quantidade :");
            int quantity = scanner.nextInt();

            System.err.println("Qunatidade inserida : \n");
            System.err.println(stock.addToStock(id, quantity).toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static void removeFromStockWindows() {
        try {
            System.err.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.err.println("Quantidade :");
            int quantity = scanner.nextInt();

            System.err.println("Qunatidade inserida : \n");
            System.err.println(stock.removeFromStock(id, quantity).toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static void deleteWindows() {
        try {
            System.err.println("informe o id : ");
            UUID id = UUID.fromString(scanner.next());

            System.err.println(stock.delete(id).toString());
        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    private static void openConfig() {
        System.out.println("\n\t[CONFIG]");

        final int[] a = { 0 };
        try {
            serviceConfig.getProperties().forEach((key, value) -> {
                System.out.println(a[0] + " - " + key + ": " + value);
                a[0] += 1;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter the variable name to change its value");
        System.out.println("or exit by typing 'exit'");

        scanner.nextLine();
        String value = scanner.nextLine();

        if (value.equals("exit"))
            return;
        try {
            setConfigValue(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setConfigValue(String a) throws IOException {
        System.out.println("Write the new value for " + a);
        System.out.println("or exit by typing 'exit'");

        String value = scanner.nextLine();

        if (value.equals("exit"))
            return;

        serviceConfig.setPropObject(a, value);
    }
}
