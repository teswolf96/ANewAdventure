
public class Fighter {
    protected int agility;
    protected int init;
    
    public Fighter(){
    	init = -1;
    }
    
    public int rollInitiative(){
    	init =  Dice.roll(20) + (agility - 10)/2; //Standard D&D way of calculating modifiers   	
    	return init;
    }
    
    public int getInit(){
    	return init;
    }
    

}
