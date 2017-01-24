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
    GameBoard mGameBoard;

    public Game(){
        mEcosystem = new Ecosystem();
        mGameBoard = new GameBoard(mEcosystem);
        mGameBoard.setVisible(true);

        startSim();
    }

    public void endGame(){}


    private void startSim(){
        timer = new Timer(1000, timerListener);
        timer.start();
        //mGameBoard.setPlayListener(timerListener);
    }

    private ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mEcosystem.createNextGeneration();
            mGameBoard.updateEcosystem(mEcosystem);
        }
    };

    private void nextRound(){}

}
