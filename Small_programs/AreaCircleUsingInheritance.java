package com.demo;

import java.util.Scanner;

public class AreaCircleUsingInheritance {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the radius:");
        double radius = scanner.nextDouble();
        new AreaCircleUsingInheritance().method(radius);
    }

    public void method(double radius) {
        Circle myCircle = new Circle();
        myCircle.setradius(radius);
        System.out.print(myCircle.getDisplayText());
    }
}

abstract class CircleShape {
    public double radius;

    public CircleShape() {

    }

    public CircleShape(double radius) {
        this.radius = radius;
    }

    public void setradius(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Radius " + radius;
    }

    abstract String getDisplayText();
}

class Circle extends CircleShape {
    private double area;

    public double area() {
        return radius * radius * 3.14;
    }


    public String getDisplayText() {
        return String.format("Radius %.1f and Area is %.1f", radius, area());
    }
}
