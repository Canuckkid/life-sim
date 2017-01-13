package organisms;

/**
 * Created by Varun on 2017-01-09.
 */
public class Algae extends Organism {

    public Algae(){
        super();

        lifeSpan = 100;
        foodLevel = 0; //Algae cannot eat
        foodValue = 2;
    }

    @Override
    public void eat(Organism prey) {
        //Do nothing because algae cannot eat
    }

    @Override
    void move() {

    }
}
