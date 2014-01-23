import java.io.*;
import java.util.*;

public class Poker extends MiniGame {

    private final int MAX_ROUNDS = 10;
    private int turnCount, pool;
    private Deck deck;
    private Hand hand, oppHand, table;
    private boolean dealer = false;

    private BufferedReader in;
    private InputStreamReader isr;

    public Poker( int difficulty ) {
        _difficulty = difficulty;
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

    public int bestBet( Hand hand, int diff, int oppWager ) {
	int retInt = handStrength( hand ) * 20 * diff + 10;
	if (oppWager <= retInt) {
	    if (oppWager + 20 >= retInt)
		retInt = oppWager;
	    else
		retInt += oppWager;
	}

	return retInt;
    }

    public int handStrength( Hand hand ) {
        int retInt = 0;
        if (hand.isPair())
         retInt = 1;
        else if (hand.isTwoPair())
         retInt = 2;
        else if (hand.isTrip())
         retInt = 3;
        else if (hand.isStraight())
         retInt = 4;
        else if (hand.isFlush())
         retInt = 5;
        else if (hand.isHouse())
         retInt = 6;
        else if (hand.isFour())
         retInt = 7;
        else if (hand.isStraightFlush())
         retInt = 8;
        else
         retInt = 0;
        return retInt;
    }

    public void newGame() {

        turnCount = 1;
        dealer = true;

        System.out.println("Welcome to Poker: Texas Hold 'Em");

    }

    public void playTurn( Player gambler ) {

        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new Hand();
        Hand oppHand = new Hand();
        Hand table = new Hand();

        hand.take( deck, 1 );
        oppHand.take( deck, 1 );
        hand.take( deck, 1 );
        oppHand.take( deck, 1 );

        String s = "";
        int wager = 0;
        int dec = 0;
        pool = 0;
        boolean fold = false;

        //~~~~~~~~~~~~~~~~~The Mighty FOR Loop~~~~~~~~~~~~~~~~

        for (int x = 0; x < 4; x++) {

         System.out.println("Your cards: ");
         for (int i = 0; i < hand.getCards().size(); i++)
	     System.out.println( hand.getCards().get(i) );
         System.out.println("\n");
         System.out.println("You have " + gambler.getHealth() + " health remaining for gambling.");

         System.out.println("Cards on the table: ");
         if (x == 0)
	     System.out.println("None.");
         else if (x == 1) {
	     table.take( deck, 3 );
	     for (int i = 0; i < table.getCards().size(); i++) {
                 hand.join(table.getCards().get(i));
                 oppHand.join(table.getCards().get(i));
                 System.out.println( table.getCards().get(i) );
	     }

         }
         else if (x == 2) {
	     table.take( deck, 1 );
	     hand.join( table.getCards().get(table.getCards().size()-1) );
	     oppHand.join( table.getCards().get(table.getCards().size()-1) );
	     for (int i = 0; i < table.getCards().size(); i++)
                 System.out.println( table.getCards().get(i) );
         }
         else {
	     table.take( deck, 1 );
	     hand.join( table.getCards().get(table.getCards().size()-1) );
	     oppHand.join( table.getCards().get(table.getCards().size()-1) );
	     for (int i = 0; i < table.getCards().size(); i++)
                 System.out.println( table.getCards().get(i) );
         }
                
         //~~~~~~~~~~~~~~~~~BETTING~~~~~~~~~~~~~~~~~~~~~~~~
         int oppStrength = handStrength( oppHand );
         int oppWager;
         if (dealer) {
        
	     oppWager = bestBet(oppHand, _difficulty, 0);

	     System.out.println("Your opponent has wagered " + oppWager + " dubloons.");

	     s += "\nWhat will you do?\n";
	     s += "1: Fold\n";
	     s += "2: Raise/Call\n";
	     s += "Selection: ";
	     System.out.print( s );

	     try {
                 dec = Integer.parseInt( in.readLine() );
	     }
	     catch ( IOException e ) { }
	     if (dec == 1) {
                 fold = true;
                 break;
	     }
	     else if (dec == 2) {
                 System.out.print("Your wager: ");
                 try {
		     wager = Integer.parseInt( in.readLine() );
                 }
                 catch ( IOException e ) { }
                 if (wager < oppWager) {
		     System.out.println("Bet is too low. Please wager at least as much as your opponent.");
		     try {
                         wager = Integer.parseInt( in.readLine() );
		     }
		     catch ( IOException e ) { }
                 }
                 gambler.addHealth( wager * -1 );
                 if (wager > oppWager) {
		     if (bestBet(oppHand, _difficulty, wager) == wager) {
                         System.out.println("Your opponent has called your raise! Off to the next round!");
                         pool += wager *2;
                        }
		     else if (bestbet(oppHand, _difficulty, wager) > wager) {
			 System.out.println("Your opponent has raised you by " +bestBet(oppHand, _difficulty, wager)-wager);
                        else {
                         System.out.println("Your opponent has folded! You win his bets!");
                         gambler.addHealth( wager );
                         gambler.addHealth( oppWager );
                         fold = true;
                         break;
                        }
                 }
                 else if (wager == oppWager) {
                 System.out.println("Both of ye have the same stacks on the table! Let's get on with it!");
                        pool += wager *2;
                 }
                }
         }

         else {
                System.out.print("Your wager: ");
                try {
                 wager = Integer.parseInt( in.readLine() );
                }
                catch ( IOException e ) { }
                gambler.addHealth( wager * -1 );
                if (oppStrength < 2) {
                 System.out.println("Your opponent has matched ye! Onto the next round.");
                 oppWager = wager;
                 pool += wager *2;
                }
                else if (oppStrength > 1) {
                 oppWager = wager + 30;
                 System.out.println("You have been raised! The bet is " + oppWager + ". Will you bet or fold?");
                 s += "\t1: Fold";
                 s += "\t2: Raise/Call\n";
                 s += "Selection: ";
                 System.out.print( s );
                 try {
                 dec = Integer.parseInt( in.readLine() );
                 }
                 catch ( IOException e ) { }
                 if (dec == 1) {
                        System.out.println("You have lost " + wager + " health.");
                        fold = true;
                        gambler.addHealth( wager * -1 );
                        break;
                 }
                 else if (dec == 2) {
                        System.out.print("Your wager: ");
                        try {
                         wager = Integer.parseInt( in.readLine() );
                        }
                        catch ( IOException e ) { }
                        if (wager < oppWager) {
                         System.out.println("Bet is too low. Please wager at least as much as your opponent.");
                         try {
                                wager = Integer.parseInt( in.readLine() );
                         }
                         catch ( IOException e ) { }
                        }
                        gambler.addHealth( wager * -1 );
                        if (wager > oppWager) {
                         if (oppStrength > 0) {
                                System.out.println("Your opponent matches you! " + wager + " from each player.");
                                oppWager = wager;
                                pool += wager *2;
                         }
                         else {
                                System.out.println("Your opponent has folded! Good betting, young one. Take your earnings.");
                                gambler.addHealth( oppWager );
                                gambler.addHealth( wager );
                                fold = true;
                                break;
                         }
                        }
                        else if (wager == oppWager) {
                         System.out.println("A smart choice in calling! On with the game.");
                         pool += wager *2;
                        }
                 }
                }
         }
        }

        //~~~~~~~~~~~~~~~~COMPARING CARDS~~~~~~~~~~~~~~~~~~~~

        if (!fold) {        
         System.out.println("Opponent's cards: ");
         for (int i = 0; i < oppHand.getCards().size(); i++)
                System.out.println( oppHand.getCards().get(i) );
         int me = handStrength( hand );
         int you = handStrength( oppHand );
         if (me > you) {
                System.out.println("Congratulations! You have won this betting round and " + pool/2 + " health!");
                gambler.addHealth( pool );
                System.out.println("New health: " + gambler.getHealth());
         }
         else if (you > me) {
                System.out.println("Alas! Your opponent has the better hand. You have lost " + pool/2 + " health.");
                System.out.println("New health: " + gambler.getHealth());
         }
         else {
                gambler.addHealth( pool/2 );
                System.out.println("You both win (or lose?). Both hands are the same. Your health remains " + gambler.getHealth());
         }
        }

        dealer = !dealer;

    }

    public boolean play( Player gambler ) {

        boolean retBoo = false;
        double health = gambler.getHealth();
        int count = 0;
        while (gambler.getHealth() > 0 && count < MAX_ROUNDS) {
         playTurn( gambler );
         System.out.print("Do you wish to play another round? You have a maximum of " + (MAX_ROUNDS-count-1) + " rounds left. For grading/boredom purposes. (Yes/No).");
         String s = "";
         try {
                s = in.readLine();
         }
         catch ( IOException e ) { }
         if (s.equals("No"))
                return true;
         count++;
        }

        if (gambler.getHealth() < health)
         return false;
        else
         return true;

    }

    public static void main (String[] args) {

        Player me = new Player();
        Poker a = new Poker( 3 );
        a.play( me );
    
    }

}
