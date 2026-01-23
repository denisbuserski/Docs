package com.demo;

import java.util.Scanner;

public class Point {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter x coordinate:");
        int num1=scanner.nextInt();
        System.out.println("Enter y coordinate:");
        int num2=scanner.nextInt();
        Point point = new Point(num1,num2);
        point.flip();
    }
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.y = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.x = y;
    }

    public void flip() {
        System.out.println("After Swapping:");
        System.out.println("x coordinate:" + getY());
        System.out.println("y coordinate:" + getX());
    }
}
