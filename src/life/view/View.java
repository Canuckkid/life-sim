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
