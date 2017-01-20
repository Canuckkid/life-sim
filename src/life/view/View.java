package life.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import life.Ecosystem;
import life.organisms.*;

/**
 * Created by Varun on 2017-01-10.
 */
public class View {
    public MouseAdapter mMouseAdapter;
    private DrawArea drawArea;

    public View(Ecosystem e){
        drawArea = new DrawArea(e);
    }

    public DrawArea getDrawArea() {
        return drawArea;
    }

    public void updateEcosystem(Ecosystem e){
        drawArea.updateEcosystem(e);
    }

    private class DrawArea extends JPanel {
        private Ecosystem e;

        public DrawArea(Ecosystem e) {
            this.e = e;
        }

        public void updateEcosystem(Ecosystem e){
            this.e = e;
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            int cellW = this.getWidth() / e.getOrganisms().length;
            int cellH = this.getHeight() / e.getOrganisms()[0].length;

            // Draw cell borders
            for (int r = 0; r < e.getOrganisms().length; r++) {
                for (int c = 0; c < e.getOrganisms()[r].length; c++) {
                    if (e.getOrganisms()[r][c] == null) { // Don't fill empty spaces
                        g.setColor(Color.WHITE);
                    } else if (e.getOrganisms()[r][c] instanceof Fish) {
                        g.setColor(Color.BLUE);
                    } else if (e.getOrganisms()[r][c] instanceof Algae) {
                        g.setColor(Color.GREEN);
                    } else if (e.getOrganisms()[r][c] instanceof Shark) {
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(c * cellW, r * cellH, (c+1) * cellW, (r+1) * cellH); // Draw the organism
                }
            }
        }
    }

    private class MouseAdapter extends MouseInputAdapter{
        int startX;
        int startY;


        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //super.mousePressed(e);
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //super.mouseDragged(e);
            //Get the end coordinates of the dragged
            int endX = e.getX();
            int endY = e.getY();

            //Do something with the start and end coordinates
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);

            //TODO: Implement scroll to zoom
        }
    }
}
