package org.asm;

public record Product(String bcode, String title, int quantity, double price) {

    @Override
    public String toString() {
        return bcode + " | " + title + " | " + quantity + " | " + price;
    }
}
