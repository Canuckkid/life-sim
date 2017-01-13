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
    void move() {

    }
}
