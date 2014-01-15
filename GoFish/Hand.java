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
}
    
