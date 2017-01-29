package life.organisms;

import java.awt.Color;

/**
 * Created by Varun on 2017-01-09.
 */
public class Algae extends Organism {

    public static final Color colour = Color.decode("#4CAF50");

    public Algae(){
        super();

        lifeSpan = 50;
        foodLevel = 0; //Algae cannot eat so must override the value in superclass
        foodValue = 2;
    }

    public Algae(int age, int foodLevel) {
        super(age, foodLevel);

        lifeSpan = 50;
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
