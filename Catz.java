
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
    public int playTurn() {

	double val = Math.random() * 8;
	String gameName = "";
	boolean outcome = true;

	System.out.println("Level " + levelCount);

	if (val < 1) {
	    gameName = "Tic-tac-toe";
	    TicTacToe game = new TicTacToe( levelCount );
	    outcome = game.play();}
	else if (val < 2) {
	    gameName = "Mancala";
	    Mancala game = new Mancala( levelCount );
	    outcome = game.play();}
	else if (val < 3) {
	    gameName = "Battleship";
	    Battleship game = new Battleship( levelCount );
	    outcome = game.play();}
	else if (val < 4) {
	    gameName = "Concentration";
	    Concentration game = new Concentration( levelCount );
	    outcome = game.play();}
	else if (val < 5) {
	    gameName = "Silo";
	    Silo game = new Silo( levelCount );
	    outcome = game.play();}
	else if (val < 6) {
	    gameName = "Go fish";
	    GoFish game = new GoFish( levelCount );
	    outcome = game.play();}
	else if (val < 7) {
	    gameName = "Poker";
	    Poker game = new Poker( levelCount );
	    game.play();}
	else {
	    gameName = "War";
	    Gun game = new War( levelCount );
	    outcome = game.play();}

	if (outcome) {
	    System.out.println("You have completed level " + levelCount + ": " + gameName);
	    levelCount++;
	}
	else {
	    System.out.println("You have been bested! Your health has reduced 50 points and you cannot pass.");
	    one.addHealth( -50 );
	    if ( one.getHealth() <= 0 )
		return -1;
	    return 0;
	}

	return 1;

    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	Catz game = new Catz();

	int levels = 0;

	while( levels < NUM_LEVELS ) {
	    if ( game.playTurn() == -1 )
		break;
	    levels += game.playTurn();
	    System.out.println();
	}

	System.out.println( "Your adventure is at an end." );

    }//end main

}//end class Catz
