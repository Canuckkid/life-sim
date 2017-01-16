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
        int dr = 0, dc = 0; // Displacement in the row and column direction

        // Check for predators
        int predDr = 0;
        int predDc = 0;
        for (int r = 0; r < neighbours.length; r++) {
            for (int c = 0; c < neighbours[r].length; c++) {
                if (neighbours[r][c] instanceof Shark) {
                    if (r == 0) {
                        predDr += 1;
                    } else if (r == 1) {
                        predDr += 2;
                    } else if (r == 3) {
                        predDr -= 2;
                    } else if (r == 4) {
                        predDr -= 1;
                    }

                    if (c == 0) {
                        predDc += 1;
                    } else if (c == 1) {
                        predDc += 2;
                    } else if (c == 3) {
                        predDc -= 2;
                    } else if (c == 4) {
                        predDc -= 1;
                    }
                }
            }
        }

        dr = predDr;
        dc = predDc;
        return new int[] {dr, dc};
    }
}
