package life;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import life.view.DrawArea;
import life.view.GameBoard;

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
        mGameBoard.setSize (1400, 1000); 
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
        mGameBoard.setEventsListener(eventSelector);

        timer = new Timer(1000, timerListener);

        //Noticed a slowdown when on the same thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        }).start();
    }

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
            mEcosystem = initialEcosystem; //Reset
            mGameBoard.updateEcosystem(mEcosystem);
            mGameBoard.getDrawArea().repaint();
        }
    };

    private ActionListener organismSelector = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            organismType = mGameBoard.getOrganismSelector().getCurrentOrganism();
            isCreateOrganism = true;

        }
    };

    private ActionListener eventSelector = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(mGameBoard.getDrawArea().isSelected()) {
                int id = Integer.parseInt(e.getActionCommand());

                Rectangle highlight = mGameBoard.getDrawArea().getSelectedHighlight();
                int startX = highlight.x / mGameBoard.getDrawArea().getCellSize();
                int startY = highlight.y / mGameBoard.getDrawArea().getCellSize();
                int endX = (highlight.x + highlight.width) / mGameBoard.getDrawArea().getCellSize();
                int endY = (highlight.y + highlight.height) / mGameBoard.getDrawArea().getCellSize();

                switch (id) {
                    case Ecosystem.ALGAL_BLOOM:
                        mEcosystem.algalBloom(startY, startX, endY, endX);
                        break;
                    case Ecosystem.GARBAGE_PATCH:
                        mEcosystem.garbagePatch(startY, startX, endY, endX);
                        break;
                    case Ecosystem.OIL_SPILL:
                        mEcosystem.oilSpill(startY, startX, endY, endX);
                        break;
                    default:
                        break;
                }

                mGameBoard.setEventBtnsEnabled(false);
                mGameBoard.getDrawArea().removeHighlight();
                mGameBoard.getDrawArea().repaint();
            }
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

                mGameBoard.setEventBtnsEnabled(false);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() instanceof DrawArea){
                ((DrawArea) e.getSource()).startHighlight(e.getPoint());
                mGameBoard.setEventBtnsEnabled(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource() instanceof DrawArea) {
                ((DrawArea) e.getSource()).updateHighlight(e.getPoint());
                mGameBoard.setEventBtnsEnabled(true);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(e.getSource() instanceof DrawArea) {
                ((DrawArea) e.getSource()).updateHighlight(e.getPoint());
                mGameBoard.setEventBtnsEnabled(true);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //super.mouseMoved(e);

            if(e.getSource() instanceof DrawArea) {
                int row = e.getX() / mGameBoard.getDrawArea().getCellSize();
                int col = e.getY() / mGameBoard.getDrawArea().getCellSize();

                //Not working because tooltip displays at component end, not cell
                mGameBoard.getDrawArea().setToolTipText(col, row);
            }
        }
    }
}
