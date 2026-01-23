package com.demo;

import java.util.Scanner;

public class CustomDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter day:");
        int day = scanner.nextInt();
        System.out.println("Enter month:");
        int month = scanner.nextInt();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        CustomDate customDate = new CustomDate(day, month, year);
        customDate.displayDate();
    }
    private int day;
    private int month;
    private int year;

    public CustomDate(int day, int month, int year) {
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day < 32) {
            this.day = day;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 13) {
            this.month = month;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 10000) {
            this.year = year;
        }
    }

    public void displayDate() {
        System.out.println(getDay() + "/" + getMonth() + "/" + getYear());
    }
}
