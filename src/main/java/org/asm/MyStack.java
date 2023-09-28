package org.asm;
// TODO record
public class MyStack<T> {
    private Node<T> head;

    // Constructor
    public MyStack() {
        head = null;
    }

    // Implement the required methods: push, pop, isEmpty, and displayAll
    public void push(T item) {
        head = new Node<>(item, head);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = head.getInfo();
        head = head.getNext();
        return item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    public void displayAll() {
        Node<T> current = head;
        System.out.println("ID | Title | Quantity | Price");
        System.out.println("--------------------------------");
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
}


