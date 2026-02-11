package com.demo;

import java.util.Scanner;

public class AreaOfShapes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("**Menu**\n1. Area of Rectangle\n2. Area of Square\n3. Area of Circle\n4. Area of Triangle\nEnter your choice :");
        int ch = sc.nextInt();

        // Area ar = new Area();
        Shape ar = new Shape();

        switch (ch) {
            case 1:
                double a, b;
                System.out.print("Enter length :");
                a = sc.nextDouble();
                System.out.print("Enter breadth :");
                b = sc.nextDouble();
                a = ar.area(a, b);
                System.out.print("Area of rectangle is :" + a);
                break;
            case 2:
                double s;
                System.out.print("Enter edge :");
                s = sc.nextDouble();
                s = ar.area(s, s);
                System.out.print("Area of square is :" + s);
                break;
            case 3:
                double r;
                System.out.print("Enter radius :");
                r = sc.nextDouble();
                r = ar.area(r);
                System.out.print("Area of circle is :" + r);
                break;
            case 4:
                double c;
                System.out.print("Enter side 1 :");
                a = sc.nextDouble();
                System.out.print("Enter side 2 :");
                b = sc.nextDouble();
                System.out.print("Enter side 3 :");
                c = sc.nextDouble();
                c = ar.area(a, b, c);
                System.out.print("Area of triangle is :" + c);
                break;
            default:
                System.out.print("Wrong Option!!");
        }
    }
}

class Shape {
    public double area(double num1, double num2) {
        if (num1 != num2) {
            return num1 * num2;
        } else {
            return num1 * num2;
        }
    }
    
    public double area(double num1) {
        return Math.PI * (num1 * num1);
    }
    
    public double area(double num1, double num2, double num3) {
        double P = (num1 + num2 + num3) / 2;
        double P1 = P * (P - num1) * (P - num2) * (P - num3);
        double P2 = Math.sqrt(P1);
        return P2;
    }
}
