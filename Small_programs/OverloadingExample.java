package com.demo;

public class OverloadingExample {
    public static void main(String args[]) {
        Overload overload = new Overload();
        int result;
        overload.test(10);
        overload.test(10, 20);
        result = overload.test('a');
        System.out.println("Result " + result);
    }
}

class Overload {

    public void test(int number) {
        System.out.println("a " + number);
    }

    public void test(int num1, int num2) {
        System.out.println("a and b " + num1 + "," + num2);
        System.out.println("char a");
    }

    public int test(char symbol) {
        return Integer.valueOf(symbol);
    }
}
