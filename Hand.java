// class hand- the collection of cards a player holds
import java.util.*;
import java.io.*;

public class Hand {
        
        
// ~~~~~~~~~~~~~Instance Vars~~~~~~~~~~~~~~

    private int _size;
    
    private ArrayList<Card> _cards;

// ~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~

    public Hand() {

        _size = 0;
        _cards = new ArrayList<Card>();

    }

//~~~~~~~~~~~~~Accessors~~~~~~~~~~~~

    public ArrayList<Card> getCards() {
	return _cards;
    }

// ~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~

    public void add( Card c ) {
	_cards.add(c);
    }

    public void take( Deck mainDeck, int num ) {
    
            for (int n = 0; n < num; n ++) {
                    _cards.add( mainDeck.draw() );
            }
	    _size++;
    }

    public void join( Card c ) {
	_cards.add(c);
    }
    
    public Card draw() {
              
        Card retCard = _cards.get(0);
        _cards.remove(0);
        return retCard;
    }

    public Card draw( int i ) {

        Card retCard = _cards.get( i );
        _cards.remove( i );
        return retCard;
    }

    public boolean has( Card c) {
	for (Card card: _cards) {
	    if (c.equals(card)){
		return true;
	    }
	}
	return false;
    }

    public boolean isPair() {
        boolean retBoo = false;
        for (int i = 0; i < _cards.size()-1; i++) {
            for (int x = (i+1); x < _cards.size(); x++) {
                if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
                    retBoo = true;
		    break;
		}
	    }
	}
	return retBoo;
    }

    public boolean isTwoPair() {
	boolean retBoo = false;

	if (_cards.size() >= 4 ) {
	    ArrayList<Card> temp = new ArrayList<Card>();
	    for (Card c: _cards)
		temp.add(c);

	    if (!(isPair()))
		return retBoo;
	    else {
		for (int i = 0; i < temp.size()-1; i++) {
		    for (int x = (i+1); x < temp.size(); x++) {
			if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			    temp.remove(x);
			    temp.remove(i);
			    for (int p = 0; p < temp.size()-1; p++) {
				for (int q = (p+1); q < temp.size(); q++) {
				    if (_cards.get(p).compareTo(_cards.get(q)) == 0) {
					retBoo = true;
					break;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	return retBoo;
    }

    public boolean isTrip() {
	boolean retBoo = false;

	if (_cards.size() >= 3) {
	    if (!(isPair()))
		return retBoo;
	    else {
		for (int i = 0; i < _cards.size()-2; i++) {
		    for (int x = (i+1); x < _cards.size()-1; x++) {
			if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			    for (int p = (x+1); p < _cards.size(); p++) {
				if (_cards.get(i).compareTo(_cards.get(p)) == 0)
				    retBoo = true;
			    }
			}
		    }
		}
	    }
	}
	return retBoo;
    }

    //~~~~~~~~~~~~~~~~~~Helpers for isStraight?()~~~~~~~~~~~~~~~~~

    public static void insertionSortV( ArrayList<Card> data ) {
	
        for (int i = 1; i < data.size(); i++) {
	    Card val = data.get(i);
	    for (int x = i; x > 0; x--) {
		Card val2 = data.get(x);
		if (val2.compareTo(data.get(x-1)) < 0)
		    data.set( x, data.set( x-1, data.get(x) ) );
		else
		    break;
	    }
	}

    }//end selectionSortV -- O(?)


    // ArrayList-returning insertionSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Card> insertionSort( ArrayList<Card> input ) {

	ArrayList<Card> data = new ArrayList<Card>();
	for (Card a: input)
	    data.add(a);
	insertionSortV(data);
	return data;

    }//end insertionnSort, O(?)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public boolean isStraight() {
	boolean retBoo = false;

	if (_cards.size() >= 5) {
	    ArrayList<Card> temp = insertionSort( _cards );
	    int count = 0;
	    for (int i = 1; i < temp.size(); i++) {
		if (temp.get(i).getValue() == temp.get(i-1).getValue()+1)
		    count++;
		else
		    count = 0;
	    }
	    if (count >= 5)
		retBoo = true;
	}
	return retBoo;
    }

    public boolean isFlush() {
	boolean retBoo = false;
        if (_cards.size() >= 5) {
	    int dcount = 0;
	    int ccount = 0;
	    int hcount = 0;
	    int scount = 0;
	    for (int i = 0; i < _cards.size(); i++) {
		if (_cards.get(i).getSuit().equals("diamonds"))
		    dcount++;
		else if (_cards.get(i).getSuit().equals("clubs"))
		    ccount++;
		if (_cards.get(i).getSuit().equals("hearts"))
		    hcount++;
		if (_cards.get(i).getSuit().equals("spades"))
		    scount++;
	    }
	    if (dcount >= 5 || ccount >= 5 || hcount >= 5 || scount >= 5)
		retBoo = true;
	}
	return retBoo;
    }

    public boolean isHouse() {
	boolean retBoo = false;

	if (isTrip()) {
	    ArrayList<Card> temp = new ArrayList<Card>();
	    for (Card c: _cards)
		temp.add(c);
	    for (int i = 0; i < temp.size()-1; i++) {
		for (int x = (i+1); x < temp.size(); x++) {
		    if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			for (int r = (x+1); r < temp.size(); r++) {
			    if (temp.get(i).compareTo(temp.get(r)) == 0) {
				temp.remove(r);
				temp.remove(x);
				temp.remove(i);
				for (int p = 0; p < temp.size()-2; p++) {
				    for (int q = (p+1); q < temp.size()-1; q++) {
					if (temp.get(p).compareTo(temp.get(q)) == 0) {
					    retBoo = true;
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	return retBoo;
    }

    public boolean isFour() {
	boolean retBoo = false;
	if (isTrip()) {
	    for (int i = 0; i < _cards.size()-3; i++) {
		for (int x = (i+1); x < _cards.size()-2; x++) {
		    if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			for (int p = (x+1); p < _cards.size()-1; p++) {
			    if (_cards.get(i).compareTo(_cards.get(p)) == 0) {
			        for (int q = (p+1); q < _cards.size(); q++) {
				    if (_cards.get(i).compareTo(_cards.get(q)) == 0)
					retBoo = true;
				}
			    }
			}
		    }
		}
	    }
	}
	return retBoo;
    }

    public boolean isStraightFlush() {
	return (isFlush() && isStraight());
    }

}

