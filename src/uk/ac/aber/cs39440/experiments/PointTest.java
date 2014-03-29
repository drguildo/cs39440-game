package uk.ac.aber.cs39440.experiments;

import java.awt.Point;

public class PointTest {

    public static void main(String[] args) {
        Point point = new Point(10, 10);
        System.out.println(point);
        point.setLocation(5, 2);
        System.out.println(point);
    }

}
