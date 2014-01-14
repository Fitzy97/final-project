// class hand- the collection of cards a player holds

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
}
// ~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~

    public void take( Deck mainDeck, int num ) {
    
    	for (int n = 0; n < num; n ++) {
    		_cards.add( mainDeck.draw() );
    	}
    }
    
    
    public Card draw() {
              
    	Card retCard = _cards.get(0);
        _cards.remove(0);
        return retCard;
    }
}
    
