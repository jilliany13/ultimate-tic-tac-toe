// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JInternalFrame;
// import javax.swing.JLabel;

// import java.awt.*;
// import java.awt.event.*;

// /**
//  * Window class
//  */
// public class BigBoardGUIOLD extends JFrame implements ActionListener {

//     private JInternalFrame frame;
//     public static final int LENGTHW = 3;
//     private int count;
//     private char realBigBoard[][];
//     private SBoardGUI[][] boards;
//     private JLabel overallWinner;

//     /**
//      * Creates a new Window object
//      * CALCUALTOR AND GUI CLASS
//      * 
//      */
//     public BigBoardGUIOLD() {

//         count = 0;
//         frame = new JInternalFrame();

//         boards = new SBoardGUI[3][3];
//         realBigBoard = new char[3][3];

//         overallWinner = new JLabel(" ");

//         this.add(frame);

//     }

//     /**
//      * Initializes the Window
//      */
//     public void initialize() {

//         frame.setLayout(new GridLayout(LENGTHW, LENGTHW));

//         // adds the boards and buttons
//         this.addBoards();

//         frame.setVisible(true);
//         // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         // frame.setTitle("Ultimate Tic Tac Toe"); CHANGE TO DISPLAY THE WINNER AT THE
//         // TOP
//         frame.setSize(800, 600);

//         this.setVisible(true);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setTitle("Ultimate Tic Tac Toe");
//         this.setSize(800, 600);

//         // allows you to see the frame
//         frame.setVisible(true);

//         this.fillRealBigBoard();

//         overallWinner.setFont(new Font("Comic Sans", Font.PLAIN, 500));
//         overallWinner.setBounds(300, 300, 500, 500);

//     }

//     /**
//      * ADDED as resetBoard()
//      * Fills the realBigBoard with empty characters

//      */
//     public void fillRealBigBoard() {

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 realBigBoard[i][j] = ' ';
//             }
//         }
//     }

//     public void addBoards() {

//         // Adds a new SBoard to each space in the overall grid
//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {

//                 boards[i][j] = new SBoardGUI();
//                 frame.add(boards[i][j]);

//                 // adds an Action Listener to each button on the board
//                 for (int k = 0; k < 9; k++) {
//                     boards[i][j].getButtons()[k].addActionListener(this);
//                 }

//             }

//         }

//     }

//     /**
//      * ADDED
//      * Checks if there's a winner of the overall game
//      * 
//      * @return winner
//      *         the winner of the overall game
//      */
//     public char checkWinner() {

//         // checks the rows and columns for winner
//         for (int i = 0; i < 3; i++) {
//             if (realBigBoard[i][0] == realBigBoard[i][1] && realBigBoard[i][0] == realBigBoard[i][2]
//                     && realBigBoard[i][0] != ' ') {
//                 return realBigBoard[i][0];
//             }

//             if (realBigBoard[0][i] == realBigBoard[1][i] && realBigBoard[0][i] == realBigBoard[2][i]
//                     && realBigBoard[i][0] != ' ') {
//                 return realBigBoard[i][0];
//             }
//         }

//         // checks diagonals for winner
//         if (realBigBoard[0][0] == realBigBoard[1][1] && realBigBoard[1][1] == realBigBoard[2][2]
//                 && realBigBoard[0][0] != ' ') {
//             return realBigBoard[0][0];
//         }

//         if (realBigBoard[0][2] == realBigBoard[1][1] && realBigBoard[1][1] == realBigBoard[2][0]
//                 && realBigBoard[0][2] != ' ') {
//             return realBigBoard[0][2];
//         }

//         return ' ';

//     }

//     /**
//      * Handles what happens when you click a spot on the board
//      */
//     @Override
//     public void actionPerformed(ActionEvent e) {

//         // iterates through the 2D boards array
//         for (int i = 0; i < 3; i++) {

//             for (int j = 0; j < 3; j++) {

//                 JButton[][] buttons = boards[i][j].get2DButtons();

//                 for (int k = 0; k < 3; k++) {

//                     for (int l = 0; l < 3; l++) {

//                         // 1. Finds the source of the button
//                         if (e.getSource() == buttons[k][l] && buttons[k][l].getText().equals(" ")) {

//                             // 2. Disables all of the buttons on the board
//                             this.disableAllButtons();

//                             // 3a. If it's PLAYER1's turn
//                             if (count % 2 == 0) {

