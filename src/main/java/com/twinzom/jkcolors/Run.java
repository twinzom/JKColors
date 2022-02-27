package com.twinzom.jkcolors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Run {

    public static void main (String[] args) {
        if (args.length <= 1) {
            System.out.println("Usage: java Run <IMAGE_PATH> <NUM_OF_COLOR>");
            System.exit(1);
        }

        String inputImagePath = args[0];
        int numOfColor = Integer.valueOf(args[1]);
        String outputImagePath = inputImagePath+"."+numOfColor+"colors.png";
        BufferedImage inputImage;
        try {
            // Read in the input image
            inputImage = ImageIO.read(Paths.get(inputImagePath).toFile());
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to read input image!", e);
        }

        ImageProcessor imageProcessor = new ImageProcessor();
        KMeans kmeans = new KMeans();
        Distance humanEuclideanDistance = new AdvancedEuclideanDistance();
        int[][] imageRGB = imageProcessor.transformImageToTwoDimensionalMatrix(inputImage);
        List<RGBColor> distinctColors = imageProcessor.distinctColors(imageRGB);
        Map<Centroid, List<RGBColor>> cluster =  kmeans.fit(distinctColors, numOfColor, 20, humanEuclideanDistance);
        int[][] outputImageRGB = imageProcessor.convertColors(imageRGB, cluster);
        BufferedImage outputImage = imageProcessor.outputImageFromMatrix(inputImage, outputImageRGB);

        try {
            ImageIO.write(outputImage, "png", new File(outputImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
