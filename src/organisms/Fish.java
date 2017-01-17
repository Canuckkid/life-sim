package organisms;

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
        int sumR = 0;
        for (int r : predR) {
            sumR += r;
        }
        int predDr = sumR / predR.size();

        int sumC = 0;
        for (int c : predC) {
            sumC += c;
        }
        int predDc = sumC / predC.size();

        // Check for food
        int foodDr = 0;
        int foodDc = 0;
        for (int r = 0; r < neighbours.length; r++) {
            for (int c = 0; c < neighbours[r].length; c++) {
                if (neighbours[r][c] instanceof Algae) {

                }
            }
        }

        dr = predDr;
        dc = predDc;
        return new int[] {dr, dc};
    }
}
