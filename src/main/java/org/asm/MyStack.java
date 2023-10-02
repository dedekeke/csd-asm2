package org.asm;

public class MyStack<T> {
    private Node<T> head;


    public MyStack() {
        head = null;
    }

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


