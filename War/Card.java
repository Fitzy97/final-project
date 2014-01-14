// Comparable Card class to be used in playing card games

public class Card implements Comparable{


//~~~~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private String _suit, _face;
    // 4 suits are clubs, hearts, spades, diamonds
    // _face is String version of value esp important for royalty where for example val=11 -> face=jack

    private int _value;
    // numerical value, useful for seeing which card "beats another"

//~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Card( String suit, int value) {
	_suit  = suit;
	_value = value;
    }

//~~~~~~~~~~~~~~~~ACCESSOR METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public String getSuit() {
	return _suit;
    }

    public String getFace() {
	return _face;
    }
    
    public int getValue() {
    	return _value;
    }
    
//~~~~~~~~~~~~~~~OTHER METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
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
    
    // check if 2 objs are equivalent
    public boolean equals( Object other ) {
    	return this == other // check for aliases
    	||
    	( other instanceof Card 
    	&& this._value == ((Card) other)._value);
    }
    
    // tels which of the two Card objs is great
    public int compareTo( Object other ) {
    	
    	if (! (other instanceof Card) )
    		throw new ClassCastException("\ncompareTo() input not Card");
    	
    	return this._value - ((Card)other)._value;
    }
	



}
