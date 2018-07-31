// Wesley Legault - Final Project
import java.util.Scanner;

// Game controller class - runs the game
// Only allows one game to be run at any time
// Running multiple instances of the game doesn't make sense
public class GameController{

    // Global scanner
    public static Scanner kb = new Scanner(System.in);

    // Instance state
    private static GameController instance = null;

    // Create LevelController
    private LevelController levelC;
    // Create a player
    private Player pc = Player.newPlayer();

    // Prevent default constructor
    private GameController(){   
        
    }

    // Ensure only one game can run at once
    public static GameController newGame(){
        if(instance == null){
            instance = new GameController();
            return instance;
        } else{
            return instance;
        }
    }

    // Call the levels into the game
    public void runGame(){
        // Instantiate
        levelC = new LevelController();
        // Call the first level
        levelC.runSpecificLevel(0, pc);
        // Run randomized levels
        levelC.runLevel(pc);
        // Run the ending level
        levelC.runSpecificLevel(6, pc);
    }
}
