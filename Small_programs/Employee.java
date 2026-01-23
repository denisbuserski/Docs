package com.demo;

import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name:");
        String first = scanner.nextLine();
        System.out.println("Enter last name:");
        String last = scanner.nextLine();
        System.out.println("Enter monthly salary:");
        double salary = scanner.nextDouble();
        Employee e1 = new Employee(first, last, salary);

        System.out.println("First name:" + e1.getFirst());
        System.out.println("Last name:" + e1.getLast());
        System.out.println("Salary:" + e1.getSalary());
    }

    public String firstName;
    public String lastName;
    public double salary;

    public Employee(String firstName, String lastName, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSalary(salary);
    }

    public String getFirst() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLast() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            this.salary = 0;
        } else {
            this.salary = salary;
        }
    }
}
