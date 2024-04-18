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
    private JButton btnDifficulty = new JButton("Change Difficulty");
    private JButton btnFlags = new JButton("Flags Left");
    private JButton btnMusic = new JButton("🔊");

    private boolean isPaused = false;

    // Constructor to set up all the UI and game components
    public MineSweeperMain() {
        Container cp = this.getContentPane(); // JFrame's content-pane
        cp.setLayout(new BorderLayout()); // in ROWSxCOLS GridLayout

        cp.add(board, BorderLayout.CENTER);

        // Add panel to the south
        cp.add(bottomPanel, BorderLayout.SOUTH);

        // Anonymous action listener for reset game
        btnResetGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new board
                board.newGame();

                if (MusicPlayer.clip == null) {
                    MusicPlayer.playBackgroundMusic("music/minesweeper-background-music.wav");
                }

            }
        });

        // Anonymous action listener for changing difficulty
        btnDifficulty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Close minesweeper game window
                dispose();

                // New game mode window
                new GameMode();
            }
        });

        // Anonymous action listener for displaying flags left
        btnFlags.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Flags Left: " + board.numFlags);
            }
        });

        // Anonymous action listener for muting background music
        btnMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle when clicked
                isPaused = !isPaused;
                if (isPaused) {
                    MusicPlayer.stop();
                    btnMusic.setText("🔇");
                } else {
                    if (MusicPlayer.clip == null) {
                        MusicPlayer.playBackgroundMusic("music/minesweeper-background-music.wav");
                    } else {
                    MusicPlayer.play();
                    }
                    btnMusic.setText("🔊");
                }
            }
        });

        // Add game buttons to panel
        bottomPanel.add(btnResetGame);
        bottomPanel.add(btnDifficulty);
        bottomPanel.add(btnFlags);
        bottomPanel.add(btnMusic);

        board.updatePreferredSize();
        board.newGame();

        pack(); // Pack the UI components, instead of setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // handle window-close button
        setTitle("Minesweeper");
        setVisible(true); // show it
    }
}