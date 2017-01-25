package life.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
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
        private Organism[][] e;
        private int cellW;
        private int cellH;

        public DrawArea(Ecosystem e) {
            this.e = e.getOrganisms();
            this.cellW = 5;
            this.cellH = 5;

            this.setPreferredSize(new Dimension(this.e.length * cellW, this.e[0].length * cellH));
        }

        public void updateEcosystem(Ecosystem e){
            this.e = e.getOrganisms();
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            // Draw cell contents
            for (int r = 0; r < e.length; r++) {
                for (int c = 0; c < e[r].length; c++) {
                    if (e[r][c] == null) { // Don't fill empty spaces
                        g.setColor(Color.WHITE);
                    } else if (e[r][c] instanceof Fish) {
                        g.setColor(Color.BLUE);
                    } else if (e[r][c] instanceof Algae) {
                        g.setColor(Color.GREEN);
                    } else if (e[r][c] instanceof Shark) {
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(c * cellW, r * cellH, (c+1) * cellW, (r+1) * cellH); // Draw the organism
                }
            }

            // Draw cell borders
            g.setColor(Color.GRAY);
            for (int r = 1; r < e.length; r++) {
                g.drawLine(0, r * cellH, this.getWidth(), r * cellH);
            }
            for (int c = 1; c < e[0].length; c++) {
                g.drawLine(c * cellW, 0, c * cellW, this.getHeight());
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
