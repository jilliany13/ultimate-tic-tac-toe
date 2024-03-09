import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.*;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class BigC_GUI extends BigBoardGUI{

    // CHANGE VARIBALE NAMES TO BE MORE CLEAR 
    private int i1;
    private int j1;
    private int k1;
    private int l1;

    /**
     * Game vs Computers
     * 
     * @param computer
     * 
     */
    public BigC_GUI() {

        super();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultimate Tic Tac Toe: 1 Player");
        this.setSize(800, 600);
        frame.setTitle("Player's Turn: " + SmallBoard.PLAYER1);
        count = 0;

    }

    /**
     * Handles what happens when you click a spot on the board
     */
    // @Override
    public void actionPerformed(ActionEvent e) {

        //boolean clicked = false;

        // 1. Find the button that the user clicked
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                JButton[][] buttons = boards[i][j].get2DButtons();

                for (int k = 0; k < 3; k++) {

                    for (int l = 0; l < 3; l++) {

                        if (e.getSource() == buttons[k][l] && buttons[k][l].getText().equals(" ")) {

                            this.i1 = i;
                            this.j1 = j;
                            this.k1 = k;
                            this.l1 = l;

                            //clicked = true;

                            buttons[k][l].setText(Character.toString(SmallBoard.PLAYER1));
                            this.disableAllButtons();

                            // X just played, so the next character to play is O
                            frame.setTitle("Player's Turn: " + SmallBoard.PLAYER2);
                            boards[i][j].getCalcBoard().player1Move(k, l);

                            // 3c. If the current board just won
                            if (boards[i][j].getCalcBoard().isWon()) {

                                // 3d. Fills the real board with the win
                                calcBigBoard.singleWin(SmallBoard.PLAYER1, i, j);
                                boards[i][j].getCalcBoard().checkWinnerSmall();

                                boards[i][j].displayWinner();

                                // 3e. If there's an overall winner on this board
                                if (calcBigBoard.checkWinner() != ' ') {
                                    this.displayWinner();
                                    return;
                                }

                            }

                            // if there's not a winner on the board that the player's gonna get sent to
                            if (!(boards[k][l].getCalcBoard().isWon())) {

                                // this.enableButons(boards[k][l]);
                                this.highlightBoard(boards[k][l]);
                            }

                            else {
                                this.highlightAll();
                                while (boards[k1][l1].getCalcBoard().isWon()) {
                                    k1 = (int) (Math.random() * 3);
                                    l1 = (int) (Math.random() * 3);
                                }

                            }

                            // -------------------------------
                            // COMPUTER'S MOVE
                            // -------------------------------

                            frame.setTitle("Computer's Turn: " + SmallBoard.COMPUTER); // Shows computer's turn
                            this.disableAllButtons();

                            Timer timer = new Timer(500, a -> {
                                // the actionPerformed call-back code
                                this.computerMove(i1, j1, k1, l1);
                            });
                            timer.setRepeats(false);
                            timer.start();

                            return;

                        }

                    }

                }

            }

        }

    }

    /**
     * Computer's move
     * 
     */
    public void computerMove(int i, int j, int k, int l) {

        this.unhighlightBoards();

        boards[k][l].getCalcBoard().computerMove(); // Computer makes a random move
        int x = boards[k][l].getCalcBoard().getComputerMove()[0]; // Computer's row move
        int y = boards[k][l].getCalcBoard().getComputerMove()[1]; // Computer's column move

        // Sets the button's text to the Computer's character
        boards[k][l].get2DButtons()[x][y].setText("<html><font color = red></font>O</html>");
        boards[k][l].get2DButtons()[x][y].setBackground(Color.CYAN);

        Timer timer = new Timer(500, a -> {
            boards[k][l].get2DButtons()[x][y].setBackground(Color.WHITE);
        });
        timer.setRepeats(false); 
        timer.start();

        // 3c. If the current board just won
        if (boards[k][l].getCalcBoard().isWon()) {

            // 3d. Fills the real board with the win
            calcBigBoard.singleWin(SmallBoard.PLAYER1, i, j);
            boards[k][l].getCalcBoard().checkWinnerSmall();

            boards[k][l].displayWinner();

            // 3e. If there's an overall winner on this board
            if (calcBigBoard.checkWinner() != ' ') {
                this.displayWinner();
                return;
            }

        }

        // if there's not a winner on the board that the player's gonna get sent to
        if (!(boards[x][y].getCalcBoard().isWon())) {

            this.enableButons(boards[x][y]);
            this.highlightBoard(boards[x][y]);
        }

        else {
            this.highlightAll();
        }

        return;

    }

}
