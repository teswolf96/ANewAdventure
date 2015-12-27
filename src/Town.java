import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Thomas Shaw
 */

public class Town {

    public static void townMenu(Character playerChar, DataInputStream input, DataOutputStream output, boolean isServer){
        boolean inTown = true;
        System.out.println("Welcome to Town!");
        while(inTown) {
            System.out.println("\nYou are in Town!");
            System.out.println("What would you like to do?");
            System.out.println("1) Visit the blacksmith");
            System.out.println("2) Stop by the inn");
            System.out.println("3) Leave town");
            System.out.print("> ");
            Scanner townScan = new Scanner(System.in);
            int choice = townScan.nextInt();
            if(choice == 1){
                blacksmith(playerChar);
            }else if (choice == 2) {
                inn(playerChar, input, output, isServer);
            }else if(choice == 3){
            	prepareToLeave(playerChar, input, output, isServer);
            }else{
                System.out.println("Please make a valid choice");
            }

        }
    }

    public static void blacksmith(Character playerChar){
        System.out.println("\nWelcome to the forge!\n");
        boolean atTheForge = true;
        while(atTheForge) {
            System.out.println("You are at the forge!");
            System.out.println("What would you like to buy?");
            System.out.println("1) Test Sword");
            System.out.println("2) Test Armor");
            System.out.println("3) Leave the forge");
            System.out.print("> ");
            Scanner theShop = new Scanner(System.in);
            int choice = theShop.nextInt();
            if(choice == 1){
                Weapon testSword = new Weapon("Test Sword", 5);
                System.out.println(testSword.getName());
                if(playerChar.isInventoryFull()){
                    System.out.println("No room in inventory");
                }else{
                    playerChar.getInventory().add(testSword);
                    System.out.println("You have purchased a Test Sword");

                }

            }else if(choice == 2) {
                Armor testArmor = new Armor("Test Armor", 3);
                if (playerChar.isInventoryFull()) {
                    System.out.println("No room in inventory");
                } else {
                    playerChar.getInventory().add(testArmor);
                    System.out.println("You have purchased a Test Armor");

                }
            }else if(choice == 3){
                System.out.println("You leave the forge");
                atTheForge = false;
            }else{
                System.out.println("Please make a valid choice");
            }

        }

    }

