// Wesley Legault - Final Project

// This class is used to represent all characters that appear in the game
public abstract class Actor{

    // Name of the character
    private String n;
    // Health of the character
    private int h;

    // Constructor
    public Actor(){
        this.n = "";
        this.h = 0;
    }

    // All character must have these methods at he very least
    public abstract String getName();
    public abstract int getHealth();
    public abstract void takeDamage(int h);
}