package com.twinzom.jkcolors;

import java.awt.Color;
import java.util.Objects;

/**
 * Encapsulates all colors for a particular cluster color.
 */
public class Centroid {

    /**
     * The centroid colors.
     */
    private final RGBColor color;

    public Centroid(RGBColor color) {
        this.color = color;
    }

    public RGBColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Centroid centroid = (Centroid) o;
        return ( getColor().getRed() == centroid.getColor().getRed()
                 && getColor().getGreen() == centroid.getColor().getGreen()
                 && getColor().getBlue() == centroid.getColor().getBlue() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }

    @Override
    public String toString() {
        return "Centroid " + color;
    }


}
