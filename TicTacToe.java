import java.util.*;
import java.io.*;
import cs1.Keyboard;

public class TicTacToe {

    private int _difficulty, turnCount;
    private BufferedReader in;
    private InputStreamReader isr;
    private ArrayList<String> board = new ArrayList<String>();

    public TicTacToe( int diff ) {
	for (int i = 0; i < 9; i++)
	    board.add("   ");
	_difficulty = diff;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
    }

    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
	    end = new Date();
        }
    }

    public int twoInARow( String mark ) {

	int retInt = (int)(Math.random() * 9);
	for (int i = 0; i < board.size(); i++) {
	    if (board.get(i).equals(" " + mark + " ")) {
		if ( (!(i == 2 || i == 5 || i == 8) && board.get(i).equals(board.get(i+1))) ) {
		    if (i == 0 || i == 3 || i == 6) {
			retInt = i+2;
			break;
		    }
		    else {
			retInt = i-1;
			break;
		    }
		}
		else if ((i == 0 || i == 3 || i == 6) && board.get(i+2).equals(board.get(i))) {
		    retInt = i + 1;
		    break;
		}
		else if (i < 6 && board.get(i).equals(board.get(i+3))) {
		    if (i < 3) {
			if (board.get(i).equals(board.get(i+6))) {
			    retInt = i + 3;
			    break;
			}
			else {
			    retInt = i + 6;
			    break;
			}
		    }
		    else {
			retInt = i - 3;
			break;
		    }
		}
		else if (i < 3 && board.get(i).equals(board.get(i+6))) {
		    retInt = i + 3;
		    break;
		}
		else if (i > 5 && board.get(i).equals(board.get(i-3))){
		    retInt = i - 6;
		    break;
		}
		else if (i == 4) {
		    if (board.get(i).equals(board.get(i+4))) {
			retInt = i - 4;
			break;
		    }
		    else if (board.get(i).equals(board.get(i-4))) {
			retInt = i + 4;
			break;
		    }
		    else if (board.get(i).equals(board.get(i-2))) {
			retInt = i + 2;
			break;
		    }
		    else if (board.get(i).equals(board.get(i+2))) {
			retInt = i - 2;
			break;
		    }
		}
		else if (i == 0 && board.get(i).equals(board.get(8))) {
		    retInt = 4;
		    break;
		}
		else if (i == 2 && board.get(i).equals(board.get(6))) {
		    retInt = 4;
		    break;
		}
	    }
	}
		
	System.out.println("RETINT: " + retInt);
	return retInt;
    }

    public boolean isThreeInARow( String mark ) {

	boolean retBoo = false;

	if ( (board.get(0).equals(board.get(1)) && 
	      board.get(0).equals(board.get(2)) &&
	      board.get(0).equals(" " + mark + " "))
	     ||
	     (board.get(3).equals(board.get(4)) &&
	      board.get(3).equals(board.get(5)) &&
	      board.get(3).equals(" " + mark + " "))
	     ||
	     (board.get(6).equals(board.get(7)) &&
	      board.get(6).equals(board.get(8)) &&
	      board.get(6).equals(" " + mark + " "))
	     ||
	     (board.get(1).equals(board.get(4)) &&
	      board.get(1).equals(board.get(7)) &&
	      board.get(1).equals(" " + mark + " "))
	     ||
	     (board.get(0).equals(board.get(3)) &&
	      board.get(0).equals(board.get(6)) &&
	      board.get(0).equals(" " + mark + " "))
	     ||
	     (board.get(2).equals(board.get(5)) &&
	      board.get(2).equals(board.get(8)) &&
	      board.get(2).equals(" " + mark + " "))
	     ||
	     (board.get(0).equals(board.get(4)) &&
	      board.get(0).equals(board.get(8)) &&
	      board.get(0).equals(" " + mark + " "))
	     ||
	     (board.get(2).equals(board.get(4)) &&
	      board.get(2).equals(board.get(6)) &&
	      board.get(2).equals(" " + mark + " ")))
	    retBoo = true;
	return retBoo;
    }

    public boolean playTurn() {

	String s = "";
	String mark = "";
	String oppMark = "";
	int choice = 0;
	boolean first = false;
	boolean retBoo = false;
	int moveCount = 0;
	ArrayList<Integer> taken = new ArrayList<Integer>();

	System.out.println("Welcome to Tic-Tac-Toe. Here is the board:");
        for (int i = 0; i < 9; i++) {
	    s += board.get(i);
	    if (i == 2 || i == 5)
		s += "\n-----------\n";
	    else if (!(i == 8))
		s+= "|";
	}
	System.out.println(s);
	pause(1);
	System.out.println("Pick your mark: X or O");
	System.out.println("1: X\n2: O");
	System.out.print("Your choice: ");
	try {
	    choice = Keyboard.readInt();
	}
	catch (  InputMismatchException e  ) { 
	    choice = 1;
	}
	if (choice == 1) {
	    mark = "X";
	    oppMark = "O";
	}
	else {
	    mark = "O";
	    oppMark = "X";
	}

	pause(1);
	System.out.println("Would you like to go first?");
	System.out.println("1: Yes\n2: No");
	System.out.print("Your choice: ");
	try {
	    choice = Keyboard.readInt();
	}
	catch (  InputMismatchException e  ) {
	    choice = 1;
	}
	if (choice == 1) {
	    while (moveCount < 6) {

		System.out.println("Where do you wish to go?");
		s = "1: Upper Left\n" +
		    "2: Upper Center\n" +
		    "3: Upper Right\n" +
		    "4: Middle Left\n" +
		    "5: Middle Center\n" +
		    "6: Middle Right\n" +
		    "7: Lower Left\n" +
		    "8: Lower Center\n" +
		    "9: Lower Right";
		System.out.println(s);
		System.out.print("Your choice: ");
		try {
		    choice = Keyboard.readInt();
		}
		catch ( InputMismatchException e ) {
		}
		for (int i = 0; i < taken.size(); i++) {
		    if (taken.get(i) == choice) {
			System.out.println("Please select an open spot on the board.");
			s = "1: Upper Left\n" +
			    "2: Upper Center\n" +
			    "3: Upper Right\n" +
			    "4: Middle Left\n" +
			    "5: Middle Center\n" +
			    "6: Middle Right\n" +
			    "7: Lower Left\n" +
			    "8: Lower Center\n" +
			    "9: Lower Right";
			System.out.println(s);
			System.out.print("Your choice: ");
			try {
			    choice = Integer.parseInt( in.readLine() );
			}
			catch (IOException e) { }
		    }
		}
		taken.add(choice);
		board.remove(choice-1);
		board.add(choice-1, " " + mark + " ");
		pause(1);
	        System.out.println("The Board: ");
		s = "";
		for (int i = 0; i < 9; i++) {
		    s += board.get(i);
		    if (i == 2 || i == 5)
			s += "\n-----------\n";
		    else if (!(i == 8))
			s+= "|";
		}
		System.out.println(s);

		if (moveCount < 4) {
		    System.out.println("Your opponent is thinking...");
		    pause(2);
		    boolean b = true;
		    while (b) {
			if (_difficulty == 1)
			    choice = (int)(Math.random() * 9);
			else if (_difficulty == 3)
			    choice = twoInARow( mark );
			else
			    choice = twoInARow( oppMark );
			boolean d = false;
			for (int i = 0; i < taken.size(); i++) {
			    if (taken.get(i) == choice+1)
				d = true;
			}
			b = d;
		    }
		    taken.add(choice);
		    board.remove(choice);
		    board.add(choice, " " + oppMark + " ");

		    pause(1);
		    System.out.println("The board: ");
		    s = "";
		    for (int i = 0; i < 9; i++) {
			s += board.get(i);
			if (i == 2 || i == 5)
			    s += "\n-----------\n";
			else if (!(i == 8))
			    s+= "|";
		    }
		    System.out.println(s);
		}
		moveCount++;

		if ( isThreeInARow( mark ) ) {
		    System.out.println("Congratulations! You have completed Tic-Tac-Toe.");
		    retBoo = true;
		    break;
		}
		else if ( isThreeInARow( oppMark ) || moveCount == 5 ) {
		    System.out.println("You have failed!");
		    return false;
		}
	    }
	}

	else if (choice == 2) {
	    while (moveCount < 6) {

		System.out.println("Your opponent is thinking...");
		pause(2);
		boolean b = true;
		while (b) {
		    if (_difficulty == 1)
			choice = (int)(Math.random() * 9);
		    else if (_difficulty == 3)
			choice = twoInARow( mark );
		    else
			choice = twoInARow( oppMark );
		    boolean d = false;
		    for (int i = 0; i < taken.size(); i++) {
			if (choice+1 == taken.get(i))
			    d = true;
		    }
		    b = d;
		}
		taken.add(choice);
		board.remove(choice);
		board.add(choice, " " + oppMark + " ");

		pause(1);
		System.out.println("The Board: ");
		s = "";
		for (int i = 0; i < 9; i++) {
		    s += board.get(i);
		    if (i == 2 || i == 5)
			s += "\n-----------\n";
		    else if (!(i == 8))
			s+= "|";
		}
		System.out.println(s);
		
		if (moveCount < 4) {
		    System.out.println("Where do you wish to go?");
		    s = "1: Upper Left\n" +
			"2: Upper Center\n" +
			"3: Upper Right\n" +
			"4: Middle Left\n" +
			"5: Middle Center\n" +
			"6: Middle Right\n" +
			"7: Lower Left\n" +
			"8: Lower Center\n" +
			"9: Lower Right";
		    System.out.println(s);
		    System.out.print("Your choice: ");
		    try {
			choice = Keyboard.readInt();
		    }
		    catch ( InputMismatchException e ) { 
			choice = 8;
		    }
		    for (int i = 0; i < taken.size(); i++) {
			if (taken.get(i) == choice) {
			    System.out.println("Please select an open spot");
			    s = "1: Upper Left\n" +
				"2: Upper Center\n" +
				"3: Upper Right\n" +
				"4: Middle Left\n" +
				"5: Middle Center\n" +
				"6: Middle Right\n" +
				"7: Lower Left\n" +
				"8: Lower Center\n" +
				"9: Lower Right";
			    System.out.println(s);
			    System.out.print("Your choice: ");
			    try {
				choice = Keyboard.readInt();
			    }
			    catch (InputMismatchException e) { }
			}
		    }
		    taken.add(choice);
		    board.remove(choice-1);
		    board.add(choice-1, " " + mark + " ");
		    pause(1);
		    System.out.println("The board: ");
		    s = "";
		    for (int i = 0; i < 9; i++) {
			s += board.get(i);
			if (i == 2 || i == 5)
			    s += "\n-----------\n";
			else if (!(i == 8))
			    s+= "|";
		    }
		    System.out.println(s);
		}
		moveCount++;

		if ( isThreeInARow( mark ) ) {
		    System.out.println("Congratulations! You have completed Tic-Tac-Toe.");
		    retBoo = true;
		    break;
		}
		else if (isThreeInARow( oppMark ) || moveCount == 5) {
		    System.out.println("You have failed!");
		    return false;
		}
	    }
	}
	return retBoo;
    }

    public boolean play() {

        return playTurn();

    }

    public static void main(String[] args) {
	TicTacToe joe = new TicTacToe(3);
	joe.play();
    }
}
