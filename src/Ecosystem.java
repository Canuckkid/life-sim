import java.util.ArrayList;
import java.util.Random;
import organisms.Organism;

/**
 * Created by Varun on 2017-01-10.
 *
 * Class for an ecosystem. Contains a 2D array of organisms
 */
public class Ecosystem {
    private Organism[][] ecosystem;

    private final int MAX_SIZE = 100;

    public Ecosystem(){
        ecosystem = new Organism[MAX_SIZE][MAX_SIZE];
    }

    /**
     * Creates an ecosystem from an existing 2D array of organisms. Mainly used when loading from a file.
     * @param ecosystem 2D array of organisms in an existing configuration.
     */
    public Ecosystem(Organism[][] ecosystem){
        this.ecosystem = ecosystem;
    }

    public void createNextGeneration(){
        Organism[][] nextGen = ecosystem; //Temporarily hold the current ecosystem

        for (int col = 0; col < ecosystem.length; col++){
            for (int row = 0; row < ecosystem[col].length; row++){
                Organism[][] subset = subset(col, row); //Get the organisms surroundings

                int[] coords = ecosystem[col][row].move(subset); //Let the organism move

                //Wrap the new coordinates into the length
                int wrappedX = GameUtils.wrapIndex(coords[0], ecosystem.length);
                int wrappedY = GameUtils.wrapIndex(coords[1], ecosystem[wrappedX].length);

                //Set the organism to its new position
                nextGen[wrappedX][wrappedY] = ecosystem[col][row];
                ecosystem[col][row] = null;
            }
        }

        ecosystem = nextGen;
    }

    /**
     * Creates a 5x5 array which is a subset of {@link #ecosystem}, centered at the desired coordinates.
     * @param col Column of the desired centre
     * @param row Row of the desired centre
     * @return Subset 5x5 array of {@link Organism} type.
     */
    private Organism[][] subset(int row, int col){
        Organism[][] subset = new Organism[5][5]; //Create a subset array of the desired size

        int x = 0; //Subset x-index
        int y = 0; //Subset y-index

        for(int dx = col - 2; dx <= col + 2; dx++){
            int dxHolder = dx; //Save the value to set after wrapping for loop to properly function

            if(dx < 0){ //Left side wrap
                dx += ecosystem.length; //Wrap to right
            } else if(dx >= ecosystem.length){ //Right side wrap
                dx -= ecosystem.length; //Wrap to left
            }

            for(int dy = row - 2; dy <= row + 2; dy++){
                int dyHolder = dy; //Save the value to set after wrapping for loop to properly function

                if(dy < 0){ //Top wrap
                    dy += ecosystem[dx].length; //Wrap to bottom
                } else if(dy >= ecosystem[dx].length){ //Bottom wrap
                    dy -= ecosystem[dx].length; //Wrap to top
                }

                subset[x][y] = ecosystem[dx][dy]; //Copy to the subset

                y++; //Increment the subset row
                dy = dyHolder; //Set to the pre-wrapped value
            }

            x++; //Increment the subset column
            y = 0; //Reset the subset row to the top
            dx = dxHolder; //Set to pre-wrapped value
        }

        return subset;
    }

    private static class Events{
        public static void algalBloom(){}
        public static void garbagePatch(){}
        public static void invasiveSpecies(){}
        public static void oilSpill(){}
    }

}