import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class Sudoku extends MiniGame{


    //~~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~~~~~

    private int[][] _board;

    //~~~~~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~~~~~~
    public Sudoku(int diff) {
	_board = new int[9][9];
	_difficulty = diff;

    }

    //~~~~~~~~~~~~~~~~~~PLAY METHOD~~~~~~~~~~~~~~~~~~~~~~
    public boolean play() {

	setUp();

	System.out.println(this);

	while (stillRoom()) {
	    round();
	}

	System.out.println("Congrats, you win!");
	return true;
    }


    //~~~~~~~~~~~~~~~~~~ROUND METHOD~~~~~~~~~~~~~~~~~~~~~
    public void round() {
	
	int col = checkCol("In which column is the number you want to fill in?") - 1;
	int row = checkRow("In which row is the number you want to fill in?");
	int val = checkVal("Enter the number you want to fill in here");

	while (ComboBad( col, row, val)) {
	
	    System.out.println( this );
	    col = checkCol("In which column is the number you want to fill in?") -1;
	    row = checkRow("In which row is the number you want to fill in?");
	    val = checkVal("Enter the number you want to fill in here");
	}

	_board[row][col] = val;
	System.out.println(this);
    }


    //~~~~~~~~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~   

    public boolean ComboBad( int col, int row, int val ) {

	// check the row
	for (int n = 0; n < 9; n++ ) {
	    if ( _board[row][n] == val ) {
		System.out.println("Oops, there is already a " + val + " in row " + converter( row ) );
		System.out.println("Try again!");
		pause( 1 );
		return true;
	    }
	}
	
	// check the column
	for (int i = 0; i < 9; i++ ) {
	    if ( _board[i][col] == val ) {
		System.out.println("Oops, there is already a " + val + " in column " + (col + 1) );
		System.out.println("Try again!");
		pause( 1 );
		return true;
	    }
	}

	int firstRow = getFirst( row );
	int firstCol = getFirst( col );

	// check the square
	for (int i = firstRow; i < (firstRow + 3); i++) {
	    for (int n = firstCol; n < (firstCol + 3); n++) {
		if ( _board[i][n] == val ) {
		    System.out.println("Oops, there is already a " + val + " in that square");
		    System.out.println("Try again!");
		    pause( 1 );
		    return true;
		}
	    }
	}

	return false;
    }    

    public int checkVal(String message) {
	int num = -1;
	boolean checkVal = true;

	while (checkVal) {
	    num = getInt( message );
	    if ( num < 1 || num > 9 ) {
		System.out.println("Oops that is not a valid number, enter a number from 1-9");
	    }
	    else {
		return num;
	    }
	}
	return -1;
    }

    public int checkCol( String message ){
	int num = -1;
	boolean getInt = true;

	while (getInt) {
	    num = getInt( message );
	    if ( num < 1 || num > 9 ) {
		System.out.println("Oops that is not a valid column, try again.");
	    }
	    else {
		return num;
	    }
	}
	return -1;
    }

    public int checkRow( String message ) {
	boolean getLetter = true;
	String letter = "x";
	int row;

	while (getLetter) {
	    letter = getString( message );
	    row = converterS( letter );
	    if ( row == -1 ) {
		System.out.println("Oops that is not a valid row, try again.");
	    }
	    else {
		return row;
	    }
	}
	return -1;
    }


    //~~~~~~~~~~~~~~~~~~OTHER METHODS~~~~~~~~~
		 

    public void setUp() {
	if ( _difficulty == 3) {
	    setUpH();
	}
	else if (_difficulty == 2) {
	    setUpM();
	}
	else{
	    setUpE();
	}
    }

    // given a number (can be col or row) returns index of first in "square"

    public int getFirst( int n ) {

	for (int i = n; i > -1; i--) {
	    if ( i % 3 == 0 ) {
		return i;
	    }
	}
	return -1;
    }

    // set up with numbers guaranteed to work
    public void setUpE() {
	
	_board[0][1] = 2;
	_board[0][2] = 3;
	_board[0][3] = 5;
	_board[0][5] = 4;

	_board[1][0] = 5;
	_board[1][2] = 6;
	_board[1][3] = 8;
	_board[1][8] = 2;

	_board[2][0] = 9;
	_board[2][2] = 8;
	_board[2][5] = 3;
	_board[2][7] = 5;
	_board[2][8] = 6;

	_board[3][1] = 9;
	_board[3][2] = 7;
	_board[3][4] = 4;
	_board[3][7] = 6;

	_board[4][3] = 2;
	_board[4][5] = 9;

	_board[5][1] = 5;
	_board[5][4] = 1;
	_board[5][6] = 3;
	_board[5][7] = 9;

	_board[6][0] = 2;
	_board[6][1] = 6;
	_board[6][3] = 1;
	_board[6][6] = 8;
	_board[6][8] = 3;
	
	_board[7][0] = 3;
	_board[7][5] = 6;
	_board[7][6] = 1;
	_board[7][8] = 9;

	_board[8][3] = 9;
	_board[8][5] = 2;
	_board[8][6] = 6;
	_board[8][7] = 7;
    }

    public void setUpM() {

	_board[0][0] = 8;
	_board[0][5] = 5;

	_board[1][0] = 3;
	_board[1][1] = 1;
	_board[1][3] = 2;
	_board[1][6] = 6;
	_board[1][8] = 8;

	_board[2][2] = 2;
	_board[2][5] = 6;

	_board[3][1] = 2;
	_board[3][2] = 8;
	_board[3][3] = 5;
	_board[3][5] = 1;
	_board[3][8] = 6;

	_board[4][1] = 6;
	_board[4][4] = 8;
	_board[4][7] = 3;

	_board[5][0] = 4;
	_board[5][3] = 9;
	_board[5][5] = 2;
	_board[5][6] = 8;
	_board[5][7] = 1;

	_board[6][3] = 6;
	_board[6][6] = 3;

	_board[7][0] = 5;
	_board[7][2] = 7;
	_board[7][5] = 3;
	_board[7][7] = 6;
	_board[7][8] = 9;

	_board[8][4] = 7;
	_board[8][8] = 4;
    }

    public void setUpH() {
	_board[0][2] = 5;
	_board[0][7] = 7;
	_board[0][8] = 9;

	_board[1][1] = 3;
	_board[1][5] = 1;
	_board[1][8] = 8;
	
	_board[2][3] = 7;
	_board[2][4] = 5;

	_board[3][1] = 4;
	_board[3][2] = 2;
	_board[3][5] = 8;
	_board[3][7] = 9;

	_board[4][3] = 9;
	_board[4][5] = 5;

	_board[5][1] = 9;
	_board[5][3] = 1;
	_board[5][6] = 2;
	_board[5][7] = 3;

	_board[6][4] = 3;
	_board[6][5] = 2;

	_board[7][0] = 8;
	_board[7][3] = 6;
	_board[7][7] = 5;

	_board[8][0] = 4;
	_board[8][1] = 1;
	_board[8][6] = 7;
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


    public String toString() {
	String retStr = "\n-----------------------------------------\n";
	retStr += "    ";
	for (int i = 1; i < 10; i ++) {
	    retStr += i + "   ";
	    if (i % 3 == 0) {
		retStr += " ";
	    }
	}

	for (int i = 0; i < 9; i++) {
	    if ( i % 3 == 0) {
		retStr += "\n  ---------------------------------------";
	    }
	    retStr += "\n" + converter(i) +  " ";
	    
	    for (int n = 0; n < 9; n++) {
		retStr += "|";
		if (_board[i][n] == 0) {
		    retStr += "   ";
		}
		else {
		    retStr += " " + _board[i][n] + " ";
		}
		if ( (n + 1) % 3 == 0) {
		    retStr += "|";
		}
	    }
	}
	retStr += "\n  ---------------------------------------";

	return retStr;
    }


		   
    /*
      MODEL SUDOKU BOARD:

      1   2   3    4   5   6    7   8   9
      ---------------------------------------
      a | 1 | 2 | 3 || 1 | 2 | 3 ||___|___|___|
      b | 4 | 5 | 6 || 4 | 5 | 6 ||___|___|___|
      c | 7 | 8 | 9 || 7 | 8 | 9 ||___|___|___|
      ---------------------------------------
      d | 1 | 2 | 3 ||___|___|___||___|___|___|
      e | 4 | 4 | 6 ||___|___|___||___|___|___|
      f | 7 | 5 | 6 ||___|___|___||___|___|___|
      ---------------------------------------
      g |___|___|___||___|___|___||___|___|___|
      h |___|___|___||___|___|___||___|___|___|
      i |___|___|___||___|___|___||___|___|___|
      ---------------------------------------

    */

    public String converter( int i ) {
	if (i == 0) {
	    return "a";
	}
	else if (i == 1) {
	    return "b";
	}
	else if (i == 2) {
	    return "c";
	}
	else if (i == 3) {
	    return "d";
	}
	else if (i == 4) {
	    return "e";
	}
	else if (i == 5) {
	    return "f";
	}
	else if (i == 6) {
	    return "g";
	}	
	else if (i == 7) {
	    return "h";
	}	
	else if (i == 8) {
	    return "i";
	}
	else {
	    return "X";
	}
    }

    public int converterS(String l) {
	if (l.equals("a")) {
	    return 0;
	}
	else if (l.equals("b")) {
	    return 1;
	}
	else if (l.equals("c")) {
	    return 2;
	}
	else if (l.equals("d")) {
	    return 3;
	}
	else if (l.equals("e")) {
	    return 4;
	}
	else if (l.equals("f")) {
	    return 5;
	}
	else if (l.equals("g")) {
	    return 6;
	}	
	else if (l.equals("h")) {
	    return 7;
	}	
	else if (l.equals("i")) {
	    return 8;
	}
	else {
	    return -1;
	}
    }


    public boolean stillRoom() {

	for (int i = 0; i < _board.length; i++ ) {
	    for (int n = 0; n < _board.length; n++) {
		if (_board[i][n] == 0) {
		    return true;
		}
	    }
	}
	return false;
    }

    public static void pause(int seconds){
	Date start = new Date();
	Date end = new Date();
	while(end.getTime() - start.getTime() < seconds * 1000){
	    end = new Date();
	}
    }


    public static void main( String[] args ) {
	Sudoku sudG = new Sudoku(1);
	sudG.play();
    }
}
