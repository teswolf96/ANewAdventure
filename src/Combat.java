import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
/*
 * @Author: Thomas Shaw
 * @Date: 12/18/15
 * This is the new combat system! Modularity FTW!
 * Old combat will be phased out
 */
public class Combat {
	
	public static boolean combatServer(Character player, LinkedList<Fighter> sortedCombatants, DataInputStream input, DataOutputStream output){
		boolean inCombat = true;
        int combatIndex=0;
        while(inCombat){
            Fighter currTurn = sortedCombatants.get(combatIndex);
            if(currTurn.isChar){
                if(currTurn.getName().equalsIgnoreCase(player.getName())){
                    try {
                        output.writeUTF("player2");
                        System.out.println("Your turn!");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try{
                        output.writeUTF("you");
                        System.out.println("Other player!");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{ //Is Monster
                try {
                    System.out.println("It's " + currTurn.getName() + "'s turn");
                    output.writeUTF("monster");
                    output.writeUTF(currTurn.getName());
                }catch(IOException e) {
                    e.printStackTrace();
                }



            }
            combatIndex++;
            if(combatIndex >= sortedCombatants.size()){
                inCombat = false;
                try {
                    output.writeUTF("done");
                }catch(IOException e) {
                    e.printStackTrace();
                }
            } //debug code!
            //if(combatIndex >= sortedCombatants.size()){combatIndex = 0;} //circular linked list!

        }
		
		return true;
	}
	
	public static boolean combatClient(DataInputStream input, DataOutputStream output){

		boolean inCombat = true;

		while(inCombat){
            try {
                String turnInfo = input.readUTF();
                if(turnInfo.equalsIgnoreCase("done")){
                    inCombat = false;
                }else if (turnInfo.equalsIgnoreCase("monster")) {
                    System.out.println(input.readUTF() + "'s turn");

                } else if (turnInfo.equalsIgnoreCase("player2")) {
                    System.out.println("Other player turn");

                } else if (turnInfo.equalsIgnoreCase("you")) {
                    System.out.println("Your turn!");

                } else {
                    System.out.println("Error, invalid message from server");
                }
            }catch(IOException e) {
            e.printStackTrace();
        }

		}
		
		return true;
	}
	
	
	
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
