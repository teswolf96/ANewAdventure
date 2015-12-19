
public class Fighter {
    protected int agility;
    protected int init;
    
    public Fighter(){
    	agility = 10;
    	init = -1;
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
    

}
