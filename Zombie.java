// The zombie monster
public class Zombie extends Monster{

    // The zombies damage
    private int damage;

    // Constructor
    public Zombie(){
        super("Zombie", 90, 20, 8);
    }

    // Alternate version to attack a player
    public void attack(Player p){
        p.takeDamage(damage);
    }

    // Show the zombies introductory text
    public void monsterSound(){
        System.out.println("\n\nYou hear gutteral moans and the shambling of a creature nearby.\n\n");
    }

    // Show the zombies flavor text for when the zombie is defeated
    public void killed(){
        System.out.println("The zombie takes one step closer toward you then falls over.");
    }
}