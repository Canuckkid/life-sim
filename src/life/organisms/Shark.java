package life.organisms;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Varun on 2017-01-09.
 */
public class Shark extends Organism {

    public static final Color colour = Color.decode("#6200EA");

    public Shark(){
        super();

        lifeSpan = 1000;
        foodValue = 0;
    }

    public Shark(int age, int foodLevel) {
        super(age, foodLevel);

        lifeSpan = 1000;
        foodValue = 0;
    }

    /**
     * Checks to see whether the Shark is able to eat the prey.
     * @param prey Organism that the Shark wants to eat.
     */
    @Override
    public void eat(Organism prey) {
        if(foodLevel < FOOD_LIMIT){ //Check if the Shark can eat
            if(prey instanceof Fish){ //Sharks only eat Fish
                foodLevel += prey.getFoodValue(); //Add food to the predator
                prey.setAlive(false); //Kill the prey
            }
        }
    }

    @Override
    public int[] move(Organism[][] neighbours) {
        // Sanity checks: the neighbours must be a 5x5 array
        assert neighbours.length == 5;
        assert neighbours[0].length == 5;

        int dr = 0, dc = 0; // Displacement in the row and column direction

        // Check for food
        ArrayList<Integer> foodR = new ArrayList<Integer>();
        ArrayList<Integer> foodC = new ArrayList<Integer>();
        for (int r = 0; r < neighbours.length; r++) {
            for (int c = 0; c < neighbours[r].length; c++) {
                if (neighbours[r][c] instanceof Fish) {
                    foodR.add(r-2);
                    foodC.add(c-2);
                }
            }
        }
        assert foodR.size() == foodC.size(); // Each food has a row and a column

        // Find the average food move value
        if (foodR.size() != 0) {
            float sumR = 0;
            for (int r : foodR) {
                sumR += r;
            }
            int foodDr = Math.round(sumR / foodR.size());

            float sumC = 0;
            for (int c : foodC) {
                sumC += c;
            }
            int foodDc = Math.round(sumC / foodC.size());

            dr = foodDr;
            dc = foodDc;
        }

        // Don't move onto a space unless it's empty or occupied by prey
        if (!(neighbours[2+dr][2+dc] == null || neighbours[2+dr][2+dc] instanceof Garbage || neighbours[2+dr][2+dc] instanceof Fish)) {
            if (dr == -2) {
                dr = -1;
            } else if (dr == -1) {
                dr = -2;
            } else if (dr == 0) {
                dr = 1;
            } else if (dr == 1) {
                dr = 2;
            } else if (dr == 2) {
                dr = 1;
            }
        }

        return new int[] {dr, dc};
    }
}
