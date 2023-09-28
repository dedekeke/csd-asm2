package org.asm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OperationToProduct {
    // Implement the required methods: getAllItemsFromFile, writeAllItemsToFile, searchByCode,
    // deleteByCode, sortByCode, convertToBinary, addLast, and addFirst
    public void getAllItemsFromFile(String filename, MyList<Product> productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 4) {
                    String code = data[0].trim();
                    String name = data[1].trim();
                    int quantity = Integer.parseInt(data[2].trim());
                    double price = Double.parseDouble(data[3].trim());

                    Product product = new Product(code, name, quantity, price);
                    productList.insertToTail(product);
                } else {
                    System.out.println("Invalid data format in the file: " + filename);
                    System.out.println(data.length);
                }
            }
            System.out.println("Data loaded from file: " + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + filename);
            System.out.println(e);
        }
    }

    public void writeAllItemsToFile(String fileName, MyList<Product> productList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            Node<Product> current = productList.getHead();
            while (current != null) {
                writer.write(current.getInfo().toString() + "\n");
                current = current.getNext();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByCode(MyList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product code (bcode) to search for: ");
        String searchCode = scanner.next();

        Node<Product> current = productList.getHead();
        while (current != null) {
            if (current.getInfo().getBcode().equalsIgnoreCase(searchCode)) {
                System.out.println("Product found:");
                System.out.println(current);
                return;
            }
            current = current.getNext();
        }
        System.out.println("Product with code " + searchCode + " not found.");
    }

    public void deleteByCode(MyList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product code (bcode) to delete: ");
        String deleteCode = scanner.next();

        Node<Product> current = productList.getHead();
        while (current != null) {
            if (current.getInfo().getBcode().equalsIgnoreCase(deleteCode)) {
                productList.deleteElement(current.getInfo());
                System.out.println("Product with code " + deleteCode + " deleted successfully.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Product with code " + deleteCode + " not found.");
    }

    public void sortByCode(MyList<Product> productList) {
        int n = productList.length();
        for (int i = 0; i < n - 1; i++) {
            Node<Product> current = productList.getHead();
            for (int j = 0; j < n - i - 1; j++) {
                if (current.getNext() != null &&
                        current.getInfo().getBcode().compareToIgnoreCase(current.getNext().getInfo().getBcode()) > 0) {
                    productList.swap(current, current.getNext());
                }
                current = current.getNext();
            }
        }
    }

    public void convertToBinary(int number) {
        MyStack<Integer> stack = new MyStack<>();
        while (number > 0) {
            stack.push(number % 2);
            number /= 2;
        }
        System.out.print("Binary: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    public void addLast(MyList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product details:");
        System.out.print("ID (bcode): ");
        String bcode = scanner.next();
        System.out.print("Title: ");
        String title = scanner.next();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Product newProduct = new Product(bcode, title, quantity, price);
        productList.insertAfterPosition(productList.length() - 1, newProduct);
        System.out.println("Product added successfully!");
    }

    public void addFirst(MyList<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product details:");
        System.out.print("ID (bcode): ");
        String bcode = scanner.next();
        System.out.print("Title: ");
        String title = scanner.next();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Product newProduct = new Product(bcode, title, quantity, price);
        productList.insertToHead(newProduct);
        System.out.println("Product added successfully!");
    }
}
