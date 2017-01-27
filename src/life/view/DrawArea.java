package life.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
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

    public DrawArea(Ecosystem e) {
        this.e = e.getOrganisms();
        this.cellSize = 5;

        this.setPreferredSize(new Dimension(this.e.length * cellSize, this.e[0].length * cellSize));
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
                    g.setColor(Color.BLUE);
                } else if (e[r][c] instanceof Algae) {
                    g.setColor(Color.GREEN);
                } else if (e[r][c] instanceof Shark) {
                    g.setColor(Color.BLACK);
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
}
