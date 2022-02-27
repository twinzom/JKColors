package com.twinzom.jkcolors;

/**
 * Creating this RGBColor class, but not using java.awt.Color, it is because
 * the KMeans operation only cater value of RGB. So, using a lighter wight
 * object may help to improve memory efficiency.
 */
public class RGBColor {
    int red;
    int green;
    int blue;
    int rgb;

    public RGBColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.rgb = ((red&0x0ff)<<16)|((green&0x0ff)<<8)|(blue&0x0ff);
    }

    public RGBColor (int rgb) {
        this.red = (rgb>>16)&0xFF;
        this.green = (rgb>>8)&0xFF;
        this.blue = (rgb)&0xFF;
        this.rgb = ((red&0x0ff)<<16)|((green&0x0ff)<<8)|(blue&0x0ff);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    @Override
    public String toString() {
        return "red: " + this.getRed() + ", green: " + this.getGreen()
                + ", blue: " + this.getBlue() + ", rgb: " + this.getRgb();
    }

    public boolean equals (Object o) {
        RGBColor c2 = (RGBColor) o;
        return this.red == c2.getRed() &&
                this.green == c2.getGreen() &&
                this.blue == c2.getBlue();
    }
}
