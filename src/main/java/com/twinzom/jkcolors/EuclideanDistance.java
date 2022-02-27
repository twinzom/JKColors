package com.twinzom.jkcolors;

public class EuclideanDistance implements Distance {

    // reference https://en.wikipedia.org/wiki/Color_difference#sRGB
    @Override
    public double calculate(RGBColor c1, RGBColor c2) {

        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();

        int sum = (r * r) + (g * g) + (b * b);

        return Math.sqrt(sum);
    }

}
