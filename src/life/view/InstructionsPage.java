package life.view;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Scrollable;

/**
 * Created by torvarun
 */
public class InstructionsPage extends ImagePanel
{
    private JButton backBtn;

    public InstructionsPage() {
        super("gameboard.png");

        ImageIcon i = new ImageIcon(getClass().getResource("images/instructions.png"));
        ScrollablePicture image = new ScrollablePicture(i, 15);
        JScrollPane jsp = new JScrollPane(image);
        jsp.createVerticalScrollBar();
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getViewport().setPreferredSize(new Dimension(640, 720));

        //setLayout(new BorderLayout());

        add(jsp);

        backBtn = new JButton("Back");
        add(backBtn);

        this.setPreferredSize(new Dimension(1080, 720));
        this.setVisible(true);
    }

    public void addBackListener(ActionListener l){
        backBtn.addActionListener(l);
    }

    /*public static void main(String[] args) {
        JFrame x = new JFrame("Hi");
        x.add(new InstructionsPage());
        x.pack();
        x.setVisible(true);
    } */
}
