package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import model.Product;
import service.ServiceStock;

public class MainWindows {

    private static Scanner scanner = new Scanner(System.in);
    private static ServiceStock stock = new ServiceStock(new ArrayList<>());

    public static void mainWindows() {

        boolean sair = false;

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

            switch (scanner.nextInt()) {
                case 1:
                    sair = true;
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

        } while (sair == false);

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

}
