package view;

import java.awt.event.MouseWheelEvent;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Varun on 2017-01-10.
 */
public class View {
    public MouseAdapter mMouseAdapter;
    public DrawArea mDrawArea;

    public void drawBoard(){}

    private class DrawArea extends JPanel {
        public DrawArea(){}

        public void draw(){}
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
