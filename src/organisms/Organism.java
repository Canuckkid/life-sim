package organisms;

/**
 * Created by Varun on 2017-01-09.
 */
public abstract class Organism{

    boolean isAlive; //Living status of organism
    public int age;
    int lifeSpan; //Ticks not years
    int foodValue; //The value a predator receives when it eats an organism
    public int foodLevel; //The food in the organism

    final int FOOD_LIMIT = 25;

    public Organism(){
        age = 0;
        isAlive = true;
        foodLevel = 10;
    }

    public Organism(int age, int foodLevel){
        this.age = age;
        this.foodLevel = foodLevel;
        isAlive = true;
    }
    /**
     * Checks is organism is fertile. organisms are fertile when they are in the second half of their lives
     * @return true if fertile; false if not.
     */
    public boolean isFertile(){
        return age > lifeSpan / 2;
    }

    abstract void eat(Organism prey);
    public abstract int[] move(Organism[][] neighbours);

    /**
     * Checks if organism's age is within the life span. Updates the isAlive boolean.
     * @return True if alive; false if dead.
     */
    public boolean isAlive(){
        isAlive = age <= lifeSpan;

        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getFoodValue() {
        return foodValue;
    }

    /**
     * Increments the age of the organism by one. Checks to see if the Organism has died of old age using {@link #isAlive}
     */
    public void nextYear(){
        age++;

        isAlive();
    }
}
