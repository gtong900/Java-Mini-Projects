package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class AsciiArtPrinter {

    /**
     * @implNote should only print values within {@code asciiArt} and nothing else within this method
     * @param asciiArt to print
     */
    public void print(char[][] asciiArt) {
	// TODO - implement this
        for (int i = 0; i < asciiArt.length; i++) {
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < asciiArt[0].length; j++) {
                row.append(asciiArt[i][j]);
            }
            System.out.println(row.toString());
        }
    }

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");  
        //System.out.println("\u001B[51;1H");
    }

}
