import sun.applet.Main;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by archlord on 10/17/15.
 */
public class Town {

    public static void townMenu(Character playerChar, DataInputStream input, DataOutputStream output){
        boolean inTown = true;
        System.out.println("Welcome to Town!");
        while(inTown) {
            System.out.println("\nYou are in Town!");
            System.out.println("What would you like to do?");
            System.out.println("1) Visit the blacksmith");
            System.out.println("2) Hangout with your friend");
            System.out.println("3) Stop by the inn");
            System.out.println("4) Leave town");
            System.out.print("> ");
            Scanner townScan = new Scanner(System.in);
            int choice = townScan.nextInt();
            if(choice == 1){
                blacksmith(playerChar);
            }else if (choice == 2){
                hangout(playerChar, input, output);
            }else if (choice == 3) {
                inn(playerChar);
            }else if(choice == 4){
                System.out.println("\nNo");
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

    public static void hangout(Character playerChar, DataInputStream input, DataOutputStream output){
        System.out.println("Waiting for your friend...");
        try {
            output.writeUTF("hey");
            input.readUTF();
            output.writeUTF(playerChar.getName());
            String player2 = input.readUTF();
            System.out.println(player2 + " has entered!");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void inn(Character playerChar){
        System.out.println("\nWelcome to the Inn!");
        boolean inTheInn = true;
        while (inTheInn) {
            System.out.println("You are at the Inn!");
            System.out.println("What would you like to do?");
            System.out.println("1) Have a bite to eat");
            System.out.println("2) Save the game");
            System.out.println("3) Check your gear");
            System.out.println("4) Leave the inn");
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
                System.out.println("\nYou leave the inn\n");
                inTheInn = false;
            }else{
                System.out.println("\nPlease make a valid choice\n");
            }
        }
    }

    public static void prepareToLeave(Character playerChar, DataInputStream input, DataOutputStream output){
        System.out.println("Waiting for your friend...");
        try {
                output.writeUTF("hey");
                input.readUTF();
                output.writeUTF(playerChar.getName());
                String player2 = input.readUTF();
                System.out.println(player2 + " has entered!");

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
