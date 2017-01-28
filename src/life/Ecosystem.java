package life;

import java.util.Random;
import life.organisms.Algae;
import life.organisms.Fish;
import life.organisms.Organism;
import life.organisms.Shark;

/**
 * Created by Varun on 2017-01-10.
 *
 * Contains simulation logic
 * Class for an ecosystem. Contains a 2D array of life.organisms
 */
public class Ecosystem {
    private Organism[][] ecosystem;

    private final int MAX_SIZE = 100;
    private final int SPAWN_RATE = 25; //1/4 chance of spawning

    private Random mRandom = new Random();

    public Ecosystem(){
        ecosystem = new Organism[MAX_SIZE][MAX_SIZE];

        for (int col = 0; col < ecosystem.length; col++){
            for (int row = 0; row < ecosystem[col].length; row++) {
                ecosystem[col][row] = getRandomOrganism();
            }
        }
    }

    /**
     * Creates an ecosystem from an existing 2D array of life.organisms. Mainly used when loading from a file.
     * @param ecosystem 2D array of life.organisms in an existing configuration.
     */
    public Ecosystem(Organism[][] ecosystem){
        this.ecosystem = ecosystem;
    }

    /**
     * @return the grid of organisms that make of the Ecosystem
     */
    public Organism[][] getOrganisms() {
        return this.ecosystem;
    }

    /**
     * Creates the next generation of the ecosystem by allowing the organisms to move and eat.
     */
    public void createNextGeneration(){
        // Create an empty board of the same size
        Organism[][] nextGen = new Organism[ecosystem.length][ecosystem[0].length];

        //Movement
        for (int col = 0; col < ecosystem.length; col++){
            for (int row = 0; row < ecosystem[col].length; row++){
                Organism[][] subset = subset(col, row); //Get the life.organisms surroundings

                if(ecosystem[col][row] != null) {
                    ecosystem[col][row].nextYear(); //Age organism

                    if(ecosystem[col][row].isAlive()) { //Check if died of old age

                        int[] coords = ecosystem[col][row].move(subset); //Let the organism move

                        //Wrap the new coordinates into the length
                        int dc = coords[0] + col;
                        int dr = coords[1] + row;

                        int wrappedX = GameUtils.wrapIndex(dc, ecosystem.length);
                        int wrappedY = GameUtils.wrapIndex(dr, ecosystem[wrappedX].length);
                        assert 0 <= wrappedX && wrappedX < ecosystem.length : wrappedY;
                        assert 0 <= wrappedY && wrappedY < ecosystem[wrappedX].length : wrappedY;

                        //Set the organism to its new position
                        nextGen[wrappedX][wrappedY] = ecosystem[col][row];
                    } else{
                        nextGen[col][row] = null; //Organism is dead
                    }
                }
            }
        }

        //Spawning
        for (int col = 0; col < nextGen.length; col++){
            for (int row = 0; row < nextGen[col].length; row++) {
                //Check if spawning
                if(nextGen[col][row] == null){ //No Organism exists
                    if(mRandom.nextInt(100) < SPAWN_RATE){ //CHeck probability of spawning
                        nextGen[col][row] = getRandomOrganism(); //Spawn a new organism
                    }
                }
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

    public void algalBloom(int startx, int starty, int endx, int endy) {
        final int PROBABILITY = 50; //50% of wipeout

        for(int col = startx; col <= endx; col++){
            for(int row = starty; row <= endy; row++) {
                if(mRandom.nextInt(100) > PROBABILITY){ //50% of wipeout
                    if (!(ecosystem[col][row] instanceof Algae)){ //Algal blooms dont wipeout algae
                        ecosystem[col][row] = new Algae(); //Replace with algae
                    }
                }

            }
        }
    }

    public void garbagePatch(int startx, int starty, int endx, int endy){
        final int PROBABILITY = 60; //60% of wipeout

        for(int col = startx; col <= endx; col++){
            for(int row = starty; row <= endy; row++) {
                if(mRandom.nextInt(100) > PROBABILITY){ //60% of wipeout
                    ecosystem[col][row] = null;
                }
            }
        }
    }

    //todo: Add invasive species to life.organisms and implement this method
    //public void invasiveSpecies(int startx, int starty, int endx, int endy){}

    public void oilSpill(int startx, int starty, int endx, int endy){
        final int PROBABILITY = 75; //75% of wipeout

        for(int col = startx; col <= endx; col++){
            for(int row = starty; row <= endy; row++) {
                if(mRandom.nextInt(100) > PROBABILITY){ //75% of wipeout
                    ecosystem[col][row] = null;
                }
            }
        }
    }

    private Organism getRandomOrganism(){
        int num = mRandom.nextInt(20);

        if(num <= 2){
            return new Algae();
        } else if (num == 3 || num == 4) {
            return new Fish();
        } else if (num == 5) {
            return new Shark();
        } else {
            return null;
        }
    }
}
