import java.util.*;
import java.io.*;

public class War extends miniGame{

    private InputStreamReader isr;
    private BufferedReader in;

    private int _difficulty;

    public War( int diff ) {
	_difficulty = diff;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	System.out.println("Welcome to War (the hand game)!  You and your opponent start out with 0 ammo.  In each turn, you have the choice of reloading, shooting, or blocking, but you can only choose one per turn.  At the same time your opponent will do the same.  If you have ammo and you shoot when your opponent reloads, you win.  If your opponent shoots when you reload, you lose.  If you both shoot at the same time, you both lose.");
    }

    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
	    end = new Date();
        }
    }

    public boolean playTurn() {

	boolean retBoo = false;

	int myAmmo = 0;
	int oppAmmo = 0;
	int turnCount = 1;
	int choice = 0;

	boolean myBlock = false;
	boolean myShoot = false;
	boolean myReload = false;

	boolean oppBlock = false;
	boolean oppShoot = false;
	boolean oppReload = false;

	while ( (!(myShoot && oppReload)) && (!(myShoot && oppShoot)) && (!(oppShoot && myReload)) ) {

	    System.out.println("Neither you nor your opponent is dead!");
	    System.out.println("Turn " + turnCount);
	    System.out.println("What would you like to do?");
	    System.out.println("1: Shoot\n2: Reload\n3: Block");
	    System.out.print("Your choice: ");
	    try {
		choice = Integer.parseInt( in.readLine() );
	    }
	    catch (IOException e) { }
	    System.out.println("Your ammo: " + myAmmo);
	    if (choice == 1) {
		if (myAmmo == 0) {
		    System.out.println("You have chosen to shoot but you 0 ammo!  Choose again!");
		    System.out.print("Your choice: ");
		    try {
			choice = Integer.parseInt( in.readLine() );
		    }
		    catch (IOException e) { }
		}
		else {
		    myAmmo--;
		    myShoot = true;
		}
	    }
	    else if (choice == 2) {
		myAmmo++;
		myReload = true;
	    }
	    else if (choice == 3)
		myBlock = true;

	    System.out.println("You opponent is thinking...");
	    pause(2);
	    if (oppAmmo < 1)
		choice = (int)(Math.random()*2+2);
	    else {
		choice = (int)(Math.random()*3+1);
		if (choice == 1) {
		    oppAmmo--;
		    oppShoot = true;
		}
	    }
	    if (choice == 2) {
		oppAmmo++;
		oppReload = true;
	    }
	    else if (choice == 3)
		oppBlock = true;
	    turnCount++;
	}

	if (oppShoot && myReload)
	    System.out.println("The computer shot when you reloaded!  This means you are dead.");
	else if (myShoot && oppReload) {
	    System.out.println("You shot when the computer reloaded!  This means you win.");
	    retBoo = true;
	}
	else if (myShoot && oppShoot)
	    System.out.println("You and the computer both shot at the same time!  This means you lose.");

	return retBoo;
    }

    public boolean play( Player gambler ) {
	return playTurn();
    }

    public static void main(String[] args) {

	Player a = new Player();
	War b = new War( 3 );
	b.play(a);
    }
}
