package org.asm;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OperationToProduct {
    // Implement the required methods: getAllItemsFromFile, writeAllItemsToFile,
    // searchByCode,
    // deleteByCode, sortByCode, convertToBinary, addLast, and addFirst
    private static final Logger logger = Logger.getLogger(Main.class);
    Scanner scanner = new Scanner(System.in);

    public void getAllItemsFromFile(String filename, MyList<Product> productList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                // check if line empty
                if (line.isEmpty()) {
                    return;
                }
                if (data.length == 4) {
                    String code = data[0].trim();
                    String name = data[1].trim();
                    int quantity = Integer.parseInt(data[2].trim());
                    double price = Double.parseDouble(data[3].trim());

                    Product product = Product.addProduct(code, name, quantity, price);
                    productList.insertToTail(product);
                } else {
                    System.out.println("Invalid data format in the file: " + filename);
                }
            }
            System.out.println("Data loaded from file: " + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + filename);
            logger.error("An IOException occurred:", e);
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
            logger.error("An IOException occurred:", e);
        }
    }

    public void searchByCode(MyList<Product> productList) {
        System.out.print("Enter the product code (bcode) to search for: ");
        String searchCode = scanner.next();

        Node<Product> current = productList.getHead();
        if (current == null) {
            System.out.println("Product list is empty.");
            return;
        }
        while (current != null) {
            if (current.getInfo().bcode().contains(searchCode)) {
                if (current.getInfo().bcode().equals(searchCode)) {
                    System.out.println(current);
                } else {
                    System.out.println(current);
                }
            }
            current = current.getNext();
        }
    }

    public void deleteByCode(MyList<Product> productList) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the product code (bcode) to delete: ");
            String deleteCode = scanner.next();

            Node<Product> current = productList.getHead();
            while (current != null) {
                if (current.getInfo().bcode().equalsIgnoreCase(deleteCode)) {
                    productList.deleteElement(current.getInfo());
                    System.out.println("Product with code " + deleteCode + " deleted successfully.");
                    // 1
                    return;
                }
                current = current.getNext();
            }
            System.out.println("Product with code " + deleteCode + " not found.");
        }
    }

    public void sortByCode(MyList<Product> productList) {
        quickSort(productList.getHead(), null);
    }

    private void quickSort(Node<Product> head, Node<Product> end) {
        if (head == null || head == end) {
            return;
        }

        Node<Product> partitionNode = partitionByCode(head, end);
        quickSort(head, partitionNode);
        quickSort(partitionNode.getNext(), end);
    }

    private Node<Product> partitionByCode(Node<Product> start, Node<Product> end) {
        Node<Product> pivot;
        pivot = end;
        Node<Product> prev = null;
        Node<Product> curr = start;
        Node<Product> tail = pivot;

        while (curr != pivot) {
            if (Long.parseLong(curr.getInfo().bcode()) < Long.parseLong(pivot.getInfo().bcode())) {
                if (prev == null) {
                    prev = curr;
                } else {
                    prev = swapNodes(prev, curr);
                }
                curr = curr.getNext();
            } else {
                tail = curr;
                Node<Product> temp = partitionByCode(curr, pivot);
                curr = temp.getNext();
                temp.setNext(null);
                tail = temp;
            }
        }

        if (prev != null) {
            tail = swapNodes(tail, pivot);
        }

        return tail;
    }

    private Node<Product> swapNodes(Node<Product> node1, Node<Product> node2) {
        Product temp = node1.getInfo();
        node1.setInfo(node2.getInfo());
        node2.setInfo(temp);
        return node2;
    }

    public int convertToBinary(int n) {
        if (n == 1) {
            return 1;
        }
        return convertToBinary(n / 2) * 10 + n % 2;
    }

    public void addLast(MyList<Product> productList) {
        System.out.println("Enter product details:");
        System.out.print("ID (bcode): ");
        String bcode = scanner.next();
        System.out.print("Title: ");
        String title = scanner.next();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        Product newProduct = Product.addProduct(bcode, title, quantity, price);
        if (newProduct != null) {
            productList.insertAfterPosition(productList.length(), newProduct);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product with bcode '" + bcode + "' already exists. Product not added.");
        }
    }

    public void addFirst(MyList<Product> productList) {
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
