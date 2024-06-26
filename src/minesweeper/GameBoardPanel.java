package minesweeper;

import static minesweeper.MineSweeperConstants.ROWS;
import static minesweeper.MineSweeperConstants.COLS;
import static minesweeper.MineSweeperConstants.MINES;

import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60; // Cell width and height, in pixels
    public static int CANVAS_WIDTH = CELL_SIZE * COLS; // Game board width/height
    public static int CANVAS_HEIGHT = CELL_SIZE * ROWS;

    private MusicPlayer soundMusicPlayer = new MusicPlayer();
    
    // Define properties (package-visible)
    /** The game board composes of ROWSxCOLS cells */
    Cell cells[][] = new Cell[ROWS][COLS];

    /** Number of mines */
    int numMines = MINES;
    // Number of flags
    int numFlags = MINES;;

    // Boolean first click
    // private boolean isFirstClick = true;

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(ROWS, COLS, 2, 2)); // JPanel

        // Allocate the 2D array of Cell, and added into content-pane.
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }

        // Allocate a common listener as the MouseEvent listener for all the Cells (JButtons)
        // TODO 3
        CellMouseListener listener = new CellMouseListener();

        // Every cell adds this common listener
        // TODO 4
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].addMouseListener(listener); // For all rows and cols
            }
        }

        // Set the size of the content-pane and pack all the components
        // under this container.
        super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    // Method to update the preferred UI size
    public void updatePreferredSize() {
        CANVAS_WIDTH = CELL_SIZE * COLS;
        CANVAS_HEIGHT = CELL_SIZE * ROWS;
        super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT)); // Inherited method from JPanel superclass
        revalidate(); // Ensure proper layout
    }

    // Initialize and re-initialize a new game
    public void newGame() {

        // Get a new mine map
        MineMap mineMap = new MineMap();
        mineMap.newMineMap(numMines);

        // Reset cells, mines, and flags
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Initialize each cell with/without mine
                cells[row][col].newGame(mineMap.isMined[row][col]);
            }
        }
        numFlags = MINES;

        // Get starting pt of game (random zero cell)
        findRandomZeroCell();
    }

    // Return the number of mines [0, 8] in the 8 neighboring cells
    // of the given cell at (srcRow, srcCol).
    private int getSurroundingMines(int srcRow, int srcCol) {
        int numMines = 0;
        for (int row = srcRow - 1; row <= srcRow + 1; row++) {
            for (int col = srcCol - 1; col <= srcCol + 1; col++) {
                // Need to ensure valid row and column numbers too
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                    if (cells[row][col].isMined)
                        numMines++;
                }
            }
        }
        return numMines;
    }

    private void findRandomZeroCell() {
        int randomRow, randomCol;
        Random random = new Random();

        // Keep finding cells randomly until zero cell is found
        do {
            randomRow = random.nextInt(ROWS);
            randomCol = random.nextInt(COLS);
        } while (getSurroundingMines(randomRow, randomCol) != 0);

        // Set zero cell to yellow
        cells[randomRow][randomCol].setBackground(Color.YELLOW);
    }

    // Reveal the cell at (srcRow, srcCol)
    // If this cell has 0 mines, reveal the 8 neighboring cells recursively
    private void revealCell(int srcRow, int srcCol) {
        int numMines = getSurroundingMines(srcRow, srcCol);
        // Set cell text to no. of mines
        cells[srcRow][srcCol].setText(numMines + "");
        cells[srcRow][srcCol].isRevealed = true;
        cells[srcRow][srcCol].isFlagged = false;
        cells[srcRow][srcCol].paint(); // based on isRevealed
        if (numMines == 0) {
            // Display nothing for cells with no neighbouring mines
            cells[srcRow][srcCol].setText("");
            // Recursively reveal the 8 neighboring cells
            for (int row = srcRow - 1; row <= srcRow + 1; row++) {
                for (int col = srcCol - 1; col <= srcCol + 1; col++) {
                    // Need to ensure valid row and column numbers too
                    if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                        if (!cells[row][col].isRevealed)
                            revealCell(row, col);
                    }
                }
            }
        }
    }

    // Return true if the player has won (all cells have been revealed or were
    // mined)
    public boolean hasWon() {
        int notOpenCell = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (!cells[row][col].isRevealed) {
                    notOpenCell++;
                }
            }
        }
        return notOpenCell == numMines;
    }

    // Define a Listener Inner Class
    // TODO 2
    private class CellMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) { // Get the source object that fired the Event
            Cell sourceCell = (Cell) e.getSource();
            // For debugging
            System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.col + ")");
            // Click on enabled cells only
            if (sourceCell.isEnabled) {
                // Left-click to reveal a cell that is unflagged; Right-click to plant/remove
                // the flag.
                if (e.getButton() == MouseEvent.BUTTON1 && !sourceCell.isFlagged) { // Left-button clicked
                    // [TODO 5] if you hit a mine, game over else reveal this cell
                    if (sourceCell.isMined) {
                        sourceCell.setText("💣");
                        // Display all mines
                        for (int row = 0; row < ROWS; row++) {
                            for (int col = 0; col < COLS; col++) {
                                if (cells[row][col].isMined) {
                                    cells[row][col].setText("💣");
                                    cells[row][col].isRevealed = true;
                                }
                                cells[row][col].setEnabled(false);
                                cells[row][col].isEnabled = false;
                            }
                        }
                        // Play end music
                        soundMusicPlayer.playEndMusic("music/minesweeper-bomb-explode.wav");
                        JOptionPane.showMessageDialog(null, "Game Over!");
                    } else {
                        revealCell(sourceCell.row, sourceCell.col);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
                    // [TODO 6]
                    // If this cell is flagged, remove the flag
                    if (sourceCell.isFlagged) {
                        sourceCell.setText("");
                        sourceCell.isFlagged = false;
                        numFlags += 1;
                    } else {
                        if (!sourceCell.isRevealed) {
                            // If this cell is unflagged & unrevealed, flag the cell
                            sourceCell.setText("🚩");
                            sourceCell.isFlagged = true;
                            numFlags -= 1;
                        }
                    }
                }
            } else {
                // Disable click on disabled cells
                return;
            }

            // [TODO 7] Check if the player has won, after revealing this cell
            if (hasWon()) {
                soundMusicPlayer.playEndMusic("music/minesweeper-win.wav");
                JOptionPane.showMessageDialog(null, "You won!");
            }

        }
    }
}