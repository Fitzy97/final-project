
public class Hand {

    private int _size;
    
    private ArrayList<Card> _cards;

    public Hand() {

	_size = 0;
	_cards = new ArrayList<Card>();

    }

    public void draw( Deck mainDeck, int num ) {
	
