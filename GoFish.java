// class gofish for minigame

import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class GoFish extends MiniGame{
  
  
    //~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~
  
    int _roundCnt;
  
    Deck _deck;
    Hand _playerH, _compH, _playerP, _compP;
    // set aside hands for active cards & completed pairs

    Boolean _playerT, _compT;
    // booleans for whose turn it is

    
  
    //~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~
    public GoFish( int difficulty) {
    
	_difficulty = difficulty;

    	_deck = new Deck();
	_playerH = new Hand();
	_compH = new Hand();
	_playerP = new Hand();
	_compP = new Hand();

	_playerT = true;
	_compT = false;
    }
    // to be inplemented later: difficulty translates to # of cards drawn
  
    //~~~~~~~~~~PLAY METHOD~~~~~~~~~~~~
    public boolean play( Player player) {

	System.out.println("Welcome to go fish!");
	pause(2);

	begin();
	while ( gameGo() ) {
	    round();
	}

	return findWinner();
	
    }



    //~~~~~~~~~~SETUP METHOD~~~~~~~~~~~
    public void begin() {
    
    
	_deck.shuffle();
    
	int num = _difficulty * 2;

	_playerH.take( _deck, (3 + num));
	_compH.take(_deck, 5);

	reportHand();

	checkPairs( _playerH, _playerP, "Player" );
	checkPairs( _compH, _compP, "Computer" );
    }

 

    // ~~~~~~~~~~~METHOD FOR EACH ROUND OF PLAY~~~~~~~
    public void round() {

	pause(3);

	// method based on whose turn it is
	if ( _playerT ) {
	    personGuess();
	}
	else if ( _compT ) {
	    computerGuess();
	    
	}
	_roundCnt ++;
    
    }
  
    //~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~~~~~

    public void personGuess() {

	System.out.println("\n------------------------------");
	System.out.println("PLAYER'S TURN");
	System.out.println("------------------------------");

	int choice;
	boolean readGuess = true;

	reportHand();
	choice = getInt( "Enter the number of the card you would like to inquire about" );

	while (readGuess) {
	    if (choice < 1 || choice > _playerH.getCards().size() ) {
		System.out.println( "Oops, you don't have any cards with that corresponding number, try again." );
		choice = getInt( "Enter the number of the card you would like to inquire about" );
	    }
	    else {
		readGuess = false;
	    }
	}

	choice --;
	Card cardWanted = _playerH.getCards().get( choice );
	System.out.println("Player: Do you have any " + cardWanted.getFace() + "s?");
	pause(1);

	int pos = finder( _compH, cardWanted );
    
	if ( pos > -1) {
	    // add new card to player hand
	    _playerH.add( _compH.draw( pos) );
	    System.out.println("Computer: Yes... *forks it over*" );
	    pause(2);

	    checkPairs( _playerH, _playerP, "Player" );

	    System.out.println("It's now your turn again!");
	} 
	else {
	    System.out.println("Computer: Go Fish!");

	    Card newCard = _deck.draw();
	    System.out.println( "You drew a " + newCard.getFace() );
	    _playerH.add( newCard );
	    checkPairs( _playerH, _playerP, "Player" );

	    if ( newCard.equals( cardWanted ) ) {
		System.out.println( "You asked for that card, so you get to go again!" );
	    }
	    else {
		_playerT = false;
		_compT = true;
	    }
	}
    }
 
 
    public void computerGuess() {

	System.out.println("\n------------------------------");
	System.out.println("COMPUTER'S TURN");
	System.out.println("------------------------------");	    

	int choice = (int) (Math.random() * _compH.getCards().size());
	Card choiceC = _compH.getCards().get(choice);

	    
	System.out.println("Computer: Do you have any " + choiceC.getFace() + "s?");
	reportHand();

	int ans = readAns();
	implementAns( choiceC, ans );
   
    }

    // ~~~~~~~~~~~HELPER METHODS FOR ROUND()~~~~~~~~~
 
    public int readAns() {
	boolean readAns = true;
	int ans = getInt( "Enter 1 if yes, enter 2 if no." );

	while (readAns ) {
	    if ( ans != 1 && ans != 2 ) {
		System.out.println( "Oops, your answers must be 1 or 2, try again." );
		ans = getInt( "Enter 1 if yes, enter 2 if no." );
	    }
	    else {
		readAns = false;
	    }
	}
	return ans;
    }


    // read player's confirmation about whether or not they have 
    public void implementAns( Card choiceC, int ans ) {
  
	int pos = finder( _playerH, choiceC );
	boolean needsLoop = true;

	while ( needsLoop ) {
	    if (ans == 1) {
		if (pos > -1) {
		    
		    _compH.add( _playerH.draw(pos) );
		    checkPairs( _compH, _compP, "Computer" );
		    System.out.println("Now the computer gets to go again.");
		    needsLoop = false;
		}
		else {
		    System.out.println( "Hey, you are a liar, try again!" );
		    ans = readAns();
		}
	    }
	    else if ( ans == 2 ) {
		if (pos == -1 ) { 
		    needsLoop = false;
		    System.out.println( "Player: Go Fish!" );

		    Card newCard = _deck.draw();
		    _compH.add( newCard );
		    checkPairs( _compH, _compP, "Computer" );

		    if ( newCard.equals( choiceC ) ) {
			System.out.println( "The card drawn is what was asked for, so the computer gets to go again." );
		    }
		    else {
			_playerT = true;
			_compT = false;
		    }
		}
		else {
		    System.out.println( "Hey, you are a liar, try again!" );
		    ans = readAns();
		}
	    }
	}
    }
 
    public void reportHand() {
	System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
	System.out.println( "Your hand is...");
	
	ArrayList<Card> cards = _playerH.getCards();
    
	for (int i = 0; i < cards.size(); i++) {
	    System.out.println( "Card " + (i + 1) + " is the " + cards.get( i ) );
	}
	System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
	pause(1);
    
    }
  
    // returns first index where target appears or -1 if not present
    public int finder( Hand hand, Card target) {

	ArrayList<Card> al = hand.getCards();

	for (int i = 0; i < al.size(); i++) {
	    if (al.get(i).equals( target ) ) {
		return i;
	    }
	}
	return -1;
    }
 
    // checks for pairs in a hands and moves them to pairs hand 
    public void checkPairs( Hand hand, Hand handP, String name ) {
    
	ArrayList<Card> alC = hand.getCards();

	for (int i = 0; i < alC.size(); i++) {
	    Card checker = alC.get(i);

	    for (int n = 0; n < alC.size(); n++ ) {
		if ( n != i && checker.equals( alC.get(n)) ) {

		    Card type = alC.get( n );
		    System.out.println( name + " has a pair of " + type.getFace() + "s" );

		    if ( n > i ) {
			handP.add( hand.draw( n ) );
			handP.add( hand.draw( i ) );
		    }
		    else {
			handP.add( hand.draw( i ) );
			handP.add( hand.draw( n ) );
		    }
 		    if (name.equals("Player")) {
			reportHand();
		    }
		}
	    }
	}
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

    public boolean findWinner() {
	if (_playerP.getCards().size() > _compP.getCards().size()) {
	    System.out.println("The player has won!");
	    return true;
	}
	else if (_playerP.getCards().size() < _compP.getCards().size()) {
	    System.out.println("The computer has won!");
	    return false;
	}
	else {
	    System.out.println("This is a tie!");
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

    public boolean gameGo() {
	return (!( _deck.getDeck().size() < 1 || _playerH.getCards().size() < 1 || _compH.getCards().size() < 1 ));
    }

    public static void main( String[] args ) {
	GoFish gofish = new GoFish(1);
	Player playa = new Player();
	gofish.play(playa);
    }

}
  

