public class Player {

    private double _health, _strength;
    private final String _name;

    public Player() {
	_health = 1000;
	_strength = 1000;
	_name = "Player";
    }

    public Player( String name ) {
	_health = 1000;
	_strength = 1000;
	_name = name;
    }

    //ACCESSORS
    public double getHealth() {
	return _health;
    }

    public double getStrength() {
	return _strength;
    }

    public String getName() {
	return _name;
    }

    //MUTATORS
    public double setHealth( double health ) {
	double oldVal = _health;
	_health = health;
	return oldVal;
    }

    public void addHealth( double add ) {
	_health += add;
    }

    public double setStrength( double strength ) {
	double oldVal = _strength;
	_strength = strength;
	return oldVal;
    }

    public void addStrength( double add ) {
	_strength += add;
    }

}
