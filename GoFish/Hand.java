// class hand- the collection of cards a player holds

import java.util.ArrayList;

public class Hand {
	
	
// ~~~~~~~~~~~~~Instance Vars~~~~~~~~~~~~~~

    private int _size;
    
    private ArrayList<Card> _cards;

// ~~~~~~~~~~~~~Constructor~~~~~~~~~~~~~~

    public Hand() {

	_size = 0;
	_cards = new ArrayList<Card>();

    }

//~~~~~~~~~~~~~~~Accessors~~~~~~~~~~~

    public ArrayList<Card> getCards() {
	return _cards;
    }


    public int getSize() {
	return _size;
    }

// ~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~

    public void take( Deck mainDeck, int num ) {
    
    	for (int n = 0; n < num; n ++) {
    		_cards.add( mainDeck.draw() );
    	}
    }
    
    
    public Card draw(int index) {
              
    	Card retCard = _cards.get(index);
        _cards.remove(index);
        return retCard;
    }
    
    public boolean isPair?() {
	boolean retBoo = false;
	for (int i = 0; i < _cards.size(); i++) {
	    for (int x = 0; x < _cards.size(); x++) {
		if (!(_cards.get(i).equals(_cards.get(x))) && 
		    _cards.get(i).compareTo(_cards.get(x)) == 0)
		    retBoo = true;
	    }
	}
	return retBoo;
    }
    
    public boolean isTwoPair?() {
	boolean retBoo = false;

	if (_cards.size() >= 4 ) {
	    ArrayList<Card> temp = new ArrayList<Card>();
	    for (Card c: _cards)
		temp.add(c);

	    if (!(isPair?()))
		return retBoo;
	    else {
		for (int i = 0; i < temp.size()-1; i++) {
		    for (int x = (i+1); x < temp.size(); x++) {
			if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			    temp.remove(i);
			    if (i < x) 
				temp.remove(x-1);
			    else
				temp.remove(x);
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

    public boolean isTrip?() {
	boolean retBoo = false;

	if (_cards.size() >= 3) {
	    if (!(isPair?()))
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

    public static void insertionSortV( ArrayList<Comparable> data ) {
	
        for (int i = 1; i < data.size(); i++) {
	    Comparable val = data.get(i);
	    for (int x = 0; x < data.size(); x++) {
		Comparable val2 = data.get(x);
		if (val.compareTo(val2) < 0) {
		    data.add(x, val);
		    data.remove(i+1);
		}
	    }
	}

    }//end insertionSortV -- O(?)


    // ArrayList-returning insertionSort
    // postcondition: order of input ArrayList's elements unchanged
    //                Returns sorted copy of input ArrayList.
    public static ArrayList<Comparable> insertionSort( ArrayList<Comparable> input ) {

	ArrayList<Comparable> data = new ArrayList<Comparable>();
	for (Comparable a: input)
	    data.add(a);
	insertionSortV(data);
	return data;

    }//end insertionnSort, O(?)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public boolean isStraight?() {
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

    public boolean isFlush?() {
	boolean retBoo = false;
        if (_cards.size() >= 5) {
	    int count = 0;
	    for (int i = 1; i < temp.size(); i++) {
		if (temp.get(i).getSuit().equals(temp.get(i-1).getSuit()))
		    count++;
	    }
	    if (count >= 5)
		retBoo = true;
	}
	return retBoo;
    }

    public boolean isHouse?() {
	boolean retBoo = false;

	if (isPair?()) {
	    ArrayList<Card> temp = new ArrayList<Card>();
	    for (Card c: _cards)
		temp.add(c);
	    for (int i = 0; i < temp.size()-1; i++) {
		for (int x = (i+1); x < temp.size(); x++) {
		    if (_cards.get(i).compareTo(_cards.get(x)) == 0) {
			temp.remove(i);
		        if (i < x) 
			    temp.remove(x-1);
			else
			    temp.remove(x);
			for (int i = 0; i < temp.size()-2; i++) {
			    for (int x = (i+1); x < temp.size()-1; x++) {
				if (temp.get(i).compareTo(temp.get(x)) == 0) {
				    for (int p = (x+1); p < temp.size(); p++) {
					if (temp.get(i).compareTo(temp.get(p)) == 0)
					    retBoo = true;
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

    public boolean isFour?() {
	boolean retBoo = false;
	if (isTrip?()) {
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

    public boolean isStraightFlush?() {
	return (isFlush?() && isStraight?());
    }
}
    
