// class ConnectFour for minigame

import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class ConnectFour {


    //~~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~~~~~

    String[][] _board;
    String _piece;


    //~~~~~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~~~~~~

    public ConnectFour() {
	_board = new String[6][7];
	_piece = "x";
    }

    //~~~~~~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~~~~~~~~~



    public void begin() {

	System.out.println("Welcome to Connect Four!");
	_piece = getPiece();

    }

    public String getPiece() {
	boolean readString = true;
	String piece = getString("What type of piece would you like to be? Enter x or o.");

	while (readString) {
	    if (!(piece.equals("x") || piece.equals("X") || piece.equals("o") || piece.equals("O"))) {
		System.out.println( "Oops, you must enter x or o, try again." );
		piece = getString("What type of piece would you like to be? Enter x or o.");
	    }
	    else {
		readString = false;
	    }
	}
	return piece;
    }
	
    public String getString( String message ) {
	boolean getString = true;
	String inString = "x";
	while (getString) {
	    System.out.println(message);
	    try {
		inString = Keyboard.readString();
		getString = false;
	    }
	    catch ( InputMismatchException e ) {
		System.out.println("Oops, invalid input, try again.");
	    }
	}
	return inString;
    }
	 


    public String toString() {

	String retStr = "";
	for (int i = 0; i < _board.length; i++ ) {
	    
	    if (i != 0) {
		retStr += "\n";
	    }
	    for ( int n = 0; n < _board[i].length; n ++ ) {
		retStr += "|_";
	    }
	    retStr += "|";
	}
	return retStr;
    }

    public static void main( String[] args ) {
	
	ConnectFour board = new ConnectFour();
	System.out.println( board );
	board.begin();
    }
}