import java.util.ArrayList;
import java.util.Random;

// Wesley Legault - Final Project
// Should also be singleton Only one player playing the game at once
public class Player extends Actor{

    // The players health
    private int health;
    // Player name
    private String name;
    // Player funds
    private int gold;
    // PLayers armor raiting
    private int armor;


    // Singleton again. Make sure there is not a second player instance. Would not make sense
    private static Player pc = null;

    // Used to track the players inventor
    private ArrayList<Items> inventory = new ArrayList<>();

    // Constructor
    private Player(){
        super();
        setupPlayer();
    }

    // Ensure only one player can be in the game
    public static Player newPlayer(){
        if(pc == null){
            pc = new Player();
            return pc;
        } else{
            return pc;
        }
    }

    // Set defaults and prompt for name
    private void setupPlayer(){
        this.health = 10;
        this.gold = 100;
        this.armor = 14;
        this.name = getInput();
    }

    // Prompt for name -- Can be anything
    public String getInput(){
        System.out.print("Enter your character's name: ");
        String input = GameController.kb.nextLine();
        System.out.println("\n");
        return input;
    }

    // Add an item to the inventory
    public void addItem(Items i){
        inventory.add(i);
    }

    // Getter for health
    public int getHealth(){
        return this.health;
    }

    // Setter for health
    public void setHealth(int h){
        this.health = h;
    }

    // Getter for name
    public String getName(){
        return this.name;
    }

    // Change the gold amount
    public void changeGold(int g){
        this.gold += g;
    }

    // Get the players gold
    public int getGold(){
        return this.gold;
    }

    // Damage the player
    public void takeDamage(int d){
        this.health -= d;
    }

    // Uses a potion from the players inventory then removes that potion
    public void usePotion(){

        // Check if there is a potion in the inventory
        for (int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getType() == Items.Type.POTION){
                // If there is -- Notify
                System.out.println("Used a potion!");
                // Fill the players health
                this.setHealth(Items.Type.POTION.getValue());
                // Remove the potion from the inventory
                inventory.remove(i);
                // Exit funtion
                return;
            } 
        }
        // Otherwise no potions -- Notify user
        System.out.println("No potions remaining! Good Luck!");
    }

    // Shows the players name and health -- Useful for combat
    public String showStats(){
        return this.name + "'s current status\nHealth: " + this.health + "\n";
    }

    // Checks the players inventory for what weapon they currently have
    // Returns the damage dealt to the caller
    public int getWeapon(){
        // Check for either type
        for(Items i : inventory){
            // Check the types
            if(i.getType() == Items.Type.BOW){
                // Return the damage amount
                return Items.Type.BOW.getValue();
            } else if(i.getType() == Items.Type.SWORD){
                return Items.Type.SWORD.getValue();
            }
        }
        // No weapon (shouldn't happen)
        return 0;
    }

    // Get the players armor rating -- Useful for combat
    public int getArmor(){
        return this.armor;
    }

    // Useful for debugging and notification
    // Prints the users inventory to the screen
    public void inventoryToString(){
        for(Items i : inventory){
            System.out.println(i.getType());
        }
    }
}