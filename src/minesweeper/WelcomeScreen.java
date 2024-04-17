package minesweeper;

import java.awt.*; // Use AWT's Layout Manager
import java.awt.event.*;
import javax.swing.*; // Use Swing's Containers and Components

/* Welcome screen with title, new game, exit game */
public class WelcomeScreen extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // Private variables
    private JLabel welcomeText = new JLabel("💣 Minesweeper 💣", SwingConstants.CENTER);
    private JButton btnNewGame = new JButton("New Game");
    private JButton btnExitGame = new JButton("Exit");
    private JPanel welcomePanel = new JPanel();

    // Public constructor
    public WelcomeScreen() {
        // Set frame properties
        setTitle("Minesweeper");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set panel layout
        welcomePanel.setLayout(new GridLayout(3, 1));

        // Set welcome text font size
        welcomeText.setFont(new Font("Monospaced", Font.BOLD, 30));

        // New Game button anonymous listener
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the welcome screen window
                dispose();

                // Open the game mode window
                new GameMode();
            }
        });

        // Exit Game button anonymous listener
        btnExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Terminate the program
                //dispose();
                System.exit(0);
            }
        });

        // Add buttons & label to welcome panel
        welcomePanel.add(welcomeText);
        welcomePanel.add(btnNewGame);
        welcomePanel.add(btnExitGame);

        // Add welcome panel to frame
        add(welcomePanel);
        setVisible(true);
    }

    // The entry main() method
    public static void main(String[] args) {
        // Play background music
        MusicPlayer.playBackgroundMusic("music/minesweeper-background-music.wav");

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