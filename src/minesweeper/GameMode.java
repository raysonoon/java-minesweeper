package minesweeper;

import static minesweeper.MineSweeperConstants.ROWS;
import static minesweeper.MineSweeperConstants.COLS;
import static minesweeper.MineSweeperConstants.MINES;

import java.awt.*; // Use AWT's Layout Manager
import java.awt.event.*;
import javax.swing.*; // Use Swing's Containers and Components

/* Welcome screen with title, new game, exit game */
public class GameMode extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // Private variables
    private JLabel gameModeText = new JLabel("Select Difficulty", SwingConstants.CENTER);
    private JButton btnEasy = new JButton("Easy (10 x 10, 10 mines)");
    private JButton btnMedium = new JButton("Medium (16 x 16, 40 mines)");
    private JButton btnHard = new JButton("Hard (16 x 30, 99 mines)");
    private JPanel gameModePanel = new JPanel();

    // package access variables
    static String gameModeString;

    // Public constructor
    public GameMode() {
        // Set frame properties
        setTitle("Minesweeper");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set panel layout
        gameModePanel.setLayout(new GridLayout(4, 1));

        // Set welcome text font size
        gameModeText.setFont(new Font("Monospaced", Font.BOLD, 30));


        // Easy button anonymous listener
        btnEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModeString = "easy";
                // 10 x 10 grid, 10 mines
                ROWS = 10;
                COLS = 10;
                MINES = 10;

                // Close the welcome screen window
                dispose();

                // Open the Minesweeper game window
                new MineSweeperMain();
            }
        });

        // Medium button anonymous listener
        btnMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModeString = "medium";
                // 16 x 16 grid, 40 mines
                ROWS = 16;
                COLS = 16;
                MINES = 40;

                // Close the welcome screen window
                dispose();

                // Open the Minesweeper game window
                new MineSweeperMain();
            }
        });

        // Hard button anonymous listener
        btnHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModeString = "hard";
                // 16 x 30 grid, 99 mines
                ROWS = 16;
                COLS = 30;
                MINES = 99;

                // Close the welcome screen window
                dispose();

                // Open the Minesweeper game window
                new MineSweeperMain();
            }
        });


        // Add buttons & label to game mode panel
        gameModePanel.add(gameModeText);
        gameModePanel.add(btnEasy);
        gameModePanel.add(btnMedium);
        gameModePanel.add(btnHard);

        // Add game mode panel to frame
        add(gameModePanel);
        setVisible(true);
    }
}