package life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import life.view.GameBoard;
import life.view.View;
import javax.swing.*;

/**
 * Created by Varun on 2017-01-09.
 */
public class  Game {
    Ecosystem mEcosystem;
    private Timer timer;
    private ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mEcosystem.createNextGeneration();
            mGameBoard.updateEcosystem(mEcosystem);
        }
    };
    GameBoard mGameBoard;

    public Game(){
        mEcosystem = new Ecosystem();
        mGameBoard = new GameBoard(mEcosystem);
        mGameBoard.setVisible(true);

        timer = new Timer(1000, timerListener);
        mGameBoard.setPlayListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("Play")) {
                        startSim();
                        ((JButton) e.getSource()).setText("Pause");
                    } else if (e.getActionCommand().equals("Pause")) {
                        stopSim();
                        ((JButton) e.getSource()).setText("Play");
                    } else {
                        assert false : e.getActionCommand(); // Unrecognized command
                    }
                }
            });
    }

    public void endGame(){}


    /**
     * Unpause the simulation
     */
    public void startSim(){
        timer.start();
    }

    /**
     * Pause the simulation
     */
    public void stopSim() {
        timer.stop();
    }

    private void nextRound(){}

}
