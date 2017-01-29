package life.organisms;

import java.awt.Color;

/**
 * Created by Varun on 2017-01-29.
 *
 * Class for garbage in a garbage patch. Garbage doesn't move or eat or die. Just stays there forever.
 */
public class Garbage extends Organism {

    public static final Color colour = Color.decode("#212121");

    @Override
    void eat(Organism prey) {
        //Do nothing
    }

    @Override
    public int[] move(Organism[][] neighbours) {
        return null;
    }
}
