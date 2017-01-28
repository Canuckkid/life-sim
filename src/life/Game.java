package life;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import life.organisms.Organism;
import life.view.DrawArea;
import life.view.GameBoard;
import life.view.OrganismSelector;

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

    private boolean isCreateOrganism; //Whether or not to create a new orgnaism on click
    private int organismType; //Magic number corresponding to type of organism to add

    public Game(){
        mEcosystem = new Ecosystem();
        initialEcosystem = mEcosystem;

        mGameBoard = new GameBoard(mEcosystem);
        mGameBoard.setVisible(true);
        mGameBoard.addMouseListener(new GameMouseAdapter());

        startSim();
    }

    private void startSim(){
        mGameBoard.setSpeedSliderListener(speedChanger);
        mGameBoard.setPlayPauseListener(playListener);
        mGameBoard.setSpeedSliderListener(speedChanger);
        mGameBoard.setRestartButtonListener(restart);
        mGameBoard.setScaleSliderListener(scaleChanger);
        mGameBoard.setNewOrganismListener(organismSelector);

        timer = new Timer(1000, timerListener);

        //Noticed a slowdown when on the same thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        }).start();
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
            mGameBoard.getDrawArea().setSize(val);
            mGameBoard.getDrawArea().repaint();
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

    private ActionListener organismSelector = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            organismType = mGameBoard.getOrganismSelector().getCurrentOrganism();
            isCreateOrganism = true;

        }
    };

    private class GameMouseAdapter extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof DrawArea){
                if(SwingUtilities.isRightMouseButton(e)){ //If right click
                    //Remove the organism
                    int row = e.getX() / mGameBoard.getDrawArea().getCellSize();
                    int col = e.getY() / mGameBoard.getDrawArea().getCellSize();

                    mEcosystem.removeOrganism(col, row);
                } else if (isCreateOrganism){
                    int row = e.getX() / mGameBoard.getDrawArea().getCellSize();
                    int col = e.getY() / mGameBoard.getDrawArea().getCellSize();

                    mEcosystem.addOrganism(organismType, col, row);

                    isCreateOrganism = false;
                } else {
                    ((DrawArea) e.getSource()).removeHighlight();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() instanceof DrawArea){
                ((DrawArea) e.getSource()).startHighlight(e.getPoint());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource() instanceof DrawArea) {
                ((DrawArea) e.getSource()).updateHighlight(e.getPoint());

                //((DrawArea) e.getSource()).removeHighlight();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(e.getSource() instanceof DrawArea) {
                ((DrawArea) e.getSource()).updateHighlight(e.getPoint());
            }
        }
    }


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
