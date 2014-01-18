// class gofish for minigame

public class GoFish {
  
  
  //~~~~~~~~~~~~~~INSTANCE VARS~~~~~~~~~~~~~~
  
  int _difficulty
  
  Deck _deck;
  Hand _playerH, _compH; _playerP, _compP;
  // set aside hands for active cards & completed pairs
  
  
  //~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~
  public GoFish( int difficulty) {
    
    _difficulty = difficulty;
    
  }
   // to be inplemented later: difficulty translates to # of cards drawn
  
  
  //~~~~~~~~~~SETUP METHOD~~~~~~~~~~~
  public void begin() {
    
    _deck = new Deck();
    _playerH = new Hand();
    _compH = new Hand();
    _playerP = new Hand();
    _compP = new Hand();
    
    _deck.shuffle();
    
    _playerH.take( _deck, 5);
    _compH.take(_deck, 5)
  }
  


// ~~~~~~~~~~~METHOD FOR EACH ROUND OF PLAY~~~~~~~
  public void round() {
    reportHand();
    personGuess();
    
  }
  
//~~~~~~~~~~~METHODS CENTRAL TO ROUND~~~~~~~~~~~~~

 public void personGuess() {
    int choice;
    
    System.out.print(Enter the number of the card you would like to inquire about:)
    try {
      choice = Integer.parseInt( in.readLine() ); //?
    }
    
    int pos = finder( _compH, _playerH.get(choice) );
    
    if ( pos > -1) {
      
      // add new card to player hand
      _playerH.add( _compH.draw( pos) );
      
      System.out.println("Computer: Yes I have that card... *forks it over*" );
    } 
    
  }
 
 
 public void computerGuess() {
   
   int choice = (int) (math.random() * _compH.size());
   Card choiceC = _compH.get(choice)
   
   System.out.println("Computer: Do you have any " choiceC.getName() + "s?");
   reportHand();
   System.out.print("Enter 1 if yes, enter 2 if no.");
   int ans = Integer.parseInt(); // ??
   
   readAnswer( choiceC, ans );
   
 }
 // ~~~~~~~~~~~HELPER METHODS FOR ROUND()~~~~~~~~~
 


// read player's confirmation about whether or not they have 
public void readAnswer( Card choiceC, int ans ) {
  
  int pos = finder( _playerH, choiceC )
  boolean needsLoop = true;

  while ( needsLoop ) {
    if (ans == 1) {
      if (pos > -1) {
        _ compH.add( _playerH.draw(pos) );
        needsLoop = false;
      }
      else {
        System.out.println( "Hey, you are a liar, try again!" )
        System.out.print("Enter 1 if yes, enter 2 if no.");
        ans = Integer.parseInt(); // ??
        break;
      }
    }
  }
 
    public void reportHand() {
    System.out.println( "Your hand is...");
    
    for (int i = 0; i < _playerH.getSize(); i++) {
      System.out.println( "Card " + i + "is " _playerH.getHand().get( i ) );
    }
    
  }
  
  // returns first index where target appears or -1 if not present
  public int finder( ArrayList<Card> al, Card target) {
    for (int i = 0; i < al.size(); i++) {
      if (al.get(i).equals( target ) ) {
        return i
      }
    }
    return -1;
  }
 
 // checks for pairs in a hands and moves them to pairs hand 
  public void checkPairs( ArrayList<Card> al, ArrayList<Card> pairs ) {
    
    for (int i = 0; i < alC.size(); i++) {
      Card checker = alC.get(i);
      for (int n = 0; n < alC.size(); n++ ) {
        if ( n != i && checker.equals( alC.get(n)) ) {
          // prob w/ n and i affecting each other
          pairs.add( al.draw( i) );
          
        }
      }
    }
  }



  
  public static void main( String[] args) {
    
    GoFish game = new GoFish();
    
    game.begin();
    

    
    
  }
  
}
