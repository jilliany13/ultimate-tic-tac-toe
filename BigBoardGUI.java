import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;

/**
 * BigBoardGUI that runs the whole game
 * Default Constructor runs the 2 Player game
 * Other Constructor runs the game against a computer
 */
public class BigBoardGUI extends JFrame implements ActionListener {

    protected JInternalFrame frame;
    protected JLabel overallWinner;
    protected SBoardGUI[][] boards;
    protected BigBoard calcBigBoard;
    protected int count;
    protected JButton backButton;

    /**
     * Number of rows/columns of the overall grid
     */
    public static final int LENGTH = 3;

    /**
     * Game vs 2 Players
     * 
     */
    public BigBoardGUI() {

        frame = new JInternalFrame();
        boards = new SBoardGUI[3][3];
        count = 0;
        calcBigBoard = new BigBoard();

        overallWinner = new JLabel(" ");
        this.add(frame); // Adds the internal frame to the overall frame

        backButton = new JButton("Back to the Game");
        backButton.addActionListener(this);

    }

    /**
     * Initializes the Window
     */
    public void initialize() {

        frame.setLayout(new GridLayout(LENGTH, LENGTH));

        // adds the boards and buttons
        this.addBoards();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ultimate Tic Tac Toe");
        this.setBounds(250, 90, 800, 600);

        // allows you to see the frame
        frame.setVisible(true);

        overallWinner.setFont(new Font("Comic Sans", Font.PLAIN, 100));
        overallWinner.setBounds(300, 300, 500, 500);

    }

