
import java.io.*;
import java.util.*;

public class Catz {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    public final int NUM_LEVELS = 10;

    public Player one;

    private int levelCount;
    private boolean gameOver;

    private InputStreamReader isr;
    private BufferedReader in;
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public Catz() {
	levelCount = 1;
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    public void newGame() {

	String s;
	String name = "";
	s = "Enter a momentous adventure no one has dared attempt!  Your skills will be tested, but the prize is mighty, should you prevail.  This land may be foreign and full of unfamiliarities... More than your sword shall be tested.  Tread carefully, young wanderer, for you are far from home.\n";

	System.out.print( s );

	try {
	    difficulty = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = "You are at the toll gate.  Offer your name.";
	System.out.print( s );

	try {
	    name = in.readLine();
	}
	catch ( IOException e ) { }


	//instantiate the player's character
	one = new Player( name );

    }//end newGame()


    /*=============================================
      post: Returns true if player wins mini-game.
            Returns false if player loses.
      =============================================*/
    public boolean playTurn() {

	double val = Math.random() * 8;
	String gameName = "";
	boolean outcome = true;

	System.out.println("Level " + levelCount);

	if (val < 1) {
	    gameName = "Tic-tac-toe";
	    TicTacToe game = new TicTacToe();
	    outcome = game.play();}
	else if (val < 2) {
	    gameName = "Mancala";
	    Mancala game = new Mancala();
	    outcome = game.play();}
	else if (val < 3) {
	    gameName = "Battleship";
	    Battleship game = new Battleship();
	    outcome = game.play();}
	else if (val < 4) {
	    gameName = "Concentration";
	    Concentration game = new Concentration();
	    outcome = game.play();}
	else if (val < 5) {
	    gameName = "Silo";
	    Silo game = new Silo();
	    outcome = game.play();}
	else if (val < 6) {
	    gameName = "Go fish";
	    GoFish game = new GoFish();
	    outcome = game.play();}
	else if (val < 7) {
	    gameName = "Poker";
	    Poker game = new Poker();
	    game.play();}
	else {
	    gameName = "War";
	    Gun game = new War();
	    outcome = game.play();}

	if (outcome) {
	    System.out.println("You have completed level " + levelCount + ": " + game);
	    levelCount++;
	}
	else {
	    System.out.println("You have been bested! Your health has reduced 50 points and you cannot pass.");
	    one.addHealth( -50 );
	}

    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	//loading...
	Catz game = new Catz();

	int levels = 0;

	while( levels < NUM_LEVELS ) {
	    if ( !game.playTurn() )
		break;
	    levels++;
	    System.out.println();
	}

	System.out.println( "Thy game doth be over." );

    }//end main

}//end class YoRPG
