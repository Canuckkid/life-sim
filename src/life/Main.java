package life;

import java.awt.EventQueue;
import life.view.GameBoard;

public class Main {
    Game game;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameBoard frame = new GameBoard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
