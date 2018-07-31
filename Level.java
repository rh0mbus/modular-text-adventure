import java.util.Random;

// Where all the story takes place, monsters spawn here also
public class Level{

    // Used to get the proper story and gameplay elements
    private int levelType;
    // Used for dice rolling in the game
    private Random r = new Random();

    // Constructor
    public Level(int l){
        this.levelType = l;
    }

    // Used for running specific levels. Only currently implemented for the starting level and ending level
    public void runLevel(Player p){
        // Call the proper level based on the level type
        // Pass in the player object to these levels only (No monsters allowed)
        switch(this.levelType){
            case  0:
                Level0(p);
                break;
            case 6:
                Level6(p);
                break;
            default:
                break;
        }
    }

    // Call the proper level based on the level type
    // Pass in the player object AND monster object
    public void runLevel(Player p, Monster m){
        switch(this.levelType){
            case 1:
                Level1(p, m);
                break;
            case 2:
                Level2(p, m);
                break;
            case 3:
                Level3(p, m);
                break;
            case 4:
                Level4(p, m);
                break;
            case 5:
                Level5(p, m);
                break;        
            default:
                break;
        }
    }

    // Setter for custom level type
    public void setType(int i){
        this.levelType = i;
    }

    // This function runs all combat encounters that happen in a level
    public void combat(Player p, Monster m){
        // Flag for checking if fight is happening
        boolean fighting = true;
        // The players attack roll
        int playerAttack;
        // Monsters attack roll
        int monsterAttack;
        // The players choice
        int choice;

        // Warn of combat stat
        System.out.println("\n**COMBAT HAS STARTED!**");

        // Polymorphism for playing monster introduction
        m.monsterSound();

        do{
            // Show fighting options
            System.out.println("What do you want to do?\n1 - Fight\n2 - Drink a healing potion\n3 - Run back to the surface!");
            // Show player status
            System.out.println(p.showStats());
            // Get choice
            choice = getChoice();
            // Attack choice
            if(choice == 1){

                // Roll a 20 sided die for attack rolls
                playerAttack = r.nextInt(20) + 1;
                monsterAttack = r.nextInt(20) + 1;

                // Notify of results
                System.out.println("You rolled a " + playerAttack);
                System.out.println(m.getName() + " rolled a " + monsterAttack);
                System.out.println("Player roll needed to hit " + m.getArmor());

                // Roll must exceep the armor rating to hit
                if(playerAttack > m.getArmor()){
                    // If hit --> Subtract health equal to weapon damage
                    m.takeDamage(p.getWeapon());
                    // Notify
                    System.out.println("\nYou hit " + m.getName() + " for " + (p.getWeapon() * -1) + " damage!\n");
                } else {
                    // A miss
                    System.out.println("You miss your target!");
                }
                // Same as above
                if(monsterAttack > p.getArmor()){
                    p.takeDamage(m.getDamage());
                    System.out.println("\n" + m.getName() + " hit you for " + m.getDamage() + " damage!\n");
                }
            } else if(choice == 2){ // Option to use a potion for healing the player
                // Call the usePotion funtion on the player
                p.usePotion();
                // Notify
                System.out.println(p.showStats()); 
            } else if(choice == 3){
                // Quit the game if you want
                System.out.println("You cowardly run up to the surface leaving the \nmysteries of the tomb behind you.");
                System.out.println("Game Over!");
                System.exit(0);
            } else {
                // Something went wrong
                System.out.println("Invalid choice");
                System.exit(1);
                continue;
            }
            // Check for death
            if(p.getHealth() <= 0){
                System.out.println("You have died in combat!");
                System.out.println("Game over!");
                System.exit(0);
            }
            // Check for defeating the montster
            if(m.getHealth() <= 0){

                // Poly morphism again to show the proper defeat flavor text
                m.killed();
                // Notify
                System.out.println("You have defeated the " + m.getName() + "! Congratulations!");
                System.out.println("**Combat is now over**\n");
                // Exit combat loop
                fighting = false;
            }
        // Run while the flag is true and all involved creatures are alive
        } while(fighting && p.getHealth() > 0 && m.getHealth() > 0);

    }

