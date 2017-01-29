package life.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage; 
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ImageIcon; 
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import life.Ecosystem;

public class GameBoard extends JFrame {

    private JPanel contentPane;
    private DrawArea cellGrid;
    JButton btnPlaypause;
    JButton restartButton;
    JSlider speedSlider;
    JSlider scaleSlider;
    OrganismSelector mOrganismSelector;
    EventsSelector mEventsSelector;

    /**
     * Create the frame.
     */
    public GameBoard(Ecosystem ecosystem) {
        super("Life is Strange");

        this.cellGrid = new DrawArea(ecosystem);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new ImagePanel("gameboard.png");
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

        BufferedImage imgsrc; 
        try{
            imgsrc = ImageIO.read(getClass().getResourceAsStream("images/life.png"));
        }
        catch (Exception e)
        {
            imgsrc = null; 
        }

        ImageIcon i = new ImageIcon(imgsrc);
        JLabel logoLabel = new JLabel(i);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints logoConstraint = new GridBagConstraints();
        logoConstraint.gridheight = 5;
        logoConstraint.gridwidth = 18;
        logoConstraint.insets = new Insets(0, 0, 5, 5);
        logoConstraint.gridx = 13;
        logoConstraint.gridy = 0;
        contentPane.add(logoLabel, logoConstraint);

        restartButton = new JButton("Restart");
        GridBagConstraints restartConstraints = new GridBagConstraints();
        restartConstraints.insets = new Insets(0, 0, 5, 5);
        restartConstraints.gridx = 37;
        restartConstraints.gridy = 4;
        contentPane.add(restartButton, restartConstraints);

        JScrollPane ecosystemView = new JScrollPane(cellGrid);
        ecosystemView.setBackground(Color.WHITE);
        GridBagConstraints bigPanelConstraints = new GridBagConstraints();
        bigPanelConstraints.gridheight = 15;
        bigPanelConstraints.gridwidth = 20;
        bigPanelConstraints.insets = new Insets(0, 0, 5, 5);
        bigPanelConstraints.fill = GridBagConstraints.BOTH;
        bigPanelConstraints.gridx = 11;
        bigPanelConstraints.gridy = 6;
        contentPane.add(ecosystemView, bigPanelConstraints);

        mEventsSelector = new EventsSelector();
        GridBagConstraints biteSizedConstraints = new GridBagConstraints();
        biteSizedConstraints.insets = new Insets(0, 0, 5, 5);
        biteSizedConstraints.fill = GridBagConstraints.BOTH;
        biteSizedConstraints.gridx = 32;
        biteSizedConstraints.gridy = 6;
        biteSizedConstraints.gridheight = 7;
        biteSizedConstraints.gridwidth = 7;

        mOrganismSelector = new OrganismSelector();
        GridBagConstraints organismConstraints = new GridBagConstraints();
        organismConstraints.gridheight = 7;
        organismConstraints.gridwidth = 7;
        organismConstraints.gridx = 32;
        organismConstraints.gridy = 14;
        organismConstraints.ipady = 30;
        organismConstraints.fill = GridBagConstraints.NORTH;
        organismConstraints.insets = new Insets(0, 0, 0, 0);
        contentPane.add(mOrganismSelector, organismConstraints);

        contentPane.add(mEventsSelector, biteSizedConstraints);

        speedSlider = new JSlider(30, 1000, 200);
        speedSlider.setOrientation(SwingConstants.VERTICAL);
        speedSlider.setMinimumSize(new Dimension(60, 400));
        Hashtable labelTable = new Hashtable();
        labelTable.put(1000, new JLabel("Slow"));
        labelTable.put(30, new JLabel("Fast"));
        speedSlider.setLabelTable(labelTable);
        speedSlider.setPaintLabels(true);
        speedSlider.setOpaque (false); 

        GridBagConstraints sliderConstraints = new GridBagConstraints();
        sliderConstraints.gridheight = 14;
        sliderConstraints.insets = new Insets(0, 0, 5, 5);
        sliderConstraints.gridx = 6;
        sliderConstraints.gridy = 7;
        contentPane.add(speedSlider, sliderConstraints);

        scaleSlider = new JSlider(5, 50, 5);
        scaleSlider.setOrientation(SwingConstants.VERTICAL);
        scaleSlider.setMinimumSize(new Dimension(16, 400));
        scaleSlider.setOpaque (false);

        GridBagConstraints scaleConstraints = new GridBagConstraints();
        scaleConstraints.gridheight = 14;
        scaleConstraints.insets = new Insets(0, 0, 5, 5);
        scaleConstraints.gridx = 9;
        scaleConstraints.gridy = 7;
        contentPane.add(scaleSlider, scaleConstraints);

        JLabel legendLabel = new JLabel("Legend says lul");
        legendLabel.setBackground(Color.DARK_GRAY);
        GridBagConstraints legendConstraints = new GridBagConstraints();
        legendConstraints.gridheight = 3;
        legendConstraints.gridwidth = 8;
        legendConstraints.insets = new Insets(0, 0, 5, 5);
        legendConstraints.gridx = 11;
        legendConstraints.gridy = 21;
        contentPane.add(legendLabel, legendConstraints);

        btnPlaypause = new JButton("Pause");
        GridBagConstraints gbc_btnPlaypause = new GridBagConstraints();
        gbc_btnPlaypause.insets = new Insets(0, 0, 0, 0);
        gbc_btnPlaypause.gridx = 35;
        gbc_btnPlaypause.gridy = 23;
        contentPane.add(btnPlaypause, gbc_btnPlaypause);

        JButton btnStats = new JButton("Stats");
        GridBagConstraints gbc_btnStats = new GridBagConstraints();
        gbc_btnStats.insets = new Insets(0, 0, 0, 0);
        gbc_btnStats.gridx = 35;
        gbc_btnStats.gridy = 24;
        contentPane.add(btnStats, gbc_btnStats);

        this.setPreferredSize(new Dimension(1080, 720));
        this.pack();
        this.setVisible(true);
    }

