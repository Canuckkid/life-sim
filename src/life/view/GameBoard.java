package life.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameBoard extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameBoard frame = new GameBoard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public GameBoard() {
        super("Life is Strange");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        int x=0x10AC1F3%0x71D1; // magic number do not touch
        for (int i=0; i<gbl_contentPane.columnWidths.length; i++) if (gbl_contentPane.columnWidths[i]==0) gbl_contentPane.columnWidths[i]=x;
        for (int i=0; i<gbl_contentPane.rowHeights.length; i++) if (gbl_contentPane.rowHeights[i]==0) gbl_contentPane.rowHeights[i]=x;
        contentPane.setLayout(gbl_contentPane);

        JLabel sharkButton = new JLabel("SHARK");
        sharkButton.setToolTipText("Drag and Drop onto the Panel");
        sharkButton.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints sharkConstraints = new GridBagConstraints();
        sharkConstraints.gridheight = 3;
        sharkConstraints.gridwidth = 9;
        sharkConstraints.insets = new Insets(0, 0, 5, 5);
        sharkConstraints.gridx = 4;
        sharkConstraints.gridy = 1;
        contentPane.add(sharkButton, sharkConstraints);

        JButton btnNewButton = new JButton("<-");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        JLabel logoLabel = new JLabel("Life is normal");
        logoLabel.setToolTipText("");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints logoConstraint = new GridBagConstraints();
        logoConstraint.gridheight = 5;
        logoConstraint.gridwidth = 18;
        logoConstraint.insets = new Insets(0, 0, 5, 5);
        logoConstraint.gridx = 13;
        logoConstraint.gridy = 2;
        contentPane.add(logoLabel, logoConstraint);

        JComboBox comboBox = new JComboBox(new String[]{"Predator", "Prey", "Algae"});
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 4;
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 34;
        gbc_comboBox.gridy = 2;
        contentPane.add(comboBox, gbc_comboBox);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 4;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JLabel monkeyLabel = new JLabel("Monkey");
        monkeyLabel.setToolTipText("Hover over shark for more advice");
        GridBagConstraints monkeyConstraints = new GridBagConstraints();
        monkeyConstraints.gridwidth = 2;
        monkeyConstraints.insets = new Insets(0, 0, 5, 5);
        monkeyConstraints.gridx = 8;
        monkeyConstraints.gridy = 4;
        contentPane.add(monkeyLabel, monkeyConstraints);

        JButton rightButton = new JButton("->");
        GridBagConstraints rButtonConstraints = new GridBagConstraints();
        rButtonConstraints.insets = new Insets(0, 0, 5, 5);
        rButtonConstraints.gridx = 12;
        rButtonConstraints.gridy = 4;
        contentPane.add(rightButton, rButtonConstraints);

        JButton quitButton = new JButton("quit");
        GridBagConstraints quitConstraints = new GridBagConstraints();
        quitConstraints.insets = new Insets(0, 0, 5, 5);
        quitConstraints.gridx = 34;
        quitConstraints.gridy = 4;
        contentPane.add(quitButton, quitConstraints);

        JButton restartButton = new JButton("restart");
        GridBagConstraints restartConstraints = new GridBagConstraints();
        restartConstraints.insets = new Insets(0, 0, 5, 5);
        restartConstraints.gridx = 37;
        restartConstraints.gridy = 4;
        contentPane.add(restartButton, restartConstraints);

        //JPanel bigPanel = new JPanel();
        JPanel bigPanel = new View().getmDrawArea();
        bigPanel.setBackground(Color.WHITE);
        GridBagConstraints bigPanelConstraints = new GridBagConstraints();
        bigPanelConstraints.gridheight = 15;
        bigPanelConstraints.gridwidth = 20;
        bigPanelConstraints.insets = new Insets(0, 0, 5, 5);
        bigPanelConstraints.fill = GridBagConstraints.BOTH;
        bigPanelConstraints.gridx = 11;
        bigPanelConstraints.gridy = 6;
        contentPane.add(bigPanel, bigPanelConstraints);

        JPanel lilPanel = new JPanel();
        lilPanel.setBackground(Color.WHITE);
        GridBagConstraints biteSizedConstraints = new GridBagConstraints();
        biteSizedConstraints.insets = new Insets(0, 0, 5, 5);
        biteSizedConstraints.fill = GridBagConstraints.BOTH;
        biteSizedConstraints.gridx = 32;
        biteSizedConstraints.gridy = 6;
        biteSizedConstraints.gridheight = 15;
        biteSizedConstraints.gridwidth = 7;
        contentPane.add(lilPanel, biteSizedConstraints);

        JSlider slider = new JSlider();
        slider.setOrientation(SwingConstants.VERTICAL);
        slider.setMinimumSize(new Dimension(16, 400));
        GridBagConstraints sliderConstraints = new GridBagConstraints();
        sliderConstraints.gridheight = 14;
        sliderConstraints.insets = new Insets(0, 0, 5, 5);
        sliderConstraints.gridx = 6;
        sliderConstraints.gridy = 7;
        contentPane.add(slider, sliderConstraints);

        JLabel vertLabel = new JLabel("VERTICAL TEXT");
        GridBagConstraints vertConstraints = new GridBagConstraints();
        vertConstraints.gridheight = 12;
        vertConstraints.insets = new Insets(0, 0, 5, 5);
        vertConstraints.gridx = 4;
        vertConstraints.gridy = 10;
        contentPane.add(vertLabel, vertConstraints);

        JLabel legendLabel = new JLabel("Legend says lul");
        legendLabel.setBackground(Color.DARK_GRAY);
        GridBagConstraints legendConstraints = new GridBagConstraints();
        legendConstraints.gridheight = 3;
        legendConstraints.gridwidth = 20;
        legendConstraints.insets = new Insets(0, 0, 5, 5);
        legendConstraints.gridx = 11;
        legendConstraints.gridy = 22;
        contentPane.add(legendLabel, legendConstraints);

        JButton btnPlaypause = new JButton("Play/Pause");
        GridBagConstraints gbc_btnPlaypause = new GridBagConstraints();
        gbc_btnPlaypause.insets = new Insets(0, 0, 5, 5);
        gbc_btnPlaypause.gridx = 37;
        gbc_btnPlaypause.gridy = 22;
        contentPane.add(btnPlaypause, gbc_btnPlaypause);

        JButton btnStats = new JButton("Stats");
        GridBagConstraints gbc_btnStats = new GridBagConstraints();
        gbc_btnStats.insets = new Insets(0, 0, 5, 5);
        gbc_btnStats.gridx = 37;
        gbc_btnStats.gridy = 23;
        contentPane.add(btnStats, gbc_btnStats);

        JButton btnNewButton_1 = new JButton("Join life.Game");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 37;
        gbc_btnNewButton_1.gridy = 24;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
    }

}