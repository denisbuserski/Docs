package com.demo;

import java.util.Scanner;

public class Author {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Author author;

        System.out.println("Enter name:");
        String somename = scanner.nextLine();

        System.out.println("Enter email:");
        String semail = scanner.nextLine();

        System.out.println("Enter book name:");
        String sbook = scanner.nextLine();

        author = new Author(somename, semail, sbook);

        System.out.println(author.name+"\n"+author.email+"\n"+author.book);
    }

    public String name;
    public String email;
    public String book;

    public Author(String name, String email, String book) {
        this.name = name;
        this.email = email;
        this.book = book;
    }
}
