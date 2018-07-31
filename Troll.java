// Troll monster
public class Troll extends Monster{

    // The trolls damage
    private int damage;

    // Constructor
    public Troll(){
        super("Troll", 110, 25, 13);
    }

    // Alternate version to attack a player
    public void attack(Player p){
        p.takeDamage(damage);
    }

    // Show the trolls introductory text
    public void monsterSound(){
        System.out.println("\n\nLoud growling sounds get close and closer as you tremble in the dimly lit room.\n\n");
    }

    // Show the trolls flavor text for when the troll is defeated
    public void killed(){
        System.out.println("The troll stumbles for a moment and then drops to the ground.");
    }

}