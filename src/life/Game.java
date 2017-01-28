package life;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import life.view.DrawArea;
import life.view.GameBoard;
import life.view.View;

/**
 * Created by Varun on 2017-01-09.
 *
 * UI Logic
 */
public class Game {
    private Ecosystem mEcosystem;
    private final Ecosystem initialEcosystem;
    private Timer timer;
    private GameBoard mGameBoard;

    public Game(){
        mEcosystem = new Ecosystem();
        initialEcosystem = mEcosystem;

        mGameBoard = new GameBoard(mEcosystem);
        mGameBoard.setVisible(true);

        startSim();
    }

    private void startSim(){
        mGameBoard.setSpeedSliderListener(speedChanger);
        mGameBoard.setPlayPauseListener(playListener);
        mGameBoard.setSpeedSliderListener(speedChanger);
        mGameBoard.setRestartButtonListener(restart);
        mGameBoard.setScaleSliderListener(scaleChanger);

        timer = new Timer(1000, timerListener);
        timer.start();
    }

    private void nextRound(){}

    public void endGame(){
        timer.stop();
    }

    private ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mEcosystem.createNextGeneration();
            mGameBoard.updateEcosystem(mEcosystem);
        }
    };

    private ActionListener playListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if(btn.getText().equals("Play")) {
                timer.start();
                btn.setText("Pause");
            } else{
                timer.stop();
                btn.setText("Play");
            }
        }
    };

    private ChangeListener speedChanger = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            try {
                JSlider slider = (JSlider) e.getSource();
                int value = slider.getValue();
                /*
                System.out.println(value);

                //50 on slider maps to 200millis
                value *= 4; //Map to milli value
                */

                timer.setDelay(value);
                timer.restart();
            } catch (Exception ex){
            }
        }
    };

    private ChangeListener scaleChanger = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int val = ((JSlider) e.getSource()).getValue();
            mGameBoard.cellGrid.setSize(val);
        }
    };

    private ActionListener restart = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            mEcosystem = initialEcosystem; //Reset
            mGameBoard.updateEcosystem(mEcosystem);
            timer.start(); //Restart Timer
        }
    };


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //GameBoard frame = new GameBoard();
                    new Game();
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
