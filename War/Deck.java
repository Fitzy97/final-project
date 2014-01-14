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
	
	public Card draw() {
		
		Card retCard = _deck.get(0);
		
		_deck.remove(0);
		
		return retCard;
			
		}
	}
}
		   

	       

	
