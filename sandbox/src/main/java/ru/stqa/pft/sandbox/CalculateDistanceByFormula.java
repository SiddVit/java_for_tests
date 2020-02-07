package ru.stqa.pft.sandbox;

public class CalculateDistanceByFormula {
    public static void main(String[] args) {
        Point p = new Point(3, 2, 5, 6);
        System.out.println(String.format("Distance between points %.1f.%.1f and %.1f.%.1f = %.1f", p.x1, p.x2, p.y1, p.y2, p.distance()));

        double x1 = 3;
        double x2 = 2;
        double y1 = 5;
        double y2 = 6;

        System.out.println(String.format("Distance between points %.1f.%.1f and %.1f.%.1f = %.1f", x1, x2, y1, y2, Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))));
    }
}
