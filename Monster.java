// Wesley Legault - Final Project

// All monsters that appear in this game come from this class
public class Monster extends Actor{

    // Monster name
    private String type;
    // Monster health
    private int health;
    // Monster armor rating
    private int armor;
    // Monster base damage
    private int damage;

    // Constructor
    public Monster(String n, int h, int d, int a){
        this.type = n;
        this.health = h;
        this.armor = a;
        this.damage = d;
    }

    // Get the monster name
    public String getName(){
        return this.type;

    }

    // Get the monsters current health 
    public int getHealth(){
        return this.health;
    }
    
    // Damage the monster
    public void takeDamage(int d){
        this.health += d;
    }

    // Show default monster text
    public void monsterSound(){
        System.out.println("\n\nYou hear something coming your way in the darkness.\n\n");
    }

    // Get armor rating
    public int getArmor(){
        return this.armor;
    }

    // Get the monsters damage
    public int getDamage(){
        return this.damage;
    }

    // Display the flavor text for when a monster in defeated
    public void killed(){
        System.out.println("The monster crumples to the floor");
    }
    
}