package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTwoPointsTests {
    @Test
    public void testDistanceTwoPoints(){
        Point p1 = new Point(3, 5);
        Point p2 = new Point(2, 6);

        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }

    @Test
    public void testErrorDistanceTwoPoints(){
        Point p1 = new Point(3, 5);
        Point p2 = new Point(2, 6);

        Assert.assertNotEquals(p1.distance(p2), 0);
    }
}
