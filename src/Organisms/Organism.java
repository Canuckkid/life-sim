package Organisms;

/**
 * Created by Varun on 2017-01-09.
 */
public abstract class Organism{

    private boolean isAlive;

    abstract void reproduce();
    abstract void eat(Organism prey);
    abstract void move();
}
