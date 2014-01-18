// class gofish for minigame

import cs1.Keyboard;
import java.util.*;
import java.io.*;

public class GoFish {
  
  
    //~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~
  
    int _difficulty, _roundCnt;
  
    Deck _deck;
    Hand _playerH, _compH; _playerP, _compP;
    // set aside hands for active cards & completed pairs

    Boolean playerT, compT;
    // booleans for whose turn it is

    
  
    //~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~
    public GoFish( int difficulty) {
    
	_difficulty = difficulty;
    
    }
    // to be inplemented later: difficulty translates to # of cards drawn
  
    //~~~~~~~~~~PLAY METHOD~~~~~~~~~~~~
    public void play() {
	begin();
	while ( _deck.getDeck().size() > 0 ) {
	    round();
	}
	System.out.println("Done");
	
    }



    //~~~~~~~~~~SETUP METHOD~~~~~~~~~~~
    public void begin() {
    
	_deck = new Deck();
	_playerH = new Hand();
	_compH = new Hand();
	_playerP = new Hand();
	_compP = new Hand();
    
	_deck.shuffle();
    
	_playerH.take( _deck, 5);
	_compH.take(_deck, 5);

	reportHand();

	checkPairs( _playerH, _playerP, "Player" );
	checkPairs( _compH, _compP, "Computer" );
    }

 

    // ~~~~~~~~~~~METHOD FOR EACH ROUND OF PLAY~~~~~~~
    public void round() {

	if (roundCnt > 0 ) {
	    reportHand();
	}

	// method based on whose turn it is
	if ( personT ) {
	    personGuess();
	}
	else if ( compT ) {
	    
	}
	roundCnt ++;
    
    }
  
    //~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~~~~~

    public boolean personGuess() {

	int choice;
	boolean readGuess = true;


	while (readGuess) {
	    System.out.print(Enter the number of the card you would like to inquire about:);
	    try {
		choice = Keyboard.readInt();
	    }
	    catch( DataException e) {
		System.out.println( "Oops, invalid input, try again." );
	    }
	    if (choice < 1 || choice > _playerH.getCards.size() ) {
		System.out.println( "Oops, you don't have any cards with that corresponding number, try again." );
	    }
	    else {
		readGuess = false;
	    }
	}

	    
	Card cardWanted = _playerH.get( choice );
	int pos = finder( _compH, cardWanted );
    
	if ( pos > -1) {
	    // add new card to player hand
	    _playerH.add( _compH.draw( pos) );
	    System.out.println("Computer: Yes I have that card... *forks it over*" );
	    System.out.println("It's now your turn again!");
	} 
	else {
	    System.out.println("Computer: Go Fish!");

	    Card newCard = _deck.draw();
	    System.out.println( "You drew a " + newCard.getFace() );
	    _playerH.add( newCard );

	    if ( newCard.equals( cardWanted ) ) {
		checkPairs( _playerH, _playerP, "Player" );
		System.out.println( "You asked for that card, so you get to go again!" );
	    }
	    else {
		personT = false;
		computerT = true;
	    }
	}
	
    }
 
 
    public void computerGuess() {
	    
	boolean readAns = true;
	int choice = (int) (math.random() * _compH.size());
	Card choiceC = _compH.get(choice);

	    
	System.out.println("Computer: Do you have any " choiceC.getFace() + "s?");
	reportHand();

	int ans = readAns();
	impletementAns( choice C, ans );
   
	}

	// ~~~~~~~~~~~HELPER METHODS FOR ROUND()~~~~~~~~~
 
    public int readAns() {
	while (readAns ) {
	    System.out.print("Enter 1 if yes, enter 2 if no.");
	    try {
		int ans = Keyboard.readInt();
	    }
	    catch (DataException e) {
		System.out.println( "Oops, invalid input, try again." );
	    }
	    if ( choice != 1 || choice != 2 ) {
		System.out.println( "Oops, your answers must be 1 or 2, try again." );
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
			System.out.println("Now the computer gets to go again.");
			checkPairs( compH, compP, "Computer" );
			_compH.add( _playerH.draw(pos) );
			needsLoop = false;
		    }
		    else {
			System.out.println( "Hey, you are a liar, try again!" );
			ans = readAns();
			break;
		    }
		}
		else if ( ans == 2 ) {
		    if (pos == -1 ) { 
			System.out.println( "Player: Go Fish!" );

			Card newCard = _deck.draw();
			_compH.add( newCard );

			if ( newCard.equals( choiceC ) ) {
			    System.out.println( "The card drawn is what was asked for, so the computer gets to go again." );
			}
			else {
			    personT = true;
			    computerT = false;
		    }
		}
 
		public void reportHand() {
		    System.out.println( "Your hand is...");
    
		    for (int i = 1; i <= _playerH.getCards.getsize(); i++) {
			System.out.println( "Card " + i + "is " _playerH.getHand().get( i ) );
		    }
    
		}
  
		// returns first index where target appears or -1 if not present
		public int finder( ArrayList<Card> al, Card target) {
		    for (int i = 0; i < al.size(); i++) {
			if (al.get(i).equals( target ) ) {
			    return i;
			}
		    }
		    return -1;
		}
 
		// checks for pairs in a hands and moves them to pairs hand 
		public void checkPairs( Hand hand, Hand handP, String name ) {
    
		    ArrayList<Card> handCard = hand.getCards()

			for (int i = 0; i < alC.size(); i++) {
			    Card checker = alC.get(i);

			    for (int n = 0; n < alC.size(); n++ ) {
				if ( n != i && checker.equals( alC.get(n)) ) {

				    Card type = HandCards.get( n );
				    System.out.println( name + " has a pair of " + type.getFace() + "s" );

				    if ( n > i ) {
					HandP.add( hand.draw( n ) );
					HandP.add( hand.draw( i ) );
				    }
				    else {
					HandP.add( hand.draw( i ) );
					HandP.add( hand.draw( n ) );
				    }
				}
			    }
			}
		}
    

    
    
		}
  
	    }
