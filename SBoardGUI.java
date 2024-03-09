import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
//import java.awt.event.*;

/**
 * SBoardGUI Class
 * extends JPanel so it can be added to the window as a JLabel
 * ADD A LABEL SAYING WHO'S TURN IT IS (PLAYER / COMPUTER)
 * CHANGE COLOR OF BORDER BASED ON IF YOU CAN PLAY ON THAT BOARD
 * EXTRA: DISABLE BUTTONS IF YOU CAN'T PLAY ON THAT BOARD
 * CHECK PLAYER1MOVE LOGIC WITH THE TIC TAC TOE C GAME
 */
public class SBoardGUI extends JPanel {

    private JPanel panel;
    private JButton buttons1D[];
    private SmallBoard calcBoard;
    private JLabel winner;
    private Border blackline;

    /**
     * Number of rows/columns of the overall grid
     */
    public static final int LENGTH = 3;

    /**
     * Creates a new SBoardGUI object
     */
    public SBoardGUI() {

        calcBoard = new SmallBoard(); // where the calculations for the Small Board happen
        winner = new JLabel(" "); // winner of the Small Board
        panel = new JPanel(); // Smaller Panel (the grid of buttons)
        buttons1D = new JButton[9]; // buttons array for the Small Board
        blackline = BorderFactory.createLineBorder(Color.black, 1);

        this.initialize();

    }

    /**
     * Initializes the board
     */
    public void initialize() {

        this.styleAndAddButtons();
        this.addComponents();
    }

    /**
     * Adds the components to the SBoard and to the panel
     */
    public void addComponents() {

        this.setBackground(Color.white);
        this.add(panel);
        this.setLayout(null);
        this.setBorder(blackline);

        panel.setBounds(60, 20, 150, 150);
        panel.setLayout(new GridLayout(LENGTH, LENGTH)); // Sets the Small Board to a grid of 3x3

        winner.setFont(new Font("Comic Sans", Font.PLAIN, 150));
        winner.setBounds(90, 20, 150, 150);

    }

    /**
     * Styles the button borders and adds them to the panel
     * The specific borders of each button gives it a tic-tac-toe grid style look
     */
    public void styleAndAddButtons() {

        for (int i = 0; i < 9; i++) {
            buttons1D[i] = new JButton(" ");
            buttons1D[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));

            if (i == 2 || i == 5) {
                buttons1D[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            }

            if (i == 6 || i == 7) {
                buttons1D[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
            }

            if (i == 8) {
                buttons1D[i].setBorder(BorderFactory.createEmptyBorder());
            }

            buttons1D[i].setBackground(Color.white);
            buttons1D[i].setPreferredSize(new Dimension(50, 50));
            buttons1D[i].setFont(new Font("Comic Sans", Font.PLAIN, 25));

            panel.add(buttons1D[i]);

        }

    }

    /**
     * Disables all of the buttons on this board
     */
    public void disableAllButtons() {

        for (JButton btn : buttons1D) {
            btn.setEnabled(false);
        }

    }

    /**
     * Displays the winner on the board
     */
    public void displayWinner() {

        // if the board doesn't have a winner, then it's Cat's (tied)
        if (calcBoard.checkWinnerSmall() == ' ') {
            winner.setText("C");
        }

        else {
            winner.setText(Character.toString(calcBoard.checkWinnerSmall()));
        }

        // lines up the O on the Small Board
        if (calcBoard.checkWinnerSmall() == SmallBoard.PLAYER2) {
            winner.setBounds(80, 20, 150, 150);
        }

        this.add(winner);
        this.add(panel);

        this.disableAllButtons();
        this.setBorder(blackline);
        // this.add(panel);

    }

    /**
     * 
     * @return buttons
     *         array of JButtons
     */
    public JButton[] getButtons() {

        return buttons1D;
    }

    /**
     * Creates a 2D array of buttons from the 1D array
     * 
     * @return buttons2D
     *         2D array of buttons
     */
    public JButton[][] get2DButtons() {

        JButton[][] buttons2D = new JButton[3][3];

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons2D[i][j] = buttons1D[count];
                count++;
            }
        }

        return buttons2D;
    }

    /**
     * 
     * @return calcBoard
     *         the SmallBoard that the SBoardGUI is connected to
     */
    public SmallBoard getCalcBoard() {

        return calcBoard;
    }

}
