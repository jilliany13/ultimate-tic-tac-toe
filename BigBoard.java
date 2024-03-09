/**
 * Big Board class
 */
public class BigBoard {

    private char realBigBoard[][];
    private int winningBoards[];

    /**
     * Creates a new BigBoard class
     */
    public BigBoard() {

        realBigBoard = new char[3][3]; // stores the X/O's for the overall board
        winningBoards = new int[6];
        this.resetBoard();

    }

    /**
     * Resets the board back to the beginning (filling it with empty chars)
     */
    public void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                realBigBoard[i][j] = ' ';
            }
        }
    }

    /**
     * Checks if there's a winner of the overall game
     * 
     * @return winner
     *         the winner of the overall game
     */
    public char checkWinner() {

        // checks the rows and columns for winner
        for (int i = 0; i < 3; i++) {
            if (realBigBoard[i][0] == realBigBoard[i][1] && realBigBoard[i][0] == realBigBoard[i][2]
                    && realBigBoard[i][0] != ' ') {
                winningBoards[0] = i;
                winningBoards[1] = 0;
                winningBoards[2] = i;
                winningBoards[3] = 1;
                winningBoards[4] = i;
                winningBoards[5] = 2;
                return realBigBoard[i][0];
            }

            if (realBigBoard[0][i] == realBigBoard[1][i] && realBigBoard[0][i] == realBigBoard[2][i]
                    && realBigBoard[0][i] != ' ') {
                winningBoards[0] = 0;
                winningBoards[1] = i;
                winningBoards[2] = 1;
                winningBoards[3] = i;
                winningBoards[4] = 2;
                winningBoards[5] = i;
                return realBigBoard[0][i];
            }
        }

        // checks diagonals for winner
        if (realBigBoard[0][0] == realBigBoard[1][1] && realBigBoard[1][1] == realBigBoard[2][2]
                && realBigBoard[0][0] != ' ') {
            winningBoards[0] = 0;
            winningBoards[1] = 0;
            winningBoards[2] = 1;
            winningBoards[3] = 1;
            winningBoards[4] = 2;
            winningBoards[5] = 2;
            return realBigBoard[0][0];
        }

        if (realBigBoard[0][2] == realBigBoard[1][1] && realBigBoard[1][1] == realBigBoard[2][0]
                && realBigBoard[0][2] != ' ') {
            winningBoards[0] = 0;
            winningBoards[1] = 2;
            winningBoards[2] = 1;
            winningBoards[3] = 1;
            winningBoards[4] = 2;
            winningBoards[5] = 0;
            return realBigBoard[0][2];
        }

        return ' ';

    }

    /**
     * Handles if there's a winner on one of the Single Boards
     * 
     * @param p
     *          player character for the one who got the Single Win (win on a small
     *          board)
     */
    public void singleWin(char p, int row, int col) {

        realBigBoard[row][col] = p;

    }

    /**
     * Winning boards are the small boards that won the game
     * 
     * @return winning boards
     *         the row/col coordinates in order
     */
    public int[] getWinningBoards() {
        return winningBoards;
    }

    /**
     * 
     * @return realBigBoard
     *         2D array showing which small boards are won
     */
    public char[][] getRealBigBoard() {
        return realBigBoard;
    }

    /**
     * 
     * @return true if the board is tied and false otherwise
     */
    public boolean isTied() {

        // If the board has a winner, then it's not tied
        if (this.checkWinner() != ' ') {
            return false;
        }

        // Checks all of the spaces on the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // if there is at least one empty space on the board
                if (realBigBoard[i][j] == ' ') {
                    return false; // the board isn't tied yet
                }
            }
        } // end for loop, there are no free spaces on the board

        // no winner and no free spaces 
        return true;
    }

    /**
     * The board has free spaces if it's not tied and there's not a winner
     * 
     * @return true if there is at least one free space on the board
     *         and false otherwise
     */
    public boolean hasFreeSpaces() {

        return !isTied() && checkWinner() == ' ';
    }

}
