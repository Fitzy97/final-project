import java.util.*;
import java.io.*;

public class Silo {

    private int _difficulty;

    private BufferedReader in;
    private InputStreamReader isr;

    public Silo (int diff) {
	_difficulty = diff;
	isr = new InputStreamReader(System.in);
	in = new BufferedReader(isr);
	System.out.println("Welcome to Silo!  You have 5 opponents.  This is a simple dice-rolling game.  Roll a 1-2-3 and you automatically lose.  Roll a double and the single die is your score.  A triple is better than any regular (double) score.  Roll a 4-5-6 to automatically win.");
    }
    
    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
         end = new Date();
        }
    }

    public int roll( int a, int b, int c ) {

	int retInt = 0;

	if (a == b && b == c)
	    retInt = (a*100)+(b*10)+c;
	else if ( (a == 1 && b == 2 && c == 3) ||
		  (a == 1 && c == 2 && b == 3) ||
		  (b == 1 && c == 2 && a == 3) ||
		  (b == 1 && a == 2 && c == 3) ||
		  (c == 1 && a == 2 && b == 3) ||
		  (c == 1 && b == 2 && a == 3) )
	    retInt = 123;
	else if ( (a == 4 && b == 5 && c == 6) ||
		  (a == 4 && c == 5 && b == 6) ||
		  (b == 4 && c == 5 && a == 6) ||
		  (b == 4 && a == 5 && c == 6) ||
		  (c == 4 && a == 5 && b == 6) ||
		  (c == 4 && b == 5 && a == 6) )
	    retInt = 456;
	else if ( a == b )
	    retInt = c;
	else if ( a == c )
	    retInt = b;
	else if ( b == a )
	    retInt = c;
	else if ( b == c )
	    retInt = a;
	else if ( c == b )
	    retInt = a;
	else if ( c == a )
	    retInt = b;

	return retInt;
    }

    public boolean playTurn( Player gambler ) {

	ArrayList<Integer> rolls = new ArrayList<Integer>();

	boolean retBoo = false;

	int myRoll = 0;
	int oppRoll = 0;

	int wager = 0;
	String s = "";

	System.out.println("How much would you like this round to cost?");
	try {
	    wager = Integer.parseInt( in.readLine() );
	}
	catch (IOException e) { }
	gambler.addHealth( wager * -1 );
	System.out.println("The pot is " + wager*6);

	while ( myRoll == 0 ) {
	    int a = (int)(Math.random() * 6 + 1);
	    int b = (int)(Math.random() * 6 + 1);
	    int c = (int)(Math.random() * 6 + 1);

	    myRoll = roll(a,b,c);

	    System.out.println("Your roll...");
	    System.out.println(a);
	    System.out.println(b);
	    System.out.println(c);
	    System.out.print("Your score: " + myRoll + "\n");

	    if (myRoll == 0)
		System.out.println("Roll again until you have a score...");
	    else if (myRoll == 456) {
		System.out.println("Congratulations!  You have won silo.  You gain " + (wager*5) + " health.");
		gambler.addHealth(wager*6);
		return true;
	    }
	    else if (myRoll == 123) {
		System.out.println("Automatic loss!  You lose " + wager + " health.");
		gambler.addHealth(wager * -1);
		return false;
	    }
	}

	int i = 0;
	while ( i < 5 ) {
	    
	    oppRoll = 0;
	    while (oppRoll == 0) {
		int a = (int)(Math.random() * 6 + 1);
		int b = (int)(Math.random() * 6 + 1);
		int c = (int)(Math.random() * 6 + 1);

		oppRoll = roll(a,b,c);

		System.out.println("Opponent " + (i+1) + "'s roll...");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.print("Opponent " + (i+1) + "'s score: " + oppRoll + "\n");

		if (oppRoll == 0)
		    System.out.println("He must roll again...");
		else if (oppRoll == 456) {
		    System.out.println("Opponent " + (i+1) + " automatically wins.  You lose " + wager + " health.");
		    return false;
		}
		else if (oppRoll == 123)
		    System.out.println("Opponent " + (i+1) + " automatically loses.");
	    }

	    rolls.add(oppRoll);
	    i++;
	}

	System.out.println("Your score: " + myRoll);
	for (int z = 0; z < rolls.size(); z++) {
	    System.out.print("Opponent " + (z+1) + "'s score: ");
	    System.out.println( rolls.get(z) );
	}
	for (int v = 0; v < rolls.size(); v++) {
	    for (int x = v; x > 0; x--) {
		int b = rolls.get(x);
		if (b < rolls.get(x-1))
		    rolls.set( x, rolls.set( x-1, rolls.get(x) ) );
		else
		    break;
	    }
	}
	if (myRoll > rolls.get( rolls.size()-1 )) {
	    System.out.println("Congratulations!  You have won silo and " + (wager*5) + " health.");
	    gambler.addHealth(wager*5);
	    return true;
	}
	else if (myRoll == rolls.get( rolls.size()-1 )) {
	    System.out.println("There is a tie!  The answer is one-on-one death roll.");
	    myRoll = 0;
	    while ( myRoll == 0 ) {
		int a = (int)(Math.random() * 6 + 1);
		int b = (int)(Math.random() * 6 + 1);
		int c = (int)(Math.random() * 6 + 1);

		myRoll = roll(a,b,c);

		System.out.println("Your roll...");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.print("Your score: " + myRoll + "\n");

		if (myRoll == 0)
		    System.out.println("Roll again until you have a score...");
		else if (myRoll == 456) {
		    System.out.println("Congratulations!  You have won silo.  You gain " + (wager*5) + " health.");
		    gambler.addHealth(wager*6);
		    return true;
		}
		else if (myRoll == 123) {
		    System.out.println("Automatic loss!  You lose " + wager + " health.");
		    gambler.addHealth(wager * -1);
		    return false;
		}
	    }

	    oppRoll = 0;
	    while (oppRoll == 0) {
		int a = (int)(Math.random() * 6 + 1);
		int b = (int)(Math.random() * 6 + 1);
		int c = (int)(Math.random() * 6 + 1);

		oppRoll = roll(a,b,c);

		System.out.println("Opponent's roll...");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.print("Opponent's score: " + oppRoll + "\n");

		if (oppRoll == 0)
		    System.out.println("He must roll again...");
		else if (oppRoll == 456) {
		    System.out.println("Opponent " + (i+1) + " automatically wins.  You lose " + wager + " health.");
		    return false;
		}
		else if (oppRoll == 123)
		    System.out.println("Opponent " + (i+1) + " automatically loses.");
	    }
	    if (myRoll > oppRoll) {
		System.out.println("You have won the death roll!  Your earnings are " + (wager*6) + " health.");
		gambler.addHealth(wager*6);
		return true;
	    }
	    else {
		System.out.println("You lost the death roll and " + wager + " health.");
		gambler.addHealth(wager*-1);
	    }
	}
	else {
	    gambler.addHealth( wager*-1 );
	    System.out.println("Unfortunately you have lost this round.");
	}

	return retBoo;
    }

    public boolean play( Player gambler ) {

        return playTurn(gambler);
    }

    public static void main(String[] args) {

	Player me = new Player();
	Silo s = new Silo(3);

	s.play( me );

    }
}
