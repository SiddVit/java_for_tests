package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {
    @Test
    public void testRectangle(){
        Rectangle r = new Rectangle(2, 6);
        Assert.assertEquals(r.area(), 12);
    }
}
