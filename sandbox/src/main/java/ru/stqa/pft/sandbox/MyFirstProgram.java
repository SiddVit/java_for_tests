package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println(String.format("Square of the area with side %.1f = %.1f.", s.l, s.area()));

        Rectangle r = new Rectangle(4, 6);
        System.out.println(String.format("Square of the rectangle with sides %.1f and %.1f = %.1f.", r.a, r.b, r.area()));
    }

    public static void hello(String somebody) {
        System.out.println(String.format("Hello, %s!", somebody));
    }
}