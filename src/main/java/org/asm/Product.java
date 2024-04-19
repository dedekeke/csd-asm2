package org.asm;

import java.util.HashSet;

public record Product(String bcode, String title, int quantity, double price) {
    private static final HashSet<String> existed = new HashSet<>();

    public Product {
        if (!existed.add(bcode)) {
            throw new IllegalArgumentException("Invalid bcode");
        }
    }

    @Override
    public String toString() {
        return bcode + " | " + title + " | " + quantity + " | " + price;
    }
}
