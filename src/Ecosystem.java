import organisms.Organism;

/**
 * Created by Varun on 2017-01-10.
 */
public class Ecosystem {
    private Organism[][] ecosystem;

    public void createNextGeneration(){}
    private boolean isLive(){return true;}

    private static class Events{
        public static void algalBloom(){}
        public static void garbagePatch(){}
        public static void invasiveSpecies(){}
        public static void oilSpill(){}
    }

}
