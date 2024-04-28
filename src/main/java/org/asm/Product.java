package org.asm;

import java.util.HashSet;

public record Product(String bcode, String title, int quantity, double price) {

    private static final HashSet<String> existed = new HashSet<>();

    public static Product addProduct(String bcode, String title, int quantity, double price) {
        if (!existed.contains(bcode)) {
            existed.add(bcode);
            return new Product(bcode, title, quantity, price);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return bcode + " | " + title + " | " + quantity + " | " + price;
    }
}
