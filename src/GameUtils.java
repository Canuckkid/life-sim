import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
     * @see docs/file_format.md contains the CSV specification
     */
    public static Organism[][] loadPattern(File f) throws FileNotFoundException {
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
                int foodLevel = Integer.parseInt(table[i][4]);

                if (table[i][2].equals("f")) {
                    board[r][c] = new Fish(age, foodLevel);
                } else if (table[i][2].equals("s")) {
                    board[r][c] = new Shark(age, foodLevel);
                } else if (table[i][3].equals("a")) {
                    board[r][c] = new Algae(age, 0); //Algae good level is always 0
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                continue; // If a row is invalid, ignore it and move to the next one
            }
        }

        return board;
    }

    /**
     * Save the state of the simulation to a file.
     *
     * @param f the file to save to. If the file already exists, its
     * contents will be clobbered and replaced with the simulation. If
     * the file doesn't exist, it will be created.
     * @param board the board state to save
     * @throws IOException if the file passed is a directory
     * @see docs/file_format.md contains the CSV specification
     */
    public static void savePattern(File f, Organism[][] board) throws IOException {
        // Generate the CSV contents
        String fileContents = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                String type = ""; // The key which represents the organism
                if (board[r][c] instanceof Fish) {
                    type = "f";
                } else if (board[r][c] instanceof Shark) {
                    type = "s";
                } else if (board[r][c] instanceof Algae) {
                    type = "a";
                } else { // All organism types should be covered above
                    assert false : "Unsupported organism type: " + board[r][c].getClass();
                }

                fileContents += r + "\t" +
                    c + "\t" +
                    type + "\t" +
                    board[r][c].age + "\t" +
                    board[r][c].foodLevel + "\n"; // Add the row
            }
        }

        // Write to the file
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(fileContents, 0, fileContents.length());
    }
}
