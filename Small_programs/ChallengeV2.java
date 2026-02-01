package com.demo;

import java.util.Scanner;

public class ChallengeV2 {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int sum = 0;

        System.out.println("Enter the number of apples:");
        int num1=scanner.nextInt();
        System.out.println("Enter the number of bananas:");
        int num2 = scanner.nextInt();
        Banana obj = new Banana(num1,num2);
        obj.show();
    }
}

class Apple {
    int numberOfItems;
}

class Banana extends Apple {
    int numberOfItems;
    int numberOfApples;

    public Banana(int apples, int bananas) {
        this.numberOfApples = apples;
        this.numberOfItems = bananas;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getNumberOfApples() {
        return numberOfApples;
    }

    public void show() {
        System.out.println("Number of apples: " + getNumberOfApples());
        System.out.println("Number of bananas: " + getNumberOfItems());
    }
}
