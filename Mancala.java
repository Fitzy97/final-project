import java.util.*;
import java.io.*;

public class Mancala {

    private int _difficulty;
    private ArrayList<Integer> board = new ArrayList<Integer>();
    private BufferedReader in;
    private InputStreamReader isr;

    public Mancala( int diff ) {
	_difficulty = diff;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	newGame();
    }
    
    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
            end = new Date();
        }
    }

    public void newGame() {
	for(int i = 0; i < 6; i++)
	    board.add(4);
	board.add(0);
	for (int i = 0; i < 6; i++)
	    board.add(4);
	board.add(0);
	System.out.println("Welcome to Mancala!  The board is made up of two rows of 6 holes, as well as two 'stores' on either side of the rows.  You store is to your right, and your goal is to get more stones into your store than your opponent.  To start out, each hole has four stones.  Select a pit on your side of the board to move the stones counter-clockwise, dropping one stone in each pit.  If you pass over your store, you place one stone into it.  If the final stone lands in an empty hole on your side across from a hole with stones, you get those stones.  If the final stone lands in your store, you get a free turn.  The game ends when all six holes on one side are cleared, then each player puts all the stones on his side into his store.");
    }

    public boolean isFinished() {
	boolean retBoo = false;
	for (int i = 0; i < 6; i++) {
	    if (board.get(i) > 0)
		break;
	    else if (i == 5)
		retBoo = true;
	}
	for (int x = 7; x < 13; x++) {
	    if (board.get(x) > 0)
		break;
	    else if (x == 12)
		retBoo = true;
	}
	return retBoo;
    }

    public String printBoard() {
	String retStr = "| ";
	retStr += board.get(13);
	if (board.get(13) < 10)
	    retStr += " | ";
	else 
	    retStr += "| ";
	for (int i = 12; i > 6; i--) {
	    retStr += board.get(i);
	    if (board.get(i) < 10)
		retStr += " | ";
	    else
		retStr += "| ";
	}
	retStr += "  |";
	retStr += "\n|   |-----------------------|   |\n|   | ";
	for (int i = 0; i < 6; i++) {
	    retStr += board.get(i);
	    if (board.get(i) < 10)
		retStr += " | ";
	    else 
		retStr += "| ";
	}
	retStr += board.get(6);
	if (board.get(6) < 10)
	    retStr += " |";
	else
	    retStr += "|";
	return retStr;
    }
    

    public boolean move( int hole ) {

	boolean retBoo = false;

	int prev = board.get( hole ); //4
	int end = hole + prev; //11

	board.set( hole, 0 );
	if ( end > board.size()-1 ) {
	    end -= 14;
	    for (int i = hole+1; i < 14; i++) {
		board.set( i, board.get(i)+1 );
		prev--;
	    }

	    for (int i = 0; i < prev; i++) {

		board.set( i, board.get(i)+1 );
		if (i == prev-1 && (end == 6 || end == 13))
		    retBoo = true;
		else if ( (i == prev-1) && (board.get(end) == 0) && (end < 6) )
		    board.set( 6, (board.get(6) + board.get(end+(12-end*2))) );
		else if ( (i == prev-1) && (board.get(end) == 0) && (end > 6) && (end < 13) )
		    board.set( 13, (board.get(13) + board.get(end-(12-(12-end)*2))) );

	    }
	}
	else {
	    for (int i = 0; i < prev; i++) {

		board.set( hole+i+1, board.get(hole+i+1)+1 );
		if (i == prev-1 && (end == 6 || end == 13))
		    retBoo = true;
		else if ( (i == prev-1) && (board.get(end) == 0) && (end < 6) )
		    board.set( 6, (board.get(6) + board.get(end+(12-end*2))) );
		else if ( (i == prev-1) && (board.get(end) == 0) && (end > 6) && (end < 13) )
		    board.set( 13, (board.get(13) + board.get(end-(12-(12-end)*2))) );

	    }
	}
	return retBoo;
    }

    public boolean playTurn() {

	boolean retBoo = false;
	boolean repeat = true;
	boolean oppRepeat = true;
	int myStore = 0;
	int oppStore = 0;
	int choice = 0;

	while (!isFinished()) {
	    repeat = true;
	    oppRepeat = true;

	    while (repeat) {
		System.out.println("The board: ");
		System.out.println( printBoard() );

		System.out.print("Which hole do you wish to move?  From left to right choose the corresponding hole's number (1-6): ");
		try {
		    choice = Integer.parseInt( in.readLine() );
		}
		catch (IOException e) { }
		choice--;
		repeat = move(choice);
	    }

	    while (oppRepeat) {
		System.out.println("The board: ");
		System.out.println( printBoard() );

		System.out.print("Your opponent is thinking...");
		choice = (int)(Math.random()*6+7);
		System.out.println("Choice: " + choice);
		oppRepeat = move(choice);
	    }

	}

	System.out.println("The final board: ");
	System.out.println( printBoard() );

	int myScore = 0;
	int oppScore = 0;
	for (int p = 0; p < 6; p++)
	    myScore += board.get(p);
	for (int q = 12; q > 6; q--)
	    oppScore += board.get(q);
	if (myScore > oppScore) {
	    System.out.println("Congratulations! You have beaten Mancala.");
	    retBoo = true;
	}
	else if (myScore == oppScore)
	    System.out.println("It is a tie!  You must win to advance...");
	else 
	    System.out.println("Your skills have been bested!");
    
	return retBoo;
    }

    public boolean play() {

	return playTurn();
   
    }

    public static void main (String[] args) {

	Mancala a = new Mancala(3);
	a.play();

    }
}
