import java.util.LinkedList;
/*
 * @Author: Thomas Shaw
 * @Date: 12/18/15
 * This is the new combat system! Modularity FTW!
 */
public class Combat {
	
	
	public LinkedList<Fighter> turnOrder(LinkedList<Fighter> inBattle){
		LinkedList<Fighter> order = new LinkedList<Fighter>();
		if(order.isEmpty()){
			order.add(inBattle.getFirst());
			inBattle.removeFirst();
		}else{
			Fighter nextChar = inBattle.getFirst();
		}
		
		return null;
		
	}

}
