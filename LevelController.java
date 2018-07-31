// Wesley Legault - Final Project

import java.util.ArrayList;
import java.util.Random;

// This class is responsible level flow and creation
public class LevelController{

    // Can be changed to generate more or less levels in the future
    public static final int NUMLEVELS = 5;

    // Dynamic level sequence stores in this list
    // Different level layout each game
    private ArrayList<Level> levelList = new ArrayList<>();
    // Dynamic monsters each game
    private ArrayList<Monster> enemies = new ArrayList<>();
    // Used to randomize generate the sequence
    private int sequence[] = new int[5];

    // Constructor
    public LevelController(){
        this.sequence = randomize();
        genLevels();
        genMonsters();
    }
    
    // Run all of the levels generated
    public void runLevel(Player p){
        for(int i = 0; i < NUMLEVELS; i++){
            // Pass in the monster(s) for each level
            levelList.get(i).runLevel(p, enemies.get(i));
        }
    }

    // Runs a specific level - Identified by its level type
    public void runSpecificLevel(int l, Player p){
        // Instantiate new level
        Level specificLevel = new Level(l);
        specificLevel.runLevel(p);
    }

    // This function randomized an integer array that is used to create the
    // level objects for the game. This method DISALLOWS repeatable levels
    private int[] randomize(){

        // Create the array in order
        int[] baseState = {1, 2, 3, 4, 5};
        // Current index
        int current;
        // Temp index
        int temp;

        // Get a new random number
        Random r = new Random();
        // Shuffle in array based on the random number
        for (int i = baseState.length - 1; i > 0; i--){
            current = r.nextInt(i + 1);
            temp = baseState[current];
            baseState[current] = baseState[i];
            baseState[i] = temp;
        }
        // Return the array to seed the levels
        return baseState;
    }

    // Generate the levellist based on the randomized sequence
    private void genLevels(){
        for(int i = 0; i < sequence.length; i++){
            levelList.add(new Level(sequence[i]));
        }
    }

    // Similar to the random level but uses a switch and no array
    // Monsters can be repeated
    private void genMonsters(){
        Random r = new Random();
        int number;

        // Add a new monster to the list based on the random number
        for(int i = 0; i < 5; i++){
            number = r.nextInt(3) + 1;
            switch (number) {
                case 1:
                    enemies.add(new Zombie());
                    break;
                case 2:
                    enemies.add(new Skeleton());
                    break;
                case 3:
                    enemies.add(new Troll());
                    break;
                default:
                    break;
            }
        }
    }
}