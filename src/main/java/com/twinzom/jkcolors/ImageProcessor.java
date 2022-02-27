package com.twinzom.jkcolors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImageProcessor {

    public int[][] transformImageToTwoDimensionalMatrix(BufferedImage img) {
        int[][] imageRGB = new int[img.getWidth() * img.getHeight()][1];
        int w = img.getWidth();
        int h = img.getHeight();
        int index = 0;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = new Color(img.getRGB(i, j), true);
                imageRGB[index][0] = ((color.getRed()&0x0ff)<<16)|
                        ((color.getGreen()&0x0ff)<<8)|(color.getBlue()&0x0ff);
                index++;
            }
        }

        return imageRGB;
    }

    public BufferedImage outputImageFromMatrix(BufferedImage originalImage, int[][] imageRGB) {
        BufferedImage outputImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int index = 0;
        for (int i = 0; i < originalImage.getWidth(); i++) {
            for (int j = 0; j < originalImage.getHeight(); j++) {
                outputImage.setRGB(i, j, imageRGB[index][0]);
                index++;
            }
        }

        return outputImage;
    }

    public int[][] convertColors (int[][] originalImageRGB, Map<Centroid, List<RGBColor>> cluster) {
        // Create color lookup table from KMeans result
        Map<Integer, Integer> lookupTable = new HashMap<>();
        for (Centroid centroid : cluster.keySet()) {
            for (RGBColor rgbColor : cluster.get(centroid)) {
                lookupTable.put(rgbColor.getRgb(), centroid.getColor().getRgb());
            }
        }

        int[][] newImageRGB = new int[originalImageRGB.length][1];
        for (int i = 0; i < originalImageRGB.length; i++) {
            newImageRGB[i][0] = lookupTable.get(originalImageRGB[i][0]);
        }

        return newImageRGB;
    }

    public List<RGBColor> distinctColors (int[][] imageRGB) {
        Set<Integer> rgbs = new LinkedHashSet<>();
        for (int i = 0; i < imageRGB.length; i++) {
            rgbs.add(imageRGB[i][0]);
        }

        List<RGBColor> rgbList = new ArrayList<>();
        for (int rgb : rgbs) {
            rgbList.add(new RGBColor(rgb));
        }

        return rgbList;
    }

    public List<RGBColor> fullColors (int[][] imageRGB) {
        List<RGBColor> rgbList = new ArrayList<>();
        for (int i = 0; i < imageRGB.length; i++) {
            rgbList.add(new RGBColor(imageRGB[i][0]));
        }

        return rgbList;
    }

}
