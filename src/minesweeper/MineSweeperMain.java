package minesweeper;

import java.awt.*; // Use AWT's Layout Manager
import java.awt.event.*;
import javax.swing.*; // Use Swing's Containers and Components

/**
 * The Mine Sweeper Game.
 * Left-click to reveal a cell.
 * Right-click to plant/remove a flag for marking a suspected mine.
 * You win if all the cells not containing mines are revealed.
 * You lose if you reveal a cell containing a mine.
 */
public class MineSweeperMain extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // private variables
    private GameBoardPanel board = new GameBoardPanel();
    private JPanel bottomPanel = new JPanel();
    private JButton btnResetGame = new JButton("Reset Game");

    // Constructor to set up all the UI and game components
    public MineSweeperMain() {
        Container cp = this.getContentPane(); // JFrame's content-pane
        cp.setLayout(new BorderLayout()); // in 10x10 GridLayout

        cp.add(board, BorderLayout.CENTER);
        
        // Add panel to the south
        cp.add(bottomPanel, BorderLayout.SOUTH);

        // Anonymous action listener for reset game
        btnResetGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new board
                board.newGame();
            }
        });

        // Add reset game button to panel
        bottomPanel.add(btnResetGame);

        board.newGame();

        pack(); // Pack the UI components, instead of setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // handle window-close button
        setTitle("Minesweeper");
        setVisible(true); // show it
    }
}