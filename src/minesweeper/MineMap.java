package minesweeper;
// "import static" constants allow us to refer to as
//  ROWS (shorthand) instead of MineSweeperConstants.ROWS
import static minesweeper.MineSweeperConstants.ROWS;
import static minesweeper.MineSweeperConstants.COLS;
import static minesweeper.MineSweeperConstants.MINES;
import java.util.Random;
/**
 * Define the locations of mines
 */
public class MineMap {
   // package access
   int numMines = MINES;
   boolean[][] isMined = new boolean[ROWS][COLS]; // default is false

   // Constructor
   public MineMap() {
      super();
   }

   // Generate new mine map method with specified no. of mines placed randomly
   public void newMineMap(int numMines) {
      this.numMines = numMines;

      // Create random object
      Random random = new Random();

      // For loop to generate mines at random index
      for (int idx = 0; idx < numMines; idx++) {
         int rows = random.nextInt(ROWS);
         int cols = random.nextInt(COLS);
         // If cell has no mine
         if (!isMined[rows][cols]) {
            // add mine
            isMined[rows][cols] = true;
         } else {
            // cell has mine, go back 1 iteration
            idx -= 1;
         }
      }

      /*// Hardcoded for illustration and testing, assume numMines=10
      isMined[0][0] = true;
      isMined[5][2] = true;
      isMined[9][5] = true;
      isMined[6][7] = true;
      isMined[8][2] = true;
      isMined[2][4] = true;
      isMined[5][7] = true;
      isMined[7][7] = true;
      isMined[3][6] = true;
      isMined[4][8] = true;*/
   }
}