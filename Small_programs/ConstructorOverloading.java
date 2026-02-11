package com.demo;

public class ConstructorOverloading {
    public static void main(String args[]) {

        Box mybox1 = new Box(10, 20, 15);

        Box mybox2 = new Box();

        Box mycube = new Box(7);

        int vol;

        vol = mybox1.volume();

        System.out.println("Volume of mybox1 is " + vol);

        vol = mybox2.volume();

        System.out.println("Volume of mybox2 is " + vol);

        vol = mycube.volume();

        System.out.println("Volume of mycube is " + vol);
    }
}

class Box {
    int width;
    int height;
    int depth;

    Box() {}

    Box(int height) {
        this.height = height;
    }

    Box(int  width, int height, int depth) {
        this(height);
        this.width = width;
        this.depth = depth;
    }

    int volume() {
        if (this.width == 0 && this.height == 0 && this.depth == 0) {
            return -1;
        } else if (this.width == 0 && this.height > 0 && this.depth == 0) {
            return height * height * height;
        } else {
            return width * height * depth;
        }
    }
}