//                                 // 3b. Set the text to PLAYER1's char
//                                 buttons[k][l].setText(Character.toString(SmallBoard.PLAYER1));
//                                 boards[i][j].getCalcBoard().player1Move(k, l);
                                

//                                 // 3c. If the current board just won
//                                 if (boards[i][j].getCalcBoard().isWon()) {

//                                     // 3d. Fills the real board with the win
//                                     // In NEW GUI: 
//                                     // calcBigBoard.singleWin(PLAYER1, i, j);
//                                     // instead of: 
//                                     realBigBoard[i][j] = SmallBoard.PLAYER1;

//                                     boards[i][j].displayWinner();

//                                     // 3e. If there's an overall winner on this board
//                                     // IN NEW GUI: 
//                                     // if(calcBigBoard.checkWinner() != ' ')
//                                     if (this.checkWinner() != ' ') {
//                                         this.displayWinner();
//                                         return;
//                                     }

//                                 }

//                                 // if there's not a winner on the board that the player's gonna get sent to
//                                 if (!(boards[k][l].getCalcBoard().isWon())) {

//                                     this.enableButons(boards[k][l]);
//                                     this.highlightBoard(boards[k][l]);
//                                 }

//                                 else {
//                                     this.highlightAll();
//                                 }

//                             }

//                             else {

//                                 buttons[k][l].setText(Character.toString(SmallBoard.PLAYER2));
//                                 boards[i][j].getCalcBoard().player2Move(k, l);

//                                 // if the current board was won
//                                 if (boards[i][j].getCalcBoard().isWon()) {
//                                     realBigBoard[i][j] = SmallBoard.PLAYER2;
//                                     boards[i][j].displayWinner();

//                                     if (this.checkWinner() != ' ') {
//                                         this.displayWinner();
//                                         return;
//                                     }

//                                 }

//                                 // if there's not a winner on the board that the player's gonna get sent to
//                                 if (!(boards[k][l].getCalcBoard().isWon())) {

//                                     this.enableButons(boards[k][l]);
//                                     this.highlightBoard(boards[k][l]);
//                                 }

//                                 else {
//                                     this.highlightAll();

//                                 }
//                             }

//                             count++;
//                             return; // ends the loop after a button was clicked

//                         }

//                     }

//                 }

//             }

//         }

//     }

//     /**
//      * Highlights the board that the user should play on
//      * 
//      * @param b
//      *          SBoardGUI to be played on
//      */
//     public void highlightBoard(SBoardGUI b) {

//         this.unhighlightBoards();
//         b.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));

//     }

//     /**
//      * Enables the buttons on the boards to be played
//      * 
//      * @param b
//      *          SBoardGUI to be played on
//      */
//     public void enableButons(SBoardGUI b) {

//         JButton[] buttons1D = b.getButtons();
//         for (JButton btn : buttons1D) {
//             btn.setEnabled(true);
//         }

//     }

//     public void disableAllButtons() {

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 JButton[] buttons = boards[i][j].getButtons();
//                 for (JButton btn : buttons) {
//                     btn.setEnabled(false);
//                 }
//             }
//         }
//     }

//     /**
//      * Highlights all the boards if the move sends you to an occupied board
//      */
//     public void highlightAll() {

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 if (!boards[i][j].getCalcBoard().isWon()) {
//                     boards[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
//                     this.enableButons(boards[i][j]);
//                 }

//             }
//         }

//         for (int x = 0; x < 3; x++) {
//             for (int y = 0; y < 3; y++) {
//                 if (!boards[x][y].getCalcBoard().isWon()) {
//                     this.enableButons(boards[x][y]);
//                 }

//             }
//         }

//     }

//     /**
//      * Unhighlights all boards
//      */
//     public void unhighlightBoards() {

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 boards[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
//             }
//         }
//     }

//     /**
//      * Displays the overall winner
//      */
//     public void displayWinner() {

//         // SOMETHING IS UNDOING THE UNHIGHLIGHTING AND DISABLING
//         this.unhighlightBoards();
//         this.disableAllButtons();

//         // THIS LINE RUNS BUT THE TWO ABOVE DON'T WORK
//         overallWinner.setText(checkWinner() + " Wins!");
//         // if (this.checkWinner() == 'O') {
//         // overallWinner.setBounds(80, 20, 150, 150);
//         // }
//         this.add(overallWinner);
//         // this.add(frame);
//         frame.setTitle(checkWinner() + " Wins!");

//     }

// }
