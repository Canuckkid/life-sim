package life;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import life.view.InstructionsPage;
import life.view.StartPage;

/**
 * Created by Varun on 2017-01-29.
 */
public class Main extends JFrame{

    private static StartPage mStartPage;

    private static ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        mStartPage.setVisible(false);
                        new Game();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private static ActionListener rulesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            InstructionsPage instructionsPage = new InstructionsPage();
            instructionsPage.addBackListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mStartPage.resetContentPane();
                    mStartPage.pack();
                    mStartPage.repaint();
                }
            });

            mStartPage.changeContentPane(instructionsPage);
            mStartPage.pack();
            mStartPage.repaint();
        }
    };

    private static void addListeners(){
        mStartPage.addRulesBtnListener(rulesListener);
        mStartPage.addStartBtnListener(startListener);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mStartPage = new StartPage();
                    addListeners();
                    GameUtils.playSound();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
