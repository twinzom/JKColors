package com.twinzom.jkcolors;

public class RectilinearDistance implements  Distance {

    // reference https://en.wikipedia.org/wiki/Rectilinear_distance
    @Override
    public double calculate(RGBColor c1, RGBColor c2) {

        int r = Math.abs(c1.getRed() - c2.getRed());
        int g = Math.abs(c1.getGreen() - c2.getGreen());
        int b = Math.abs(c1.getBlue() - c2.getBlue());

        return r+g+b;
    }
}
