import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Game lobby
 * Asks the user if they want to play against the computer or 2 player
 */
public class Lobby extends JFrame implements ActionListener {

    private JLabel instructions;
    private JButton onePlayerBtn;
    private JButton twoPlayerBtn;

    /**
     * Creates a new Lobby
     */
    public Lobby() {

        instructions = new JLabel("Which game would you like to play?");
        onePlayerBtn = new JButton("1 Player");
        twoPlayerBtn = new JButton("2 Players");

    }

    /**
     * To Do - Make the sizes relative to the Window Size
     * Initializes the Lobby
     * Called in TicTacToe Runner
     * 
     */
    public void initialize() {

        this.setLayout(null);
        this.setBounds(250, 90, 800, 350);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lobby: Ultimate Tic Tac Toe");

        instructions.setFont(new Font("Comic Sans", Font.BOLD, 15));
        instructions.setBounds(250, SwingConstants.TOP, 300, 100);
        this.add(instructions);

        Font btnFont = new Font("Comic Sans", Font.BOLD, 15);

        onePlayerBtn.setBounds(200, SwingConstants.TOP + 150, 125, 75);
        onePlayerBtn.setFont(btnFont);
        onePlayerBtn.setBackground(Color.GREEN);
        onePlayerBtn.addActionListener(this);

        twoPlayerBtn.setBounds(450, SwingConstants.TOP + 150, 125, 75);
        twoPlayerBtn.setFont(btnFont);
        twoPlayerBtn.setBackground(Color.CYAN);
        twoPlayerBtn.addActionListener(this);

        this.add(onePlayerBtn);
        this.add(twoPlayerBtn);

    }

    /**
     * Handles when a button is clicked in the lobby
     * 
     * @param e
     *          the event performed after the button was clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == twoPlayerBtn) {

            BigBoardGUI board = new BigBoardGUI();
            board.initialize();
            this.dispose();

        }

        else {

            BigC_GUI board = new BigC_GUI();
            board.initialize();
            this.dispose();
        }

    }
}
