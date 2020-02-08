package ru.stqa.pft.sandbox;

public class CalculateDistanceByFormula {
    public static void main(String[] args) {
        double x1 = 3;
        double x2 = 2;
        double y1 = 5;
        double y2 = 6;

        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);

        System.out.println(p1.distance(p2));
    }
}