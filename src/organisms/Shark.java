package organisms;

/**
 * Created by Varun on 2017-01-09.
 */
public class Shark extends Organism {

    public Shark(){
        super();

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
    void move() {

    }
}
