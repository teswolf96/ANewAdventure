import java.util.LinkedList;
/*
 * @Author: Thomas Shaw
 * @Date: 12/18/15
 * This is the new combat system! Modularity FTW!
 */
public class Combat {
	
	
	public static LinkedList<Fighter> turnOrder(LinkedList<Fighter> inBattle){
		LinkedList<Fighter> order = new LinkedList<Fighter>();
		while(!inBattle.isEmpty()){			
			Fighter nextChar = inBattle.getFirst();
			inBattle.removeFirst();
			int charInit = nextChar.rollInitiative();
			if(order.isEmpty()){
				order.addFirst(nextChar);
			}else{
				boolean sorted = false;
				for(int idx=0;idx<order.size();idx++){
					if(charInit > order.get(idx).getInit()){
						order.add(idx, nextChar);
						sorted = true;
						break;
					}
				}
				if(!sorted)
					order.addLast(nextChar);
				
			}
			
			
		}
			return order;
		
	}

}
