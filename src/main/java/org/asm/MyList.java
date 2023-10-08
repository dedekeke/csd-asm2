package org.asm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyList<T> {

    private Node<T> head;
    private Node<T> tail;

    public MyList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public void insertToHead(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void insertAfterPosition(int position, T item) {
        int listLength = length();
        if (position < 0 || position > listLength) {
            System.out.println("Invalid position.");
            return;
        }

        if (position == 0) {
            insertToHead(item);
            return;
        }

        if (position == listLength) {
            insertToTail(item);
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.getNext();
        }

        Node<T> newNode = new Node<>(item, current.getNext());
        current.setNext(newNode);

        if (current == tail) {
            tail = newNode;
        }
    }

    public void deleteTail() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        Node<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
    }

    public void deleteElement(T item) {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        if (head.getInfo().equals(item)) {
            head = head.getNext();
            if (tail.getInfo().equals(item)) {
                tail = null;
            }
            return;
        }

        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getInfo().equals(item)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == null) {
                tail = current;
            }
        }
    }

    public void swap(Node<T> firstNode, Node<T> secondNode) {
        if (firstNode == null || secondNode == null) {
            System.out.println("Invalid nodes to swap.");
            return;
        }

        T temp = firstNode.getInfo();
        firstNode.setInfo(secondNode.getInfo());
        secondNode.setInfo(temp);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public Node<T> getNodeAtPosition(int position) {
        if (position < 0 || position >= length()) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void insertToTail(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }


    public void displayAll() {
        Node<T> current = head;
        if (current == null) {
            System.out.println("Nothing's here");
        }
        while (current != null) {
            System.out.println(current.getInfo().toString());
            current = current.getNext();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                T item = (T) line;
                insertToTail(item);
            }
            System.out.println("Data loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    public void shuffle() {
        List<Node<T>> nodeList = this.getNodesAsList();
        Collections.shuffle(nodeList);
        this.head = nodeList.get(0);
        Node<T> current = this.head;
        for (int i = 1; i < nodeList.size(); i++) {
            current.setNext(nodeList.get(i));
            current = current.getNext();
        }
        current.setNext(null);
        this.tail = current;
    }

    private List<Node<T>> getNodesAsList() {
        List<Node<T>> nodeList = new ArrayList<>();
        Node<T> current = this.head;
        while (current != null) {
            nodeList.add(current);
            current = current.getNext();
        }
        return nodeList;
    }
}
