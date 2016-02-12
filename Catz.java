import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Catz {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    public static final int NUM_LEVELS = 24;

    public Player one;

    private int levelCount;
    private boolean gameOver;
    private int spot = 1;


    private InputStreamReader isr;
    private BufferedReader in;

    ImageIcon image = new ImageIcon("hobbit.jpg");
    JPanel panel = new JPanel(new BorderLayout());
    JLabel label = new JLabel("", image, JLabel.CENTER);
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public Catz() {
        levelCount = 1;
	label.setLayout(new BorderLayout());
        gameOver = false;
        isr = new InputStreamReader( System.in );
        in = new BufferedReader( isr );
        newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    public void displayMap() {

        System.out.println("Opening the map... close it to resume.");

        Drawer mapLines = new Drawer(levelCount - 1);
        mapLines.setOpaque(false);

        label.add(mapLines);

        panel.add(label, BorderLayout.CENTER);

	JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(960, 511);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public void newGame() {

        String s;
        String name = "";
        s = "Enter a momentous adventure no one has dared attempt!  Your skills will be tested, but the prize is mighty, should you prevail.  This land may be foreign and full of unfamiliarities... More than your sword shall be tested.  Tread carefully, young wanderer, for you are far from home.\n";

        System.out.print( s );

        s = "You are at the toll gate.  Offer your name: ";
        System.out.print( s );

        try {
            name = in.readLine();
        }
        catch ( IOException e ) { }

        //instantiate the player's character
        one = new Player( name );

        displayMap();

    }//end newGame()


    /*=============================================
      post: Returns true if player wins mini-game.
            Returns false if player loses.
      =============================================*/
    public int playTurn() {
            
        String gameName = "";
        boolean outcome = true;
        int diff;

        if (levelCount == 1)
            diff = 1;
        else if (levelCount == 2)
            diff = 2;
        else
            diff = 3;

        System.out.println("Level " + levelCount);

	while (spot < 10) {
	    if (spot == 1) {
		gameName = "Tic-tac-toe";
		TicTacToe game = new TicTacToe( diff );
		outcome = game.play();
	    }
	    else if (spot == 2) {
		gameName = "Mancala";
		Mancala game = new Mancala( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 3) {
		gameName = "Connect Four";
		ConnectFour game = new ConnectFour( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 4) {
		gameName = "Sudoku";
		Sudoku game = new Sudoku( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 5) {
		gameName = "Silo";
		Silo game = new Silo( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 6) {
		gameName = "Go fish";
		GoFish game = new GoFish( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 7) {
		gameName = "Poker";
		Poker game = new Poker( diff );
		outcome = game.play( one );
	    }
	    else if (spot == 8) {
		gameName = "War";
		War game = new War( diff );
		outcome = game.play( one );
	    }

	    if (outcome) {
		System.out.println("You have completed level " + levelCount + ": " + gameName);
		spot++;

		if (spot > 8) {
		    System.out.println("You have completed all of level " + levelCount + "!");
		    displayMap();
		    levelCount++;
		    spot = 1;
		}

	    }
        
	    else {
		System.out.println("You have been bested! Your health has been reduced 50 points and you cannot pass.");
		one.addHealth( -50 );
		System.out.println("For grading/boredom purposes... do you wish to skip to the next game??  yes/no");
		System.out.print("Your choice: ");
		String a = "";
		try {
		    a = in.readLine();
		}
		catch (IOException e) {}
		if (a.equals("yes"))
		    spot++;
		if ( one.getHealth() <= 0 )
		    return -1;
		return 0;
	    }

	}

        return 1;

    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main(String[] args) {

        Catz game = new Catz();

        int levels = 0;

        while( levels < NUM_LEVELS ) {
            int a = game.playTurn();
            if ( a == -1 ) {
                System.out.println("Your health has expired!");
                break;
            }
            levels += a;
        }

        System.out.println( "Your adventure is at an end." );

    }//end main


}//end class Catz
