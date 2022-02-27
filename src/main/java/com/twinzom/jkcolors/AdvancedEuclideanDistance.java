package com.twinzom.jkcolors;

public class AdvancedEuclideanDistance implements Distance {

    // reference https://en.wikipedia.org/wiki/Color_difference#sRGB
    @Override
    public double calculate(RGBColor c1, RGBColor c2) {

        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        int sum;
        if ((c1.getRed() + c2.getRed())/2 < 128) {
            sum = (r * r)*2 + (g * g)*4 + (b * b)*3;
        } else {
            sum = (r * r)*3 + (g * g)*4 + (b * b)*2;
        }
        return Math.sqrt(sum);
    }

}
