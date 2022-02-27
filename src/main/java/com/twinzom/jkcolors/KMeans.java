package com.twinzom.jkcolors;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class KMeans {

    private static final Random random = new Random();

    /**
     * Performs the K-Means clustering algorithm on the given dataset.
     *
     * @param colors        The dataset. In this case, the data is colors
     *                      in input image.
     * @param k             Number of Clusters. How many color we want
     *                      to keep on image.
     * @param maxIterations Upper bound for the number of iterations.
     * @param distance      The distance calculator
     * @return K clusters along with their features.
     */
    public Map<Centroid, List<RGBColor>> fit(List<RGBColor> colors,
                                                 int k,
                                                 int maxIterations,
                                                 Distance distance) {
        List<Centroid> centroids = randomCentroids(colors, k);
        Map<Centroid, List<RGBColor>> clusters = new HashMap<>();
        Map<Centroid, List<RGBColor>> lastState = new HashMap<>();

        // iterate for a pre-defined number of times
        for (int i = 0; i < maxIterations; i++) {
            boolean isLastIteration = i == maxIterations - 1;

            // in each iteration we should find the nearest centroid for each color
            for (RGBColor color : colors) {
                Centroid centroid = nearestCentroid(color, centroids, distance);
                assignToCluster(clusters, color, centroid);
            }

            // if the assignments do not change, then the algorithm terminates
            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (shouldTerminate) {
                break;
            }

            // at the end of each iteration we should relocate the centroids
            centroids = relocateCentroids(clusters);
            clusters = new HashMap<>();
        }

        return lastState;
    }

    /**
     * Move all cluster centroids to the average of all assigned colors.
     *
     * @param clusters The current cluster configuration.
     * @return Collection of new and relocated centroids.
     */
    private static List<Centroid> relocateCentroids(Map<Centroid, List<RGBColor>> clusters) {
        return clusters
                .entrySet()
                .stream()
                .map(e -> average(e.getKey(), e.getValue()))
                .collect(toList());
    }

    /**
     * Moves the given centroid to the average position of all assigned colors. If
     * the centroid has no color in its cluster, then there would be no need for a
     * relocation. Otherwise, for each entry we calculate the average of all colors
     * first by summing all the entries and then dividing the final summation value by
     * the number of colors.
     *
     * @param centroid The centroid to move.
     * @param colors  The assigned colors.
     * @return The moved centroid.
     */
    private static Centroid average(Centroid centroid, List<RGBColor> colors) {
        // if this cluster is empty, then we shouldn't move the centroid
        if (colors == null || colors.isEmpty()) {
            return centroid;
        }

        // reference: https://sighack.com/post/averaging-rgb-colors-the-right-way
        int red = 0;
        int green = 0;
        int blue = 0;

        for (RGBColor color : colors) {
            red += color.getRed() * color.getRed();
            green += color.getGreen() * color.getGreen();
            blue += color.getBlue() * color.getBlue();
        }

        RGBColor averagedColor = new RGBColor((int) Math.sqrt(red/colors.size()),
                (int) Math.sqrt(green/colors.size()),
                (int) Math.sqrt(blue/ colors.size()));

        return new Centroid(averagedColor);
    }

    /**
     * Assigns a color to the given centroid. If this is the first assignment for this centroid,
     * first we should create the list.
     *
     * @param clusters The current cluster configuration.
     * @param color   The color.
     * @param centroid The centroid.
     */
    protected static void assignToCluster(Map<Centroid, List<RGBColor>> clusters, RGBColor color, Centroid centroid) {
        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(color);
            return list;
        });
    }

    /**
     * With the help of the given distance calculator, iterates through centroids and finds the
     * nearest one to the given color.
     *
     * @param color    The color to find a centroid for.
     * @param centroids Collection of all centroids
     * @param distance      The distance calculator.
     * @return The nearest centroid to the given color.
     */
    protected Centroid nearestCentroid(RGBColor color, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(color, centroid.getColor());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }

        return nearest;
    }

    /**
     * Generates k random centroids. Before kicking-off the centroid generation process,
     * first we calculate the possible value range for each RGB. Then when
     * we're going to generate the centroids, we generate random colors in
     * the [min, max] range for each RGB.
     *
     * @param colors The dataset which helps to calculate the [min, max] range for
     *                each RGB.
     * @param k       Number of clusters.
     * @return Collections of randomly generated centroids.
     */
    protected List<Centroid> randomCentroids(List<RGBColor> colors, int k) {
        List<Centroid> centroids = new ArrayList<>();
        Integer maxRed = null;
        Integer maxGreen = null;
        Integer maxBlue = null;
        Integer minRed = null;
        Integer minGreen = null;
        Integer minBlue = null;

        for (RGBColor color : colors) {
            // compares the value with the current max and choose the bigger value between them
            maxRed = maxRed == null || color.getRed() > maxRed ? color.getRed() : maxRed;
            maxGreen = maxGreen == null || color.getGreen() > maxGreen ? color.getGreen() : maxGreen;
            maxBlue = maxBlue == null || color.getBlue() > maxBlue ? color.getBlue() : maxBlue;

            // compares the value with the current min and choose the smaller value between them
            minRed = minRed == null || color.getRed() < minRed ? color.getRed() : minRed;
            minGreen = minGreen == null || color.getGreen() < minGreen ? color.getGreen() : minGreen;
            minBlue = minBlue == null || color.getBlue() < minBlue ? color.getBlue() : minBlue;
        }

        for (int i = 0; i < k; i++) {
            int red = (int) (Math.round(random.nextDouble() * (maxRed - minRed)))+ minRed;
            int green = (int) (Math.round(random.nextDouble() * (maxGreen - minGreen))) + minGreen;
            int blue = (int) (Math.round(random.nextDouble() * (maxBlue - minBlue))) + minBlue;

            RGBColor color = new RGBColor(red, green, blue);
            centroids.add(new Centroid(color));
        }

        return centroids;
    }
}
