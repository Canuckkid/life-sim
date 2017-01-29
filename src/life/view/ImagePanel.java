package life.view;
import javax.swing.JPanel; 
import javax.imageio.ImageIO; 
import java.awt.Image; 
import java.awt.Graphics; 
/**
 * Write a description of class ImagePanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImagePanel extends JPanel
{
    private Image image;
    public ImagePanel(String background) {
        try{
            image = ImageIO.read(getClass().getResourceAsStream("images/" + background));
        }
        catch (Exception e)
        {}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

