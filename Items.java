// Wesley Legault

// This class holds all of the items available in the game
public class Items{

    // The type of item
    private Type type;

    enum Type{
        // Default
        EMPTY(-1, 0, ""),
        POTION(100, -10, "Potion of Healing"),
        BOW(-50, -70, "Bow"),
        SWORD(-65, -80, "Sword");

        // How much the item affects health of actors
        int value;
        // What the item costs in gold
        int cost;
        // Name of the item
        String name;

        // Constructor
        private Type(int v, int c, String n){
            this.value = v;
            this.cost = c;
            this.name = n;
        }

        // Getter
        public int getValue(){
            return this.value;
        }
        // Getter
        public int getCost(){
            return this.cost;
        }
        // Getter
        public String getName(){
            return this.name;
        }
        
    }

    // Default class constructor
    public Items(){
        this.type = Type.EMPTY;
    }

    // Override
    public Items(int t){
        // Create an item based on the type(integer)
        switch(t){
            case 1:
                this.type = Type.POTION;
                break;
            case 2:
                this.type = Type.BOW;
                break;
            case 3:
                this.type = Type.SWORD;
                break;
            default:
                this.type = Type.EMPTY;
                break;
        }
    }

    // Getter for the type of the item
    public Type getType(){
        return this.type;
    }
}
