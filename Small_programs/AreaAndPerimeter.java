package com.demo;

import java.util.Scanner;

public class AreaAndPerimeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double area, peri;
        System.out.print("Menu-\n1. Circle\n2. Rectangle\n3. Square\nEnter your choice :");
        int ch = scanner.nextInt();


        switch (ch) {
            case 1:
                Circle circ = new Circle(scanner);
                area = circ.area();
                peri = circ.peri();
                System.out.print("Area of Circle is :" + area + "\nPerimeter of Circle is :" + peri);
                break;
            case 2:
                Rec rec = new Rec(scanner);
                area = rec.area();
                peri = rec.peri();
                System.out.print("Area of Rectangle is :" + area + "\nPerimeter of Rectangle is :" + peri);
                break;
            case 3:
                Square sqr = new Square(scanner);
                area = sqr.area();
                peri = sqr.peri();
                System.out.print("Area of Square is :" + area + "\nPerimeter of Square is :" + peri);
                break;
            default:
                System.out.print("Wrong choice");
        }
    }
}

interface Shape {
    double area();
    double peri();
}

class Circle implements Shape {
    private double radius;

    public Circle(Scanner scanner) {
        this.radius = scanner.nextInt();
    }

    @Override
    public double area() {
        return Math.PI + radius * radius;
    }

    @Override
    public double peri() {
        return 2 * Math.PI + radius;
    }
}

class Rec implements Shape {
    private double length;
    private double width;

    public Rec(Scanner scanner) {
        this.length = scanner.nextInt();
        this.width = scanner.nextInt();
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double peri() {
        return 2 * length * width;
    }
}

class Square implements Shape {
    private double side;

    public Square(Scanner scanner) {
        this.side = scanner.nextInt();
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double peri() {
        return 4 * side;
    }
}
