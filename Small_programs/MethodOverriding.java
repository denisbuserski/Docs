package com.demo;

import java.util.Scanner;

public class MethodOverriding {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        System.out.println("Enter the 1st number:");
        int num1 = scanner.nextInt();
        System.out.println("Enter the 2nd number:");
        int num2 = scanner.nextInt();
        System.out.println("Enter the 3rd number:");
        int num3 = scanner.nextInt();
        B obj = new B(num1, num2, num3);
        obj.show();
    }
}

class A {
    int i,j;

    A (int a, int b){
        i = a;
        j = b;
    }

    void show() {
        System.out.println("i and j:" + i + " " + j);
    }
}

class B extends A {
    int k;

    B (int a, int b, int c){
        super(a, b);
        k = c;
    }

    @Override
    void show() {
        System.out.println("k = " + k);
    }
}