    // Generic method for notifying the user of what took place after
    // combat has concluded
    public  <T extends Actor> void printActor(T actor) {

        // Check against instance types
        if(actor instanceof Player){
            // Display actor types
            System.out.println("Player: " + actor.getName());
        } else if (actor instanceof Monster){
            // Display actor types
            System.out.println("Defeated: " + actor.getName());
        } else {
            // Something went wrong
            System.err.println("Error : Uknown object!");
        }
    }

    // Starting level
    private void Level0(Player p){

        // Used for decision making
        int currentChoice; 

        System.out.println("You had a rough night last night...\n Just before arriving into town you" +
            " were robbed along the path into Silverymoon. The only thing you " + 
            "thought to do was to go to the local tavern and drown your sorrows in some ale.\n" + 
            " You came to Silverymoon for a fresh start. Apparently your wishes are coming true since you've " +
            " no longer got anything aside from the cloths on your back and, luckily enough, your gold pouch.\n" +
            "After a few hours of drinking and a strange woman approaches with scraggly long gray" + 
            " hair and wrinkled skin. She smells of stale ale and smoke. You recoil a bit at first but she " + 
            " tells you of a nearby tomb that is said to contain great wealth. You lend her your ear for a while " + 
            " but eventually you get decide to go to sleep for the night. \n The following morning you can't help but " +
            "consider what the old woman was saying... \n What do you decide to do?");
            System.out.println("1 - Decide to check out this mysterious tomb and see what is inside.\n" + 
                "2 - Go about your life normally.\n");
            // Get the players choice
            currentChoice = getChoice();
            // Switch on that choice
            switch(currentChoice){
                case 1:
                    System.out.println("Before heading out to the tomb you decide it is best to buy some equipment " + 
                    "so you head to the markets.");
                    // Buy equipment
                    Shopping(p);
                    break;

                case 2:
                    // Quit the game
                    System.out.println("You decide that an adventure sounds far too dangerous for you" + 
                        " and try to recover you losses the from yesterday in a safer manner.");
                    System.exit(0);
                    break;

                default:
                    // Catch upexpected
                    System.out.println("Invalid response: exiting game");
                    System.exit(1);
            }
    }

    // Level 1 - NOTE - these can occur in ANY ORDER
    private void Level1(Player p, Monster m){
        System.out.println("You round the corner and come across an engraved plate on the wall " + 
            "of a dark and dank room.\nStrangely enough there is a lit torch on the wall next to the plate and" +
            " you \ndecide it is probably best for you to take this with you.");

        // Health based monster spawn
        if(p.getHealth() % 3 == 0){
            combat(p, m);
            // Call generic to what the player just fought
            printActor(p);
            printActor(m);
        }
        
        System.out.println("You walk up to the plate and notice that it reads\n\"I am a strong as ten men,\nyet ten men cannot stand me up,\n" +
            "What am I?\"\n");
        System.out.println("Three buttons next to the engraved plate have the following words on them and look like\n" +
            "they could be pressed inward. What do you press?");

        // Correct answer = Water --
        System.out.println("1 - Trees\n2 - A bear \n3 - Water");
        // Get the players choice
        int choice = getChoice();
        // Check the correct choice
        if(choice != 3){
            System.out.println("You hear a feint crack as a dart flies at you hitting you in the leg." + 
                "\nYou take 10 damage");
            // Damage player for failure
            p.takeDamage(10);
            // Check for player death
            if(p.getHealth() == 0){
                System.out.println("\nYou have died! Better luck next time!\n");
                System.exit(0);
            }
            System.out.println("Moments later a door on your left opens up and you stumble through.\n");
            // Show the players stats
            System.out.println(p.showStats()); 
        } else {
            System.out.println("The button depresses and nothing happens for a moment.\n A door on your left opens." +
                " You walk through the door.");
        }
    }

