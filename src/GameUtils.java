import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import organisms.*;

/**
 * Created by Varun on 2017-01-09.
 */
public final class GameUtils {

    private static final String DIR_PATTERN = "";
    private static final String DIR_MUSIC = "";
    private static final String DIR_FONT = "";

    private GameUtils(){}

    public static Ecosystem loadFromFile(){return null;}
    public static void loadMusic(){}
    public static void loadFonts(){}

    /**
     * Read a saved simulation state.
     *
     * @param f the CSV file containing the pattern data. Must exist
     * and be a file.
     * @return the organism array represented by the CSV file
     * @throws FileNotFoundException if the file passed doesn't exist
     */
    public Organism[][] loadPattern(File f) throws FileNotFoundException {
        assert f.isFile() : f;

        // Read all the lines from the file
        Scanner s = new Scanner(f);
        List<String> lines = new ArrayList<String>();
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }

        // Convert the list of lines to an array
        String[][] table = new String[lines.size()][5];
        for (int i = 0; i < table.length; i++) {
            table[i] = lines.get(i).split("[\t, ]"); // The file can be space-, comma-, or tab-separated
        }

        // Find the largest row and column in the table to determine
        // the new organism array size
        int maxR = 0;
        int maxC = 0;
        for (int i = 0; i < table.length; i++) {
            maxR = Math.max(Integer.parseInt(table[i][0]), maxR);
            maxC = Math.max(Integer.parseInt(table[i][1]), maxC);
        }

        // Create and fill the new organism array
        Organism[][] board = new Organism[maxR][maxC];
        for (int i = 0; i < table.length; i++) {
            try {
                int r = Integer.parseInt(table[i][0]);
                int c = Integer.parseInt(table[i][1]);
                int age = Integer.parseInt(table[i][3]);
                int hunger = Integer.parseInt(table[i][4]);

                if (table[i][2].equals("f")) {
                    board[r][c] = new Fish(age, hunger);
                } else if (table[i][2].equals("s")) {
                    board[r][c] = new Shark(age, hunger);
                } else if (table[i][3].equals("a")) {
                    board[r][c] = new Algae(age, hunger);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                continue; // If a row is invalid, ignore it and move to the next one
            }
        }

        return board;
    }
}
