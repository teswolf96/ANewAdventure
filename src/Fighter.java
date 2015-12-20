
public class Fighter {
    protected int agility;
    protected int init;
    protected int health;
    protected boolean isChar;
    
    public Fighter(){
    	agility = 10;
    	init = -1;
    	health = 10;
    	isChar = false;
    }
    
    public Fighter(int agl, int hlth, boolean isPlayer){
    	agility = agl;
    	health = hlth;
    	isChar = isPlayer;
    	
    }
    
    public int rollInitiative(){
    	int roll = Dice.roll(20);
    	int mod = (agility - 10)/2;
    	init =  roll + mod ; //Standard D&D way of calculating modifiers   
    	return init;
    }
    
    public int getInit(){
    	return init;
    }
    
    public void getMenu(){
    	System.out.println("This is the generic menu!");
    }

}