    // Another level
    private void Level2(Player p, Monster m){
        // Generate a random chance encounter
        int encouter = r.nextInt(100);

        // 50% chance for a fight
        if(encouter < 50){
            combat(p, m);
            // Call generic to what the player just fought
            printActor(p);
            printActor(m);
        }

        System.out.println("Behind the door you see a small pool of standing water in the middle of the room." +
            " A spirit appears in front of you and asks you to answer her riddle." + 
            "\nIn a raspy otherwordly voice she mutters:\nYou can see nothing else\n" +
            "When you look in my face,\nI will look you in the eye,\nAnd I will never lie.\n");
        System.err.println("You answer her question with:\n1 - Your parents\n2 - A mirror\n3 - A clock");
        // Correct answer = mirror
        // Get the choice
        int choice = getChoice();
        // Check the answer
        if(choice != 2){
            // Punish for wrong answer
            System.out.println("You suddenly feel ice cold. The spirit rushes through you and vanishes.\n" +
                "You take 15 damage");
            // Damage player    
            p.takeDamage(15);      
            // Check for death      
            if(p.getHealth() == 0){
                System.out.println("\nYou have died! Better luck next time!\n");
                System.exit(0);
            }
            // Show stats
            System.out.println(p.showStats());
            System.out.println("Out of nowhere the pool drains and reveals a trap door.\n"); 
        } else {
            System.out.println("The spirit gives a light chuckle and vanishes");
            System.out.println("As soon as the spirit dissipates the pool drains revealing a trap door.");
        }
    }

    // Yet another level
    private void Level3(Player p, Monster m){
        System.out.println("You come into an empty hallway with a door at the far side. A unknown" +
            " creature stands in your path...");
        // Guaranteed fight
        combat(p, m);
        // Call generic to what the player just fought
        printActor(p);
        printActor(m);
    }

    // Another level 
    private void Level4(Player p, Monster m){
        // Generate another chance encounter
        int encouter = r.nextInt(100);

        System.out.println("The door falls off of its rotting hinges as you press forward." +
            "\nThe loud noise startles you. Hopefully you were the only one to have heard that...");

        // ~35% chance for a fight
        if(encouter < 35){
            System.out.println("Unfortunately you were not alone in hearing the deafening noise of " +
                "the heavy door falling to the ground.");
            combat(p, m);
            // Call generic to what the player just fought
            printActor(p);
            printActor(m);
        }
        System.out.println("There are three swords laying on a large pedestal at the center of the room." +
            "\nThere is an enscription on the pedestal that states:\n\"Only the strong may proceed\"");
        System.err.println("What do you do?\n1 - Pick up the left sword\n2 - Pick up all of the swords\n" + 
            "3 - Pick up the right sword\n4 - Pick up the middle sword\n5 - Drink a sip of a healing potion");
        // Get the choice
        int choice = getChoice();
        // Correct answer == either #2 or #5
        // Check the answers
        if(choice == 1 || choice == 3 || choice == 4){
            System.out.println("The swords fly up into the air on their own striking you as they do so.\n" +
                "You take 45 damage before they come clattering back to the ground.");
            // Damage player
            p.takeDamage(45);
            // Check for death            
            if(p.getHealth() == 0){
                System.out.println("\nYou have died! Better luck next time!\n");
                System.exit(0);
            }
            System.out.println(p.showStats());
            System.out.println("The large pedestal rises out of the ground revealing a door at its center.\n"); 
        } else {
            System.out.println("The swords turn to dust and the large pedestal rises out\nof the ground revealing a door at its center.");
        }
    }

    // Again, another lever
    private void Level5(Player p, Monster m){
        System.out.println("The door slowly creaks open. The noise seems to have disturbed whatever was on the other side.");
        System.out.println("Suddenly, through the torchlight you see a dark figured rush toward you!");
        // Another guaranteed fight
        combat(p, m);
        // Call generic to what the player just fought
        printActor(p);
        printActor(m);
    }

    // The end level -- Always occurs at the end of the game
    private void Level6(Player p){
        System.out.println("The room you enter is brightly lit by several torches who bear a bright blue flame.");
        System.out.println("The room grows brighter as you enter. Blinding you as walk closer to the center of the room.");
        System.out.println("The next thing you know you feel weightless and cannot see anything other than blinding white light");
        System.out.println("When you vision clears you feel the cool breeze of the wind and can see the sun setting in front of you");
        System.out.println("You have been teleported to the entrance of the tomb! Immediately you notice that resting at your" +
            " feet is a large chest that feels like it weighs more than you do!");
        System.out.println("Congratulations! You did it!");
    }

