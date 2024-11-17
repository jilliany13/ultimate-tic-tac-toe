/**
 * Small Board Class
 * LOOP RUNS WHILE
 */
public class SmallBoard {

    private int[] computerMove;
    private char[][] board;
    private boolean isWon;
    private final int ROWS = TicTacToeRunner.ROWS;
    private final int COLS = TicTacToeRunner.COLS;

    // Have an opening screen where the person gets to choose their character
    public static final char PLAYER1 = 'X';
    public static final char COMPUTER = 'O';
    public static final char PLAYER2 = 'O';

    /**
     * Creates a new SmallBoard object
     */
    public SmallBoard() {

        isWon = false; // True if the Small Board is won
        // isTied = false;
        computerMove = new int[2];
        board = new char[3][3];

        this.resetBoard();
    }

    /**
     * Checks if there's a winner on the Small Board
     * CALL THIS METHOD IN THE WINDOW AND IF THERE IS A WINNER, PRINT A BIG VERSION
     * OF THAT CHARACTER ON THE SMALL BOARD
     * 
     * @return the char of the winner
     */
    public char checkWinnerSmall() {

        for (int i = 0; i < ROWS; i++) {

            // Checks the rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                isWon = true;
                return board[i][0];
            }

            // checks the columns
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                isWon = true;
                return board[0][i];
            }
        }

        // Checks diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            isWon = true;
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            isWon = true;
            return board[0][2];
        }

        // if there's no winner
        return ' ';

    }

    /**
     * Resets the board to have no moves
     */
    public void resetBoard() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Player 1's move
     */
    public void player1Move(int row, int col) {

        board[row][col] = PLAYER1;
        this.checkWinnerSmall();
    }

    /**
     * Player 2's move
     */
    public void player2Move(int row, int col) {

        board[row][col] = PLAYER2;
        this.checkWinnerSmall();
    }

    /**
     * The Computer's Move
     */
    public void computerMove() {

        int row;
        int col;

        // finds a random number between 0 and 2
        do {

            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);

        } while (board[row][col] != ' ');

        board[row][col] = COMPUTER;
        computerMove[0] = row;
        computerMove[1] = col;
        this.checkWinnerSmall();

    }

    /**
     * 
     * @return computerMove
     *         the computer's move
     */
    public int[] getComputerMove() {
        return computerMove;
    }

    /**
     * 
     * @return isWon
     *         whether or not the small board is won
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Figures out if the board is tied and returns if it is
     * 
     * @return isTied
     *         whether or not the small board is tied
     */
    public boolean isTied() {

        if (this.isWon()) {
            return false;
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                // if there is at least one empty space on the board
                if (board[i][j] == ' ') {
                    return false; // board still has empty spaces, so it's not tied
                }
            }
        } // end for loop, the board has no empty spaces

        // the board is full and not won, so it's tied
        return true;
    }

    /**
     * The board has free spaces if it's not tied and there's not a winner
     * 
     * @return true if there is at least one free space on the board
     *         and false otherwise
     */
    public boolean hasFreeSpaces() {

        return !this.isTied() && !this.isWon();
    }

}