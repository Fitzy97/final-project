import java.io.*
import java.util.*

public class Poker {

    System.out.println("Welcome to Poker: Texas Hold 'Em");
    private final int MAX_ROUNDS = 5;
    private int _difficulty, turnCount, pool;
    private Deck deck;
    private Hand hand, oppHand, table;
    private boolean dealer;

    public Poker( difficulty ) {
	_difficulty = difficulty;
    }

    public void newGame() {

	deck = new Deck();
	deck.shuffle();

	hand = new Hand();
	oppHand = new Hand();
	table = new Hand()

	hand.take( deck, 1 );
	oppHand.take( deck, 1 );
	hand.take( deck, 1 );
	oppHand.take( deck, 1 );

	turnCount = 1;
	pool = 0;
	dealer = true;

    }

    public boolean playTurn( Player gambler ) {

	String s;
	int wager, dec;

	System.out.println("Your cards: ");
	for (int i = 0; i < hand.size(); i++)
	    System.out.println( hand.getCards().get(i) );
	System.out.println("You have " + gambler.getHealth() + " health remaining for gambling.");

	System.out.println("Cards on the table: ");
	if (turnCount == 1)
	    System.out.println("None.");
	else if (turnCount == 2) {
	    table.take( deck, 3 );
	    for (int i = 0; i < table.size(); i++)
		System.out.println( table.getCards().get(i) );
	}
        else if (turnCount >= 3) {
	    table.take( deck, 1 );
	    for (int i = 0; i < table.size(); i++)
		System.out.println( table.getCards().get(i) );
	}

	s += "\nWhat will you do?\n";
	s += "\t1: Fold";
	s += "\t2: Raise/Call/Bet\n";
	s += "Selection: ";
	System.out.print( s );

	try {
	    dec = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }
	if (dec == 1)
	    break;
	else {
	    System.out.print("Your wager: ");
	    try {
		wager = Integer.parseInt( in.readLine() );
	    }
	    catch ( IOException e ) { }
	}
	
        

    }

    public boolean play( Player gambler ) {



    }

}


