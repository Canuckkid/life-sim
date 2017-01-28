package life.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.annotation.Resource;
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

    private final String[] IMAGE_PATHS = {"./src/images/shark.jpg", "./src/images/fish.jpg", "./src/images/algae.jpg"};

    public OrganismSelector(){
        this.setPreferredSize(new Dimension(300, 300));

        loadImages(); //Load the images from the directory

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

        //Add the main images
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

        for(int i = 0 ; i < IMAGE_PATHS.length; i ++){
            try {
                BufferedImage imgsrc = ImageIO.read(new File(IMAGE_PATHS[i]));
                images[i] = new ImageIcon(new ImageIcon(imgsrc).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
            } catch (IOException e) {
                //e.printStackTrace();
                //System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
     * @return Lowercase String of name of organism being displayed
     */
    public String getCurrentOrganism(){
        return organismNames[currentIndex].toLowerCase();
    }

    /**
     * Sets the action listener for the main image button
     * @param l ActionListener to set
     */
    public void addImageListener(ActionListener l){
        imageBtn.addActionListener(l);
    }
}
