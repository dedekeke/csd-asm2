package org.asm;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = head.getInfo();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return item;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void displayAll() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getInfo().toString());
            current = current.getNext();
        }
    }
}