    public static void hangout(Character playerChar, DataInputStream input, DataOutputStream output, boolean isServer){
        System.out.println("Waiting for your friend...");              
        try {           
            if(isServer){ //SERVER---------------------------------------
                output.writeUTF("hey");
                String player2 = input.readUTF();
                output.writeUTF(playerChar.getName());
                System.out.println(player2 + " has entered!");
	            System.out.println("What would you like to do?");
                System.out.println("1) Play Dice");
                System.out.println("2) Just roll dice for fun!");
                System.out.print("> ");
                Scanner menu = new Scanner(System.in);
                int menuChoice = menu.nextInt();
                output.writeInt(menuChoice);
                
                if(menuChoice == 1){
                	System.out.println("Ready to roll the dice? Guess if the number will be over or under 10!");
                	System.out.println("Over or under?");
	                System.out.print("> ");
	                Scanner gameChoice = new Scanner(System.in);
	                String choice = gameChoice.next();
	                input.readUTF();	
	                int dieRoll = Dice.roll(20);
	                output.writeInt(dieRoll);
	                System.out.println("The die rolled a " + dieRoll);
	                if(dieRoll > 10 && choice.equalsIgnoreCase("over") || dieRoll < 10 && choice.equalsIgnoreCase("under")){
	                	System.out.println("Correct!");
	                }else{
	                	System.out.println("WRONG!");
	                }
	                
                }else if(menuChoice == 2){
                	System.out.println("You chose to roll a bunch of dice!");
                	System.out.println("How many dice do you want to roll?");
                	Scanner diceRoll = new Scanner(System.in);
                	System.out.print("> ");
                	int numDice = diceRoll.nextInt();
                	output.writeInt(numDice);
                	System.out.println("Great! How many sides do you want on the dice?");
                	System.out.print("> d");
                	int diceSides = diceRoll.nextInt();
                	output.writeInt(diceSides);
                	for(int idx=0; idx<numDice; idx++){
                		int roll = Dice.roll(diceSides);
                		output.writeInt(roll);
                		System.out.println((idx+1) + ") " + roll);
                	}
                	
                	
                }
                
                
                
            }else{ //CLIENT-----------------------------------------------
                input.readUTF();           
                output.writeUTF(playerChar.getName());
                String player2 = input.readUTF();
                System.out.println(player2 + " has entered!");
                System.out.println("For now, only the host decides what to do. Please Wait.");
                int menuChoice = input.readInt();
                
                if(menuChoice == 1){
                	System.out.println("Your frind chose to play dice!");
                	System.out.println("Ready to roll the dice? Guess if the number will be over or under 10!");
	                System.out.println("Over or under?");
	                System.out.print("> ");
	                Scanner gameChoice = new Scanner(System.in);
	                String choice = gameChoice.next();
	                output.writeUTF("Picked!");
	                int dieRoll = input.readInt();
	                System.out.println("The die rolled a " + dieRoll);
	                if(dieRoll > 10 && choice.equalsIgnoreCase("over") || dieRoll < 10 && choice.equalsIgnoreCase("under")){
	                	System.out.println("Correct!");
	                }else{
	                	System.out.println("WRONG!");
	                }
                }
                
                if(menuChoice == 2){
                	System.out.println("Your frind chose to roll a bunch of random dice! Because reasons.");
                	int numDice = input.readInt();
                	int numSides = input.readInt();
                	System.out.println("Rolling " + numDice + "d" + numSides);
                	for(int idx=0; idx<numDice; idx++){
                		System.out.println((idx+1) + ") " + input.readInt());
                	}

                }
                
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void inn(Character playerChar, DataInputStream input, DataOutputStream output, boolean isServer){
        System.out.println("\nWelcome to the Inn!");
        boolean inTheInn = true;
        while (inTheInn) {
            System.out.println("You are at the Inn!");
            System.out.println("What would you like to do?");
            System.out.println("1) Have a bite to eat");
            System.out.println("2) Save the game");
            System.out.println("3) Check your gear");
            System.out.println("4) Hang out with a friend");
            System.out.println("5) Leave the inn");
            Scanner theInn = new Scanner(System.in);
            System.out.print("> ");
            int saveChoice = theInn.nextInt();
            if(saveChoice == 1){
                System.out.println("\nYou feel refreshed. Your health is restored\n");
                playerChar.setHealth(playerChar.getMaxHealth());
            }else if(saveChoice == 2) {
                System.out.println("\nYou saved the game!\n");
            }else if(saveChoice == 3){
                playerChar.getMenu();
                for(int idx = 0; idx < playerChar.getInventory().size(); idx++){
                    Item curr = playerChar.getInventory().get(idx);
                    System.out.printf("%s : %d : %d\n",curr.getName(),curr.getiClass(),curr.getValue());
                }
            }else if(saveChoice == 4){
            	hangout(playerChar, input, output, isServer);
        	}else if(saveChoice == 5){
                System.out.println("\nYou leave the inn\n");
                inTheInn = false;
            }else{
                System.out.println("\nPlease make a valid choice\n");
            }
        }
    }

    public static void prepareToLeave(Character playerChar, DataInputStream input, DataOutputStream output, boolean isServer){
        System.out.println("Waiting for your friend...");
        try {
        	if(isServer){ //SERVER--------------------------------------------------        		        	
                output.writeUTF("hey");
                output.writeUTF(playerChar.getName());
                String player2name = input.readUTF();
                System.out.println(player2name + " has entered!");
                //For now, hand make encounter for testing purposes
                //Step 2: RNG monsters
                //Step 3: Zones
                
                Enemy kobold = new Enemy("Kobold", "Adventurer Folder", 5, 5, 9);
                Enemy mouse = new Enemy("Mouse", "It's a mouse.", 3, 5, 7);
                Fighter player2 = new Fighter(input.readUTF(), input.readInt(), input.readInt(), true);
                LinkedList<Fighter> unsorted = new LinkedList<>();
                unsorted.add(kobold);
                unsorted.add(mouse);
                unsorted.add(player2);
                unsorted.add(playerChar);
                LinkedList<Fighter> sorted = Combat.turnOrder(unsorted);
                Combat.combatServer(playerChar, sorted, input, output);
                
                
                
                
                
        	}else{ //CLIENT---------------------------------------------------------
                input.readUTF();
                String player2name = input.readUTF();
                output.writeUTF(playerChar.getName());
                System.out.println(player2name + " has entered!");
                output.writeUTF(playerChar.getName());
                output.writeInt(playerChar.getAgility());
                output.writeInt(playerChar.getHealth());

                Combat.combatClient(input, output);
        		
        	}

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