    // This function gets the players choice from the command line
    private int getChoice(){
        // The current string
        String line;
        // Default
        int choice = -1;
        // Run while true
        boolean flag = true;

        while(flag){
            System.out.println("Please enter the number of your choice: ");
            line = GameController.kb.nextLine();
            // Regex for digits only
            if(line.matches("[1-9]")){
                // Get the integer from the string
                choice = Integer.parseInt(line);
                flag = false;
            } else {
                // Warn for wrong input
                System.out.println("Invalid choice. Enter the number of your choice: ");
            }
        }
        return choice;
    }

    // This function handles the opening shopping sequence
    private void Shopping(Player p){
        // The players choice
        int choice;
        // Used to make sure the player buys a weapons
        boolean boughtWeapon = false;
        // Check to see if the person wants to exit shopping early
        boolean done = false;
        // Can only buy one weapon
        int weaponStock = 1;
        // Can only buy one potion
        int potionStock = 3;
        System.out.println("\n\n\nYou arrive at the market district of Silverymoon.\n" +
            "The smell of freshly baked breads and cooked foods waft over you. It all smells" + 
            " so delicious.\nYou grab yourself some breakfast and then search around for the nearest " +
            " adventurers shop.\n The owner gives you a warm greeting and asks what he can get for you:\n" );

        // Run at least once
        do {
            System.out.println("Your total gold: " + p.getGold() + "\nWhat'll it be?\n");
            System.out.println("1 - Healing potion - Fills health to maximum.");
            System.out.println("2 - Bow - Does 50 damage to enemies.");
            System.out.println("3 - Sword - Does 65 damage to enemies.");
            System.out.println("4 - Done shopping.");
            choice = getChoice();
            
            // Switch on choice add the selected items to the players inventory
            switch(choice){
                case 1:
                    // Check if potions in stock
                    if(potionStock >= 1){
                        // Add to player
                        p.addItem(new Items(choice));
                        // Reduce player gold
                        p.changeGold(Items.Type.POTION.getCost());
                        // Reduce potion stock
                        potionStock--;
                        System.out.println("\n\nYou have purchased a potion.\nThank you for your business!");
                    } else {
                        System.out.println("\n\nWe're sold out of potions! You bought them all!");
                    }
                    break;
                case 2:
                // Make sure play only buys one weapon
                // Works the same as the potion case above
                if(weaponStock >= 1){
                    p.addItem(new Items(choice));
                    p.changeGold(Items.Type.BOW.getCost());
                    boughtWeapon = true;
                    weaponStock--;
                    System.out.println("\n\nYou have purchased a bow!\nGood choice!");
                } else{
                    System.err.println("\n\nYou already have a weapon!");
                }
                    break;
                case 3:
                // Same
                    if(weaponStock >= 1){
                        p.addItem(new Items(choice));
                        p.changeGold(Items.Type.SWORD.getCost());
                        boughtWeapon = true;
                        weaponStock--;
                        System.out.println("\n\nYou have purchased a sword!\nA fine blade!");
                    } else {
                        System.err.println("\n\nYou already have a weapon!");
                    }
                    break;
                case 4:
                // This is used for when the player wants to leave the shopping phase early
                // Can make for a more challengine game
                // Make sure the player has a weapon first!
                    if(boughtWeapon){
                        System.out.println("\n\nIt's been a pleasure doing business with you!\n Good day!");
                        done = true;
                        break;
                    } else {
                        break;
                    }
                // Something went wrong
                default:
                    System.out.println("Invalid choide - exiting");
                    System.exit(1);
            }
        }
        // Run while they didnt leave early and still have gold
        while((choice != 4 && p.getGold() > 0) && (!done));

        System.out.println("Now that you are armed. You head out of the town and head toward the tomb" +
            " the mysterious woman told you about.\n\n Then entrance to the tomb is overgrown with vines and " +
            "the pathway leads downward and curves out of sight. You continue into the musty hallway and come upon" +
            " your first room.");
    }

}