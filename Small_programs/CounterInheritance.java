package com.demo;

public class CounterInheritance {
    public static void main(String[] args) {
        Counter counter = new Counter();

        counter.increment();
        counter.increment();
        counter.increment();

        counter.print();
    }
}

class Counter {
    int i = 0;
    Counter increment() {
        i++;
        return this;
    }

    void print() {
        System.out.println("i = " + i);
    }
}
