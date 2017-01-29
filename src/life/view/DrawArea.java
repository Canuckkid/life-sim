package life.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.event.MouseInputAdapter;
import life.organisms.*;
import life.Ecosystem;

/**
 * The drawing of the grid of cells. This class handles drawing the
 * grid of cells and the lines between them. It should probably be
 * placed in a JScrollPane or similar as it simply makes itself as big
 * as it needs to be to fit the grid of cells.
 */
public class DrawArea extends JPanel {
    private Organism[][] e;
    private int cellSize;

    private Rectangle selectedHighlight;
    private Point startPoint;
    private boolean isSelected;

    private Point hoveredOrganism; //Point of where to display tooltip

    public DrawArea(Ecosystem e) {
        this.e = e.getOrganisms();
        this.cellSize = 5;

        this.setPreferredSize(new Dimension(this.e.length * cellSize, this.e[0].length * cellSize));

        selectedHighlight = new Rectangle();
        isSelected = false;

        createToolTip(); //Make a tooltip to display organism info
        hoveredOrganism = new Point();
    }

    public void updateEcosystem(Ecosystem e){
        this.e = e.getOrganisms();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        // Draw cell contents
        for (int r = 0; r < e.length; r++) {
            for (int c = 0; c < e[r].length; c++) {
                if (e[r][c] == null) { // Don't fill empty spaces
                    g.setColor(Color.WHITE);
                } else if (e[r][c] instanceof Fish) {
                    g.setColor(Fish.colour);
                } else if (e[r][c] instanceof Algae) {
                    g.setColor(Algae.colour);
                } else if (e[r][c] instanceof Shark) {
                    g.setColor(Shark.colour);
                } else if (e[r][c] instanceof Garbage) {
                    g.setColor(Garbage.colour);
                }


                g.fillRect(c * cellSize, r * cellSize, (c+1) * cellSize, (r+1) * cellSize); // Draw the organism
            }
        }

        // Draw cell borders
        g.setColor(Color.GRAY);
        for (int r = 1; r < e.length; r++) {
            g.drawLine(0, r * cellSize, this.getWidth(), r * cellSize);
        }
        for (int c = 1; c < e[0].length; c++) {
            g.drawLine(c * cellSize, 0, c * cellSize, this.getHeight());
        }

        if (isSelected) {
            g.setColor(new Color(97, 97, 97));
            g.drawRect(selectedHighlight.x, selectedHighlight.y, selectedHighlight.width, selectedHighlight.height);
            g.setColor(new Color(97, 97, 97, 90));
            g.fillRect(selectedHighlight.x, selectedHighlight.y, selectedHighlight.width, selectedHighlight.height);
        }
    }

    /**
     * Set the size of each cell. Cells are square, with sizes in
     * the range [5, 50].
     *
     * @param size the new size of the cell, in pixels
     * @throws IllegalArgumentException if the size isn't in the valid range
     */
    public void setSize(int size) {
        if (size < 5) {
            throw new IllegalArgumentException("Size must be at least 5, given " + size);
        } else if (size > 50) {
            throw new IllegalArgumentException("Size must be less than 50, given " + size);
        } else {
            this.cellSize = size;
            this.setPreferredSize(new Dimension(this.e.length * cellSize, this.e[0].length * cellSize));
        }
    }

    public void startHighlight(Point p) {
        this.startPoint = p;
        selectedHighlight = new Rectangle(this.startPoint);
        isSelected = true;
        repaint();
    }

    public void updateHighlight(Point p) {
        selectedHighlight = new Rectangle(this.startPoint);
        selectedHighlight.add(p);
        repaint();
    }

    public void removeHighlight() {
        selectedHighlight = new Rectangle();
        isSelected = false;
        repaint();
    }

    public Rectangle getSelectedHighlight() {
        return selectedHighlight;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setToolTipText(int col, int row){
        Organism organism = e[col][row];

        if(organism == null){
            setToolTipText(null);
            return;
        }

        String text = "";

        if(organism instanceof Algae){
            text += "Algae\n";
        } else if (organism instanceof Fish) {
            text += "Fish\n";
        } else{
            text += "Shark\n";
        }

        text += "Age: " + organism.getAge() + "\n";
        text += "Food Level: " + organism.getFoodLevel() * 100.00 / organism.getFoodLimit() + "%";

        hoveredOrganism.setLocation(col * cellSize, row * cellSize);

        setToolTipText(text);
    }
}