    public void setPlayPauseListener(ActionListener a){
        btnPlaypause.addActionListener(a);
    }

    public void setRestartButtonListener(ActionListener a){
        restartButton.addActionListener(a);
    }

    public void setSpeedSliderListener(ChangeListener c){
        speedSlider.addChangeListener(c);
    }

    public void setScaleSliderListener(ChangeListener c) {
        scaleSlider.addChangeListener(c);
    }

    public void updateEcosystem(Ecosystem e){
        cellGrid.updateEcosystem(e);
    }

    public void setNewOrganismListener(ActionListener l){
        mOrganismSelector.addImageListener(l);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        contentPane.addMouseListener(l);
        cellGrid.addMouseListener(l); //needed for drag and drop, highlighting
    }

    public DrawArea getDrawArea() {
        return cellGrid;
    }

    public OrganismSelector getOrganismSelector() {
        return mOrganismSelector;
    }

    public void setEventsListener(ActionListener l){
        mEventsSelector.addActionListener(l);
    }

    public void setEventBtnsEnabled(boolean isEnabled){
        mEventsSelector.enableBtns(isEnabled);
    }

    private class EventsSelector extends JPanel{
        JButton algae;
        JButton oil;
        JButton garbage;

        public EventsSelector(){
            this.setLayout(new BorderLayout(0,0));
            this.setBackground(Color.white);

            algae = new JButton("Algal Bloom");
            algae.setActionCommand(String.valueOf(Ecosystem.ALGAL_BLOOM));
            algae.setPreferredSize(new Dimension(getWidth(), 50));

            oil = new JButton("Oil Spill");
            oil.setActionCommand(String.valueOf(Ecosystem.OIL_SPILL));
            oil.setPreferredSize(new Dimension(getWidth(), 50));

            garbage = new JButton("Garbage Patch");
            garbage.setActionCommand(String.valueOf(Ecosystem.GARBAGE_PATCH));
            garbage.setPreferredSize(new Dimension(getWidth(), 50));

            this.add(algae, BorderLayout.NORTH);
            this.add(oil, BorderLayout.CENTER);
            this.add(garbage, BorderLayout.SOUTH);

            enableBtns(false); //Buttons start off disabled
        }

        public void enableBtns(boolean isEnabled){
            algae.setEnabled(isEnabled);
            garbage.setEnabled(isEnabled);
            oil.setEnabled(isEnabled);
        }

        public void addActionListener(ActionListener l){
            algae.addActionListener(l);
            oil.addActionListener(l);
            garbage.addActionListener(l);
        }
    }
}
