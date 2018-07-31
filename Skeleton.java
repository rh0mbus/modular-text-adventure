// Skeleton monster
public class Skeleton extends Monster{

    // The skeletons damage
    private int damage;

    // Constructor
    public Skeleton(){
        super("Skeleton", 80, 10, 11);
    }

    // Alternate version to attack a player
    public void attack(Player p){
        p.takeDamage(damage);
    }

    // Show the skeletons introductory text
    public void monsterSound(){
        System.out.println("\n\nYou hear the clattering of dry bones hitting one another.\n\n");
    }

    // Show the skeleton flavor text for when the skeleton is defeated
    public void killed(){
        System.out.println("The skeletons bones go clattering across the ground.");
    }

}