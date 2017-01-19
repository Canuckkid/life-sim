package life.organisms;

/**
 * Created by Varun on 2017-01-09.
 */
public class Algae extends Organism {

    public Algae(){
        super();

        lifeSpan = 100;
        foodLevel = 0; //Algae cannot eat so must override the value in superclass
        foodValue = 2;
    }

    public Algae(int age, int foodLevel) {
        super(age, foodLevel);

        lifeSpan = 100;
        foodValue = 2;
    }

    @Override
    public void eat(Organism prey) {
        //Do nothing because algae cannot eat
    }

    @Override
    public int[] move(Organism[][] neighbours) {
        return new int[] {0, 0};
    }
}
