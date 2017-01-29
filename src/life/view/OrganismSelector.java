package life.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Varun on 2017-01-24.
 */
public class OrganismSelector extends JPanel {
    private ImageIcon[] images;
    private String[] organismNames = {"Shark", "Fish", "Algae"};
    private JButton imageBtn;
    private JButton nextBtn;
    private JButton prevBtn;
    private int currentIndex = 0;

    //Magic constants for the organism types
    public static final int ORGANISM_SHARK = 0;
    public static final int ORGANISM_FISH = 1;
    public static final int ORGANISM_ALGAE = 2;

    private final InputStream[] IMAGE_SOURCES = {getClass().getResourceAsStream("images/shark.jpg"),
        getClass().getResourceAsStream("images/fish.jpg"),
        getClass().getResourceAsStream("images/algae.jpg")};

    public OrganismSelector(){
        this.setPreferredSize(new Dimension(300, 300));

        loadImages(); //Load the life.view.images from the directory

        prevBtn = new JButton("<-");
        //Add the previous button
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.weightx = 0.2;
        this.add(prevBtn, constraints);

        nextBtn = new JButton("->");
        //Modify constraints to add next button
        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(nextBtn, constraints);

        //Add the main life.view.images
        imageBtn = new JButton();
        imageBtn.setIcon(images[currentIndex]);
        imageBtn.createToolTip();
        imageBtn.setToolTipText(organismNames[currentIndex]);
        //Modify constraints
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0.6;

        setListeners(); //Set the listeners for the buttons
        this.add(imageBtn, constraints);
    }

    private void loadImages(){
        images = new ImageIcon[3];

        for(int i = 0 ; i < IMAGE_SOURCES.length; i ++){
            try {
                BufferedImage imgsrc = ImageIO.read(IMAGE_SOURCES[i]);
                images[i] = new ImageIcon(new ImageIcon(imgsrc).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
            } catch (IOException e) {
            }
        }
    }

    private void setListeners(){
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;

                if(currentIndex == images.length){ //Out of bounds of array
                    currentIndex = 0;
                }

                imageBtn.setIcon(images[currentIndex]);
                imageBtn.setToolTipText(organismNames[currentIndex]);
                imageBtn.repaint();
            }
        });

        prevBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex --;

                if(currentIndex < 0){ //Out of bounds of array
                    currentIndex = images.length -1;
                }

                imageBtn.setIcon(images[currentIndex]);
                imageBtn.setToolTipText(organismNames[currentIndex]);
                imageBtn.repaint();
            }
        });
    }

    /**
     * Gets the current organism being displayed in the imageBtn button
     * @return MagicInteger of name of organism being displayed
     */
    public int getCurrentOrganism(){
        if (currentIndex == 0) {
            return ORGANISM_SHARK;
        } else if (currentIndex == 1) {
            return ORGANISM_FISH;
        } else {
            return ORGANISM_ALGAE;
        }
    }

    /**
     * Sets the action listener for the main image button
     * @param l ActionListener to set
     */
    public void addImageListener(ActionListener l){
        imageBtn.addActionListener(l);
    }

    public void removeFocus(){
        imageBtn.setFocusPainted(false);
    }

}
