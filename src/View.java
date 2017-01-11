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
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
        }
    }
}
