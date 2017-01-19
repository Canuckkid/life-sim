package life.organisms;

import java.util.ArrayList;

/**
 * Created by Varun on 2017-01-09.
 */
public class Fish extends Organism {

    public Fish(){
        super();

        lifeSpan = 500;
        foodValue = 5;
    }

    public Fish(int age, int foodLevel) {
        super(age, foodLevel);

        lifeSpan = 500;
        foodValue = 5;
    }

    @Override
    public void eat(Organism prey) {
        if(foodValue < FOOD_LIMIT){ //Check if the organism is full
            if(prey instanceof Algae) { //Fish can only eat algae
                foodLevel += prey.getFoodValue(); //Add food to the predator
                prey.setAlive(false); //The prey has now died
            }
        }

    }

    @Override
    public int[] move(Organism[][] neighbours) {
        // Sanity checks: the neighbours must be 5x5
        assert neighbours.length == 5;
        assert neighbours[0].length == 5;

        int dr = 0, dc = 0; // Displacement in the row and column direction

        // Check for predators
        ArrayList<Integer> predR = new ArrayList<Integer>();
        ArrayList<Integer> predC = new ArrayList<Integer>();
        for (int r = 0; r < neighbours.length; r++) {
            for (int c = 0; c < neighbours[r].length; c++) {
                if (neighbours[r][c] instanceof Shark) {
                    if (r == 0) {
                        predR.add(1);
                    } else if (r == 1) {
                        predR.add(2);
                    } else if (r == 2) {
                        predR.add(2);
                    } else if (r == 3) {
                        predR.add(-2);
                    } else if (r == 4) {
                        predR.add(-1);
                    }

                    if (c == 0) {
                        predC.add(1);
                    } else if (c == 1) {
                        predC.add(2);
                    } else if (c == 2) {
                        predC.add(2);
                    } else if (c == 3) {
                        predC.add(-2);
                    } else if (c == 4) {
                        predC.add(-1);
                    }
                }
            }
        }
        assert predR.size() == predC.size(); // Each predator has a row and a column

        // Find the average predator move value
        int predDr = 0, predDc = 0;
        if (predR.size() != 0) {
            float sumR = 0;
            for (int r : predR) {
                sumR += r;
            }
            predDr = Math.round(sumR / predR.size());

            float sumC = 0;
            for (int c : predC) {
                sumC += c;
            }
            predDc = Math.round(sumC / predC.size());
        }

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
        int foodDr = 0, foodDc = 0;
        if (foodR.size() != 0) {
            float sumR = 0;
            for (int r : foodR) {
                sumR += r;
            }
            foodDr = Math.round(sumR / foodR.size());

            float sumC = 0;
            for (int c : foodC) {
                sumC += c;
            }
            foodDc = Math.round(sumC / foodC.size());
        }

        dr = Math.round((2*predDr + foodDr) / 3.0f);
        dc = Math.round((2*predDc + foodDc) / 3.0f);

        // Don't move onto a space unless it's empty or occupied by prey
        if (!(neighbours[2+dr][2+dc] == null || neighbours[2+dr][2+dc] instanceof Algae)) {
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
