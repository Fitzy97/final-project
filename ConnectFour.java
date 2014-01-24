// class ConnectFour for minigame

import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class ConnectFour extends MiniGame {


    //~~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~~~~~

    int[][] _board;
    int _piece, _pieceC;
    // 1 is x, 2 is o

    boolean _playerT;


    //~~~~~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~~~~~~

    public ConnectFour(int diff) {
	_board = new int[6][7];
	_piece = 1;
	_pieceC = 2;
	_playerT = true;
	_difficulty = diff;
    }

    //~~~~~~~~~~~~~~~~~~PLAY METHOD~~~~~~~~~~~~~~~~~~~~~~

    public boolean play( Player player) {

	System.out.println("Welcome to Connect Four! Your goal is to get 4 in a row going either horizontally, vertically, or diagonally by dropping your piece into your chosen column of the board.  Remember to block your opponent! Good luck!");

	pause(6);

	begin();
	while (whoWon() == -1 && stillRoom() ) {
	    round();
	}
	return printResults();

    }

    //~~~~~~~~~~~~~~~~~~ROUND METHOD~~~~~~~~~~~~~~~~~~~~~

    public void round() {

	if (_playerT) {
	    System.out.println( this );
	    playerTurn();
	}
	else {
	    compTurn();
	}
    }


    //~~~~~~~~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~


    public void playerTurn() {

	int choice = 1;
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

	int choice = -1;
	boolean getChoice = true;
	
	pause(1);

	System.out.println("\n------------------------------");
	System.out.println("COMPUTER'S TURN");
	System.out.println("------------------------------");
	
	pause(1);

	int winMove =   bestColumn(_pieceC);
	int bestBlock = bestColumn(_piece);
	
	
	int randNum = (int) (Math.random() * 3);

	if ( winMove != -1 && randNum < _difficulty) {
	    choice = winMove;
	}
	else if ( bestBlock != -1 && randNum <_difficulty ) {
	    choice = bestBlock;
	}
	
	else {
	    if (randNum < _difficulty) {
	    choice = nextBestColumn( _pieceC );
	    }
	    else {
		choice = (int) (Math.random() * 7);
	    }
	}
	
	placePiece( choice + 1, _pieceC );
	System.out.println( this );
	pause(2);
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
		if ( _board[i][n]    ==  piece         &&
		     _board[i][n]    == _board[i][n+1] &&
		     _board[i][n+1] == _board[i][n+2]
		     ) {
		    if ( (n + 3) < 7 && _board[i][n+3] == 0 && isTop(i, (n+3)) ){
			return (n + 3);
		    }
		}
	    }
	}
    	
	// look down
	for (int i = 0; i < 7; i++) {
	    for (int n = 5; n > 2; n--) {
		if (_board[n][i]   ==  piece          &&
		    _board[n][i]   == _board[n-1][i]  &&
		    _board[n-1][i] == _board[n-2][i]) {
    			    	
		    if ( (n-3) > -1 && _board[n-3][i] == 0 ) {
			return i;
		    } 
		}
	    }
	}
  
	// look for downwards diagonal
	for (int i = 0; i < 4; i ++) {
	    for (int n = 0; n < 5; n++) {
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
	for (int i = 5; i > 1; i-- ) {
	    for (int n = 0; n < 5; n++) {
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

		    
    // method to find next best move
    public int nextBestColumn(int piece){
    	
	int best = 0;
	
	for (int i = 1; i < 7; i++) {
	    if ( numTouching( piece, i ) > numTouching( piece, best ) ) {
		best = i;
	    }
	}
	if ( numTouching( piece, best ) == 0 ) {
	    return ((int) (Math.random() * 7));
	}
	else {
	    return best;
	}	
    }	

    // method to see how many pieces of same type a piece dropped in would touch	
    public int numTouching(int piece, int column) {
	int counter = 0;
	int row = findRow( column );

	for (int x = -1; x <= 1; x+=2) {
	    for (int y = -1; y <= 1; y++ ) {
		if ( (row + x) < 6 && (row + x) > -1 && (column + y) < 7 && (column + y) > -1) {
		    if (_board[row+x][column+y] == piece){
			counter++;
		    }
		}
	    }
	}

	if ((row + 1) < 6) {
	    if (_board[row + 1][column] == piece) {
		counter++;
	    }
	}
	return counter;
    }

	
    /*
      _board[i-1][n-1]                  _board[i-1][n+1]
      _board[i]  [n-1] NEW PIECE [i][n] _board[i][n+1]
      _board[i+1][n-1] _board[i+1][n]   _board[i+1][n+1]
    */



    public int findRow( int column ) {
	
	for (int i = 5; i > -1; i--){
	    if ( _board[i][column] == 0 ) {
		return i;
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
	for (int i = 5; i > 2; i-- ) {
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
	if ( _board[row][column] == 0 && ( (row + 1) > 5 || _board[row+1][column] != 0)) {
	    return true;
	}
	return false;
    }

    public boolean stillRoom() {
	int zeroCnt = 0;
	for (int i = 0; i < 6; i++) {
	    for (int n = 0; n < 7; n++) {
		if (_board[i][n] == 0) {
		    zeroCnt++;
		}
	    }
	}
	if (zeroCnt == 0) {
	    return false;
	}
	else {
	    return true;
	}
    }

    public boolean printResults() {
	if (whoWon() == _piece) {
	    System.out.println("You won!!");
	    return true;
	}
	else if (whoWon() == _pieceC) {
	    System.out.println("The computer won, better luck next time.");
	    return false;
	}
	else {
	    System.out.println("It is a tie!");
	    return false;
	}
    }

    public static void pause(int seconds){
	Date start = new Date();
	Date end = new Date();
	while(end.getTime() - start.getTime() < seconds * 1000){
	    end = new Date();
	}
    }


    public static void main( String[] args ) {
	
	ConnectFour board = new ConnectFour(1);
	Player playa = new Player();
	board.play(playa);

    }
}
