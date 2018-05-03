package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class Grayscaler {

    /**
     * Turns {@code pixel} into gray-scale
     * @implNote {@code pixel} is an pixel value where blue is the first 8 bits, green is the second 8 bits and red is the third 8 bits
     * @implNote to gray-scale an pixel use this formula {@literal 0.21 * red + 0.72 * green + 0.07 * blue}
     * @param pixel to gray-scale
     * @return the gray-scaled value
     */
    public double grayScale(int pixel) {
	// TODO - implement this
        final int red = (pixel >> 16) & 0xFF;
        final int green = (pixel >> 8) & 0xFF;
        final int blue = pixel & 0xFF;
        return 0.21d * red + 0.72d * green + 0.07d * blue;
    }

}
