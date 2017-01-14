import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GameUtils.savePattern(new File("/home/wec/hmwk/ics/life-sim/data/patterns/tested.csv"),
                              GameUtils.loadPattern(new File("/home/wec/hmwk/ics/life-sim/data/patterns/test.csv")));
    }
}
