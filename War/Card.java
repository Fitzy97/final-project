public class Card {

    private String _suit, _face;
    // 4 suits are clubs, hearts, spades, diamonds
    // _face is String version of value esp important for royalty where for example val=11 -> face=jack

    private int _value;
    // numerical value, useful for seeing which card "beats another"

    public Card( String suit, int value) {
	_suit  = suit;
	_face = face;
    }

    public String getSuit() {
	return _suit;
    }

    public String getFace() {
	return _face;
    }
    
    public void setFace() {
    	if (_value < 11) {
    		_face = Integer(_value).toString();
    	}
    	else if (_value == 11) {
    		_face = "jack"
    	}
    	else if (_value == 12) {
    		_face = "queen"
    	}
    	else if (_value == 13) {
    		_face = "king"
    	}
    	else {
    		_face = "ace"
    	}
    }
	

}
