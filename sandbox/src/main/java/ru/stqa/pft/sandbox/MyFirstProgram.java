package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");

        double l = 5;
        System.out.println(String.format("Площадь квадрата со стороной %.1f = %.1f.", l, area(l)));

        double a = 4;
        double b = 6;
        System.out.println(String.format("Площадь прямоугольника со сторонами %.1f и %.1f = %.1f.", a, b, area(a, b)));
    }

    public static void hello(String somebody) {
        System.out.println(String.format("Hello, %s!", somebody));
    }

    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b){
        return a * b;
    }
}