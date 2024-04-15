package minesweeper;

import java.awt.*; // Use AWT's Layout Manager
import java.awt.event.*;
import javax.swing.*; // Use Swing's Containers and Components

/* Welcome screen with title, new game, exit game */
public class WelcomeScreen extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // Private variables
    private JButton btnNewGame = new JButton("New Game");
    private JButton btnExitGame = new JButton("Exit");
    private JPanel welcomePanel = new JPanel();
    private JLabel welcomeText = new JLabel();

    // Public constructor
    public WelcomeScreen() {
        // Set frame properties
        setTitle("Minesweeper");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set panel layout
        welcomePanel.setLayout(new GridLayout(2, 1));

        // New Game button anonymous listener
        btnNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Close the welcome screen window
                dispose();

                // Open the Minesweeper game window
                new MineSweeperMain();
            }
        });

        // Exit Game button anonymous listener
        btnExitGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Close the welcome screen window
                dispose();
            }
        });

        // Add buttons to welcome panel
        welcomePanel.add(btnNewGame);
        welcomePanel.add(btnExitGame);

        // Add welcome panel to frame
        add(welcomePanel);
        setVisible(true);
    }

    // The entry main() method
    public static void main(String[] args) {
        // [TODO 1] Check Swing program template on how to run the constructor
        // Run GUI codes in Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeScreen(); // Let the constructor do the job
            }
        });
    }
}