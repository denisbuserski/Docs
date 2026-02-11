package com.demo;

import java.util.Scanner;

public class PurchasedItem {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name:");
        String name1 = scanner.nextLine();
        System.out.println("Enter unit price:");
        double up = scanner.nextDouble();
        System.out.println("Enter weight:");
        double w = scanner.nextDouble();

        WeighedItem item1 = new WeighedItem(name1, up, w);

        scanner.nextLine();
        System.out.println("Enter name:");
        String name2 = scanner.nextLine();
        System.out.println("Enter unit price:");
        double up1 = scanner.nextDouble();
        System.out.println("Enter quantity:");
        int q = scanner.nextInt();

        CountedItem item2 = new CountedItem(name2, up1, q);

        System.out.println(item1);
        System.out.println(item2);
    }
}

class PurchaseItem {
    private String name;
    private double unitPrice;

    public PurchaseItem(String name, double unitPrice) {
        this.name = name;
        this.setPrice(unitPrice);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double unitPrice) {
        this.unitPrice = (unitPrice > 0) ? unitPrice : 0;
    }

    public double getPrice() {
        return unitPrice;
    }

    public String toString() {
        return getName() + "@ " + getPrice();
    }
}

class CountedItem extends PurchaseItem {
    private int quantity;

    public CountedItem(String name, double unitPrice, int quantity) {
        super(name, unitPrice);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.quantity + " units " + (getPrice() * quantity) + " $"; // "CountedItem{" + "quantity=" + quantity + '}';
    }
}

class WeighedItem extends PurchaseItem {
    private double weightKg;

    public WeighedItem(String name, double unitPrice, double weightKg) {
        super(name, unitPrice);
        this.weightKg = weightKg;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.weightKg + " Kg " + (getPrice() * weightKg) + " $";
    }
}
