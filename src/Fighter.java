
public class Fighter {
    protected int agility;
    protected int init;
    protected int health;
    protected boolean isChar;
    protected String name;

    public Fighter(){
    	agility = 10;
    	init = -1;
    	health = 10;
    	isChar = false;
        name = "Undef";
    }
    
    public Fighter(String fName, int agl, int hlth, boolean isPlayer){
    	agility = agl;
    	health = hlth;
    	isChar = isPlayer;
    	name = fName;
    }
    
    public int rollInitiative(){
    	int roll = Dice.roll(20);
    	int mod = (agility - 10)/2;
    	init =  roll + mod ; //Standard D&D way of calculating modifiers
    	return init;
    }

    public String getName(){
        return name;
    }

    public int getInit(){
    	return init;
    }
    
    public void getMenu(){
    	System.out.println("This is the generic menu!");
    }

}
