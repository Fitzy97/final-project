// class ConnectFour for minigame

import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class ConnectFour {


    //~~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~~~~~

    int[][] _board;
    int _piece, _pieceC;
    // 1 is x, 2 is o

    boolean _playerT;


    //~~~~~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~~~~~~

    public ConnectFour() {
	_board = new int[6][7];
	_piece = 1;
	_pieceC = 2;
	_playerT = true;
    }

    //~~~~~~~~~~~~~~~~~~PLAY METHOD~~~~~~~~~~~~~~~~~~~~~~

    public void play() {
	begin();
	while (whoWon() == -1) {
	    round();
	}
	System.out.println( whoWon() );
    }

    //~~~~~~~~~~~~~~~~~~ROUND METHOD~~~~~~~~~~~~~~~~~~~~~

    public void round() {
	System.out.println( this );
	if (_playerT) {
	    playerTurn();
	}
	else {
	    compTurn();
	}
    }


    //~~~~~~~~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~


    public void playerTurn() {

	int choice;
	boolean readChoice = true;
	
	System.out.println("\n------------------------------");
	System.out.println("PLAYER'S TURN");
	System.out.println("------------------------------");

	while (readChoice) {
	    
	    choice = getInt("Which column would you like to drop your piece into?");

	    if ( choice < 1 || choice > 7 ) {
		System.out.println( "Oops, that is not the number of a column. Enter 1-7.");
	    }
	    else if (colFull(choice)) {
		System.out.println( "Oops, that column is full, try again." );
	    }
	    else {
		readChoice = false;
		placePiece( choice, _piece );
		System.out.println( this );
		_playerT = false;

	    }
	}
    }

    public void compTurn() {

	int choice;
	boolean readChoice = true;
	
	System.out.println("\n------------------------------");
	System.out.println("COMPUTER'S TURN");
	System.out.println("------------------------------");

	int winMove =   bestColumn(_pieceC);
	int bestBlock = bestColumn(_piece);

	if ( winMove != -1 ) {
	    // insert math.random() later
	    choice = winMove;
	}
	else if ( bestBlock != -1 ) {
	    choice = bestBlock;
	}
	else {
	    choice = (int) (Math.random() * 7);
	}
	
	placePiece( choice, _pieceC );
	System.out.println( this );
	_playerT = true;
    }
	   


    //~~~~~~~~~~~~~~~~~~OTHER METHODS~~~~~~~~~~~~~~~~~~~~
    public void begin() {

	System.out.println("Welcome to Connect Four!");
	String piece = getPiece();
	if ( piece.equals("x") || piece.equals("X") ) {
	    _piece = 1;
	    _pieceC = 2;
	}
	else {
	    _piece = 2;
	    _pieceC = 1;
	}
	    
    }

    public boolean colFull(int col) {
	return (_board[0][col - 1] != 0);
    }

    public boolean placePiece(int colGiven, int piece) {
	int col = colGiven - 1;
	    
	for (int i = 5; i > -1; i--) {
	    if ( _board[i][col] == 0 ) {
		_board[i][col] = piece;
		return true;
	    }
	}
	return false;
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

    public int getInt(String message) {
	boolean readInt = true;
	int num = -1;

	while (readInt) {
	    System.out.println(message);
	    try {
		num = Keyboard.readInt();
		readInt = false;
	    }
	    catch ( InputMismatchException e ) {
		System.out.println("Oops, invalid input, try again.");
	    }
	}

	return num;
    }
    
    // method to find if any moves to win
    public int bestColumn(int piece){
    	
    	
    	// look across
    	for (int i = 0; i < _board.length; i++) {
	    for (int n = 0; n < 4; n++) {
		( _board[i][n]    ==  piece         &&
		  _board[i][n]    == _board[i][n+1] &&
		  _board[i][n+1]) == _board[i][n+2]
		    ) {
		if ( (n -1) > -1 && _board[i][n-1] == 0 && isTop(i, (n-1) )) {
		    return (n - 1);
		}
		else if ( (n + 3) < 7 && _board[i][n+3] == 0 && isTop(i, (n+3)) ){
		    return (n + 3);
		}
	    }
    	
	    // look down
	    for (int i = 0; i < board[0].length; i++) {
		for (int n = 0; n < 3; n++){
		    if (_board[n][i]   ==  piece          &&
			_board[n][i]   == _board[n+1][i]  &&
			_board[n+1][i] == _board[n+2][i]) {
    			    	
			if ( (n-1) > -1 && _board[n-1][i] == 0 ) {
			    return i;
			} 
		    }
		}
	    }
  
	    // look for downwards diagonal
	    for (int i = 0; i < 3; i ++) {
		for (int n = 0; n < 4; n++) {
		    if ( _board[i][n]     ==  piece           &&
			 _board[i][n]     == _board[i+1][n+1] &&
			 _board[i+1][n+1] == _board[i+2][n+2]) {

			if ( (i-1) > -1 && (n-1) > -1 && _board[i-1][n-1] == 0 && isTop((i-1),(n-1))) {
			    return (n-1);
			}
			else if ((i +1) < 6 && (n+1) < 7 && _board[i+1][n+1] == 0 && isTop(i+1,n+1)) { 
			    return (n+1);
			}
		    }
		}
	    }

	    // look for upwards diagonal
	    for (int i = 3; i < 6; i++ ) {
		for (int n = 0; n < 4; n++) {
		    if ( _board[i][n]     ==  piece           &&
			 _board[i][n]     == _board[i-1][n+1] &&
			 _board[i-1][n+1] == _board[i-2][n+2] ) {

			if ( (i+1) < 6 && (n-1) > -1 && _board[i+1][n-1] == 0 && isTop(i+1,n-1) ) {
			    return (i+1);
			}
			else if ( (i-3) > -1 && (n+3) < 7 && _board[i-3][n+3] == 0 && isTop(i-3,n+3)) {
			    return (i -3);
			}
		    }
		}
	    }
	    return -1;
	}

		    
	
	
	
	// returns 1 if x, 2 is o, -1 if no one
	public int whoWon() {
	
	    // check for across
	    for (int i = 0; i < _board.length; i++) {
		for (int n = 0; n < 4; n++) {
		    if ( _board[i][n]   == _board[i][n+1] &&
			 _board[i][n+1] == _board[i][n+2] &&
			 _board[i][n+2] == _board[i][n+3] &&
			 _board[i][n] != 0 ) {

			if (_board[i][n] == 1){
			    return 1;
			}
			else {
			    return 2;
			}
		    }
		}
	    }

	    // check for down
	    for (int i = 0; i < _board[0].length; i++) {
		for (int n = 0; n < 3; n++) {
		    if ( _board[n][i]   == _board[n+1][i] &&
			 _board[n+1][i] == _board[n+2][i] &&
			 _board[n+2][i] == _board[n+3][i] &&
			 _board[n][i] != 0 ) {

			if (_board[n][i] == 1){
			    return 1;
			}
			else {
			    return 2;
			}
		    }
		}
	    }

	    // check for downwards diagonal
	    for (int i = 0; i < 3; i ++) {
		for (int n = 0; n < 4; n++) {
		    if ( _board[i][n]     == _board[i+1][n+1] &&
			 _board[i+1][n+1] == _board[i+2][n+2] &&
			 _board[i+2][n+2] == _board[1+3][n+3] &&
			 _board[i][n] != 0) {

			if (_board[i][n] == 1) {
			    return 1;
			}
			else {
			    return 2;
			}
		    }
		}
	    }

	    // check for upwards diagonal
	    for (int i = 3; i < 6; i++ ) {
		for (int n = 0; n < 4; n++) {
		    if ( _board[i][n]     == _board[i-1][n+1] &&
			 _board[i-1][n+1] == _board[i-2][n+2] &&
			 _board[i-2][n+2] == _board[i-3][n+3] &&
			 _board[i][n] != 0) {

			if (_board[i][n] == 1) {
			    return 1;
			}
			else {
			    return 2;
			}
		    }
		}
	    }
	    return -1;
	}


	public String toString() {

	    String retStr = "-----------------\n";
	    for (int i = 0; i < _board.length; i++ ) {
	    
		if (i != 0) {
		    retStr += "\n";
		}
		for ( int n = 0; n < _board[i].length; n ++ ) {
		    retStr += "|";
		    if (_board[i][n] == 1) {
			retStr += "x";
		    }
		    else if (_board[i][n] == 2) {
			retStr += "o";
		    }
		    else {
			retStr += "_";
		    }
		}
		retStr += "|";
	    }
	    retStr += "\n 1 2 3 4 5 6 7 ";
	    retStr += "\n-----------------";
	    return retStr;
	}


	public boolean isTop( int row, int column ) {
	    if ( row < 0 || column < 0 || row > 5 || column > 6 ) {
		return false;
	    }
	    if ( _board[r][c] && ( (r + 1) > 5 || _board[r+1][c] == 0)) {
		    return true;
	    }
	}

	public static void main( String[] args ) {
	
	    ConnectFour board = new ConnectFour();
	    board.play();

	}
    }
