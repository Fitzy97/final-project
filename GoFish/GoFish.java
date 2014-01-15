

// class gofish for minigame

public class GoFish {
  
  
  //~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~
  
  int _difficulty
  
  Deck _deck;
  Hand _playerH, _compH;
  
  public GoFish( int difficulty) {
    
    _difficulty = difficulty;
    
  }
  
  
  public void begin() {
    
    _deck = new Deck();
    _playerH = new Hand();
    _compH = new Hand();
    
    _deck.shuffle();
    
    _playerH.take( _deck, 5);
    _compH.take(_deck, 5)
    }
  }
  
  public void reportHand() {
    System.out.println( "Your hand is...");
    
    for (int i = 0; i < _playerH.getSize(); i++) {
      System.out.println( _playerH.getHand().get( i ) );
    }
    
  }
  
  public void round() {
    reportHand();
    
  }
  
  // to be inplemented later: difficulty translates to # of cards drawn
  
  public static void main( String[] args) {
    
    GoFish game = new GoFish();
    
    game.begin();
    

    
    
  }
  
}
