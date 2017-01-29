package life.view;
import javax.swing.JButton; 
import javax.swing.JPanel; 
import javax.swing.JFrame; 
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 
import javax.swing.ImageIcon; 
import java.awt.GridBagLayout; 
import java.awt.*;

/**
 * Write a description of class StartPage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartPage extends JFrame
{
    JButton startbutton; 
    JButton instructions; 
    private JPanel contentPane; 

    /**
     * Constructor for objects of class StartPage
     */
    public StartPage()
    {
        contentPane = new ImagePanel(); 
        setContentPane (contentPane); 
        setResizable (false); 
        try{
            startbutton = new JButton (new ImageIcon(ImageIO.read(getClass().getResourceAsStream("images/startbutton.png"))));
            instructions = new JButton (new ImageIcon (ImageIO.read(getClass().getResourceAsStream("images/rules.png"))));
        }
        catch (Exception e) 
        {}

        contentPane.setLayout(new GridBagLayout()); 

        GridBagConstraints c = new GridBagConstraints(); 
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        startbutton.setBorderPainted(false);//Gets rid of borders. Looks like one picture button
        startbutton.setBorder(null);
        startbutton.setMargin(new Insets(0, 0, 0, 0));
        startbutton.setContentAreaFilled(false);   

        contentPane.add (startbutton, c); 

        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        instructions.setBorderPainted(false);//Gets rid of borders. Looks like one picture button
        instructions.setBorder(null);
        instructions.setMargin(new Insets(0, 0, 0, 0));
        instructions.setContentAreaFilled(false);   

        contentPane.add (instructions, c); 
    }

    public static void main (String [] args)
    {
        StartPage startpage = new StartPage (); 
        startpage.setSize (1600, 920); 
        startpage.setVisible (true); 
    }
}
