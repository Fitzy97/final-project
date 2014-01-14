public class Deck {
        
	private ArrayList<Card> _deck;

	public Deck() {
	       _deck = new ArrayList<Card>();

	       // fill deck with complete set of cards

	       for ( int i = 2; i < 15; i++ ) {
		   Card add1 = new Card("hearts", i);
		   Card add2 = new Card("spades", i);
		   Card add3 = new Card("diamonds", i);
		   Card add4 = new Card("clubs", i);

		   _deck.add( add1 );
		   _deck.add( add2 );
		   _deck.add( add3 );
		   _deck.add( add4 );

	       }
	       
	       for ( Card c : _deck) {
	           c.setFace();
	       }
	}
	
	
	// shuffling helper method
	
	public void swapper(ArrayList<Object> al) {
		int num1 = (int) (Math.random() * al.size());
		int num2 = (int) (Math.random() * al.size());
		
		Object ob1 = al.get( num1 );
		Object ob2 = al.get( num2 );
		
		al.set( num1, ob2 );
		al.set( num2, ob1 );
	}
	
	// shuffle deck method
	public void shuffle() {
		for ( int i = 0; i < 250; i++ ) {
			swapper( _deck );
		}
	}
	
	
	// draw method to be called on in games -> returns card & removes it from its own deck
	public Card draw() {
		
		Card retCard = _deck.get(0);
		_deck.remove(0);
		return retCard;
			
		}
	}
	
	public void add( Card newCard ) {
		
		_deck.add( newCard);
	}
}
		   

	       

	
