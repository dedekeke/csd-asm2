package org.asm;

import java.util.Scanner;

public class Main {

    public static void showMenu() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("1. Load data from file and add to the end");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save product list to file");
        System.out.println("5. Search by product code");
        System.out.println("6. Delete by product code");
        System.out.println("7. Sort by product code");
        System.out.println("8. Convert first item's quantity to binary");
        System.out.println("9. Load data from file and add to stack");
        System.out.println("10. Load data from file and add to queue");
        System.out.println("11. Shuffle!!!");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyList<Product> productList = new MyList<>();
        MyStack<Product> productStack = new MyStack<>();
        MyQueue<Product> productQueue = new MyQueue<>();
        OperationToProduct operationToProduct = new OperationToProduct();

        int choice;

        do {
            showMenu();
            System.out.print("choice = ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    operationToProduct.getAllItemsFromFile("data.txt", productList);
                    productList.displayAll();
                }
                case 2 -> operationToProduct.addLast(productList);
                case 3 -> productList.displayAll();
                case 4 -> {
                    operationToProduct.writeAllItemsToFile("data.txt", productList);
                    System.out.println("Product list saved successfully!");
                }
                case 5 -> operationToProduct.searchByCode(productList);
                case 6 -> operationToProduct.deleteByCode(productList);
                case 7 -> {
                    operationToProduct.sortByCode(productList);
                    System.out.println("Product list sorted successfully!");
                }
                case 8 -> {
                    int n = productList.getHead().getInfo().quantity();
                    int result = operationToProduct.convertToBinary(n);
                    System.out.println("Current number: " + n  + " to binary: " + result);
                }
                case 9 -> {
                    Node<Product> currentStack = productList.getHead();
                    while (currentStack != null) {
                        productStack.push(currentStack.getInfo());
                        currentStack = currentStack.getNext();
                    }
                    productStack.displayAll();
                }
                case 10 -> {
                    Node<Product> currentQueue = productList.getHead();
                    while (currentQueue != null) {
                        productQueue.enqueue(currentQueue.getInfo());
                        currentQueue = currentQueue.getNext();
                    }
                    productQueue.displayAll();
                }
                case 11 -> {
                    productList.shuffle();
                    System.out.println("Product list shuffled successfully!");
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