    /**
     * Adds the SmallBoardGUIs to the big board
     */
    public void addBoards() {

        // Adds a new SBoard to each space in the overall grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                boards[i][j] = new SBoardGUI();
                frame.add(boards[i][j]);

                // adds an Action Listener to each button on the board
                for (int k = 0; k < 9; k++) {
                    boards[i][j].getButtons()[k].addActionListener(this);
                }

            }

        }

    }

    /**
     * Handles what happens when you click a spot on the board
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {

            frame.setVisible(true); // we want to see the frame now
            //overallWinner.setText(calcBigBoard.checkWinner() + " Wins!");
            this.remove(overallWinner); 
            this.remove(backButton);
            //this.getContentPane().setBackground(Color.CYAN);
            frame.setTitle(calcBigBoard.checkWinner() + " Wins! (went back)");
            return;

        }

        // iterates through the 2D boards array
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                JButton[][] buttons = boards[i][j].get2DButtons();

                for (int k = 0; k < 3; k++) {

                    for (int l = 0; l < 3; l++) {

                        // 1. Finds the source of the button
                        if (e.getSource() == buttons[k][l] && buttons[k][l].getText().equals(" ")) {

                            // 2. Disables all of the buttons on the board
                            this.disableAllButtons();

                            // 3a. If it's PLAYER1's turn
                            if (count % 2 == 0) {

                                // X just played, so the next character to play is O
                                frame.setTitle("Player's Turn: " + SmallBoard.PLAYER2);

                                // 3b. Set the text to PLAYER1's char
                                buttons[k][l].setText(Character.toString(SmallBoard.PLAYER1));
                                boards[i][j].getCalcBoard().player1Move(k, l);

                                /**
                                 * If the current board doesn't have free spaces (either it just tied or it just
                                 * won)
                                 * The hasFreeSpaces method calls the tied and won method
                                 * The if statement only runs if the board does NOT have free spaces
                                 */
                                if (!boards[i][j].getCalcBoard().hasFreeSpaces()) {

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

                                    if (boards[i][j].getCalcBoard().isTied()) {

                                        // CHANGE METHOD NAME TO SINGLE WIN OR TIE??
                                        calcBigBoard.singleWin('C', i, j);
                                        boards[i][j].displayWinner();
                                    }

                                }

                                // If there's space on the board that the player's gonna get sent to
                                if (boards[k][l].getCalcBoard().hasFreeSpaces()) {

                                    this.enableButons(boards[k][l]);
                                    this.highlightBoard(boards[k][l]);
                                }

                                // If there's not space on the board that the player's gonna get sent to
                                else if (!this.calcBigBoard.hasFreeSpaces()) {
                                    this.displayWinner();
                                }

                                else {
                                    this.highlightAll();
                                }

                            } // end Player 1's (X) turn

                            // start Player 2's (O) turn
                            else {

                                // O just played (button was just clicked), so the next character to play is X
                                frame.setTitle("Player's Turn: " + SmallBoard.PLAYER1);

                                // Sets the button that was clicked to an O
                                buttons[k][l].setText(Character.toString(SmallBoard.PLAYER2));
                                boards[i][j].getCalcBoard().player2Move(k, l); // handles the move that was played

                                /**
                                 * If the current board doesn't have free spaces
                                 * (either it just tied or it just won)
                                 * The hasFreeSpaces method calls the tied and won method
                                 * The if statement only runs if the board does NOT have free spaces
                                 */
                                if (!boards[i][j].getCalcBoard().hasFreeSpaces()) {

                                    // 3c. If the current board just won
                                    if (boards[i][j].getCalcBoard().isWon()) {

                                        // 3d. Fills the real board with the win
                                        calcBigBoard.singleWin(SmallBoard.PLAYER2, i, j);
                                        boards[i][j].getCalcBoard().checkWinnerSmall();

                                        boards[i][j].displayWinner();

                                        // 3e. If there's an overall winner on this board
                                        if (calcBigBoard.checkWinner() != ' ') {
                                            this.displayWinner();
                                            return;
                                        }

                                    }

                                    if (boards[i][j].getCalcBoard().isTied()) {

                                        // CHANGE METHOD NAME TO SINGLE WIN OR TIE??
                                        calcBigBoard.singleWin('C', i, j);
                                        boards[i][j].displayWinner();
                                    }

                                }

                                // If there's space on the board that the next player's gonna get sent to
                                if (boards[k][l].getCalcBoard().hasFreeSpaces()) {

                                    this.enableButons(boards[k][l]);
                                    this.highlightBoard(boards[k][l]);
                                }

                                // If there's not space on the board that the player's gonna get sent to
                                else if (!this.calcBigBoard.hasFreeSpaces()) {
                                    this.displayWinner();
                                }

                                else {
                                    this.highlightAll();
                                }

                            }

                            count++;
                            return; // ends the loop after a button was clicked

                        }

                    }

                }

            }

        }

    }

    /**
     * Highlights the board that the user should play on
     * 
     * @param b
     *          SBoardGUI to be played on
     */
    public void highlightBoard(SBoardGUI b) {

        this.unhighlightBoards();

        if (!b.getCalcBoard().isWon()) {
            b.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        }

    }

    /**
     * Highlights the winning boards in green
     * Called in the displayWinner method
     * 
     * @param b
     *          one of the 3 winning baords
     */
    public void highlightWinningBoard(SBoardGUI b) {
        b.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
    }

    /**
     * Enables the buttons on the boards to be played
     * 
     * @param b
     *          SBoardGUI to be played on
     */
    public void enableButons(SBoardGUI b) {

        JButton[] buttons1D = b.getButtons();
        for (JButton btn : buttons1D) {
            btn.setEnabled(true);
        }

    }

    /**
     * Disables all of the buttons on the board
     * Called in the displayWinner method
     */
    public void disableAllButtons() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton[] buttons = boards[i][j].getButtons();
                for (JButton btn : buttons) {
                    btn.setEnabled(false);
                }
            }
        }
    }

    /**
     * Highlights all the boards if the move sends you to an occupied board
     * AND enables all of the buttons on the highlighted boards
     */
    public void highlightAll() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // If the board has free spaces
                if (boards[i][j].getCalcBoard().hasFreeSpaces()) {
                    boards[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
                    this.enableButons(boards[i][j]);
                }

            }
        }

    }

    /**
     * Unhighlights all of the boards
     * Called in the displayWinner method
     */
    public void unhighlightBoards() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boards[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
            }
        }
    }

    /**
     * Displays the overall winner and shows the end screen
     */
    public void displayWinner() {

        this.unhighlightBoards();
        this.disableAllButtons();

        // coordinates of each winning board x in even and y in odd
        int[] winningBoards = calcBigBoard.getWinningBoards();

        for (int i = 0; i < 6; i += 2) {
            this.highlightWinningBoard(boards[winningBoards[i]][winningBoards[i + 1]]);
        }

        Timer timer = new Timer(2000, a -> {
            frame.setVisible(false);
            overallWinner.setText(calcBigBoard.checkWinner() + " Wins!");
            this.add(overallWinner);
            this.add(backButton);
            this.getContentPane().setBackground(Color.CYAN);
            frame.setTitle(calcBigBoard.checkWinner() + " Wins!");
        });
        timer.setRepeats(false);
        timer.start();

        // THIS IF STATEMENT DOESN'T RUN
        if (calcBigBoard.isTied()) {
            this.overallWinner.setText("The game was tied!");
        } else {
            overallWinner.setText(calcBigBoard.checkWinner() + " Wins!");
        }

        this.setLayout(null);
        this.add(overallWinner);
        overallWinner.setBounds(230, 100, 600, 300);
        // this.add(frame);
        frame.setTitle(calcBigBoard.checkWinner() + " Wins!");

    }

}
