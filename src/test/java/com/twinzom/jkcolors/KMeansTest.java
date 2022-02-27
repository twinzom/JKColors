package com.twinzom.jkcolors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class KMeansTest {

    KMeans kMeans;

    @BeforeEach
    void setUp() {
        kMeans = new KMeans();
    }

    @Test
    public void randomCentroidsTest () {
        RGBColor[] colors = new RGBColor[10];
        colors[0] = new RGBColor(200, 7, 87); // max red
        colors[1] = new RGBColor(5, 98, 11); // min red
        colors[2] = new RGBColor(37, 135, 10);
        colors[3] = new RGBColor(87, 175, 23);
        colors[4] = new RGBColor(120, 85, 200); // max blue
        colors[5] = new RGBColor(56, 67, 5); // min blue
        colors[6] = new RGBColor(189, 34, 54);
        colors[7] = new RGBColor(199, 123, 10);
        colors[8] = new RGBColor(23, 200, 12); // max green
        colors[9] = new RGBColor(111, 5, 56); // min green
        List colorList =  Arrays.asList(colors);
        List<Centroid> centroids = kMeans.randomCentroids(colorList, 5);

        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;
        int minRed = 255;
        int minGreen = 255;
        int minBlue = 255;

        for (Centroid centroid : centroids) {
            RGBColor color = centroid.getColor();
            System.out.println(centroid.getColor());

            maxRed = color.getRed() > maxRed ? color.getRed() : maxRed;
            maxGreen = color.getRed() > maxGreen ? color.getGreen() : maxGreen;
            maxBlue = color.getBlue() > maxBlue ? color.getBlue() : maxBlue;

            minRed = color.getRed() > minRed ? color.getRed() : minRed;
            minGreen = color.getRed() > minGreen ? color.getGreen() : minGreen;
            minBlue = color.getBlue() > minBlue ? color.getBlue() : minBlue;
        }

        Assertions.assertTrue(maxRed <= 200);
        Assertions.assertTrue(maxGreen <= 200);
        Assertions.assertTrue(maxBlue <= 200);
        Assertions.assertTrue(minRed >= 5);
        Assertions.assertTrue(minGreen >= 5);
        Assertions.assertTrue(minBlue >= 5);
    }
}
