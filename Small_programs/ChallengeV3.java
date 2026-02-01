package com.demo;

import java.util.Scanner;

public class Challenge {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        Book[] book = new Book[2];

        System.out.println("Title of fiction book:");
        String t = scanner.nextLine();
        book[0] = new Fiction(t);

        System.out.println("Title of non fiction book:");
        String t1 = scanner.nextLine();
        book[1] = new NonFiction(t1);

        for (int i = 0; i < book.length; i++) {
            System.out.println(book[i]);
        }
    }
}

abstract class Book {
    private String title;
    private double price;

    public Book(String title) {
        this.title = title;
        this.setPrice(setPrice());
    }

    public String getTitle() {
        return title;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public abstract double setPrice();

    @Override
    public String toString() {
        return "Title -" + getTitle() + " Cost - $" + getPrice();
    }
}

class Fiction extends Book {
    private double price;

    public Fiction(String title) {
        super(title);
        this.setPrice();
    }

    @Override
    public double setPrice() {
        return 24.99;
    }
}

class NonFiction extends Book {
    private double price;

    public NonFiction(String title) {
        super(title);
        this.setPrice();
    }

    @Override
    public double setPrice() {
        return 37.99;
    }
}
