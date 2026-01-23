package com.demo;

import java.util.Scanner;

public class Circle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        System.out.println("Enter radius:");
        double rad = scanner.nextDouble();
        System.out.println("Enter color:");
        String col = scanner.next();

        Circle mc = new Circle(rad, col);

        System.out.println(mc.getArea());
        System.out.println(mc.color);
    }
    public double radius;
    public String color;

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}
