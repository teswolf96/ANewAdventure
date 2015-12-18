
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Name: Thomas Shaw
 */
//Moved to github
public class MainGameClass {
    public static void main(String[] args) {
        System.out.println("Welcome to A New Adventure!");
        Boolean advance = false;
        int mainMenuChoice;
        do {
            System.out.println("Main Menu");
            System.out.println("----------------------");
            System.out.println("1) New Character");
            System.out.println("2) Load Character");
            System.out.print("> ");
            Scanner mainMenu = new Scanner(System.in);
            mainMenuChoice = mainMenu.nextInt();
            if (mainMenuChoice == 1 || mainMenuChoice == 2)
                advance = true;
            else
                System.out.println("Please choose a valid option");
        } while (!advance);

        Character playerChar = null;
        if (mainMenuChoice == 1) {
            playerChar = createChar();
            saveChar(playerChar);
        } else if (mainMenuChoice == 2) {
            System.out.println("Character name to load?");
            System.out.print("> ");
            Scanner loadName = new Scanner(System.in);
            String charName = loadName.next();
            playerChar = loadChar(charName);
        }
        if(playerChar == null)
            return;

        System.out.println("Client or server?");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("client")) {
            //CLIENT-----------------------------------
            //System.out.println("Client!");
            Socket myClient;
            DataInputStream input;
            DataOutputStream output;
            Scanner getIP = new Scanner(System.in);
            System.out.print("Server IP: ");
            String ipAddr = getIP.next();
            try {
                myClient = new Socket(ipAddr, 6666);
                input = new DataInputStream((myClient.getInputStream()));
                output = new DataOutputStream(myClient.getOutputStream());

                output.writeUTF(playerChar.getName());
                output.write(playerChar.getHealth());
                //STUFF!
                boolean whileInGame = true;
                while(whileInGame){
                    String command = input.readUTF();
                    if(command.equalsIgnoreCase("quit"))
                        whileInGame = false;
                    else if(command.equalsIgnoreCase("message"))
                        System.out.println(input.readUTF());
                    else if(command.equalsIgnoreCase("reply")){
                        System.out.print("Message: ");
                        Scanner msg = new Scanner(System.in);
                        output.writeUTF(msg.nextLine());
                    }
                    else if(command.equalsIgnoreCase("combat")){
                        Combat.fightSceneClient(input,output,playerChar);
                    }
                    else if(command.equalsIgnoreCase("heal")){
                        playerChar.setHealth(input.read());
                        System.out.println("You have been healed! You have " + playerChar.getHealth() + " HP!");
                    }
                    else if(command.equalsIgnoreCase("printstats")){
                        System.out.print(playerChar.getMenu());
                    }else if(command.equalsIgnoreCase("town")){
                        Town.townMenu(playerChar, input, output);
                    }


                }

                input.close();
                output.close();
                myClient.close();

            } catch (IOException e) {
                System.out.println(e);
            }

        } else if (choice.equalsIgnoreCase("server")) {
            //SERVER----------------------------------------
            System.out.println("Waiting for client to connect...");
            DataInputStream input;
            DataOutputStream output;
            try {
                ServerSocket socket = new ServerSocket(6666);
                Socket clientSocket = socket.accept();
                input = new DataInputStream(clientSocket.getInputStream());
                output = new DataOutputStream(clientSocket.getOutputStream());

                //LOAD PLAYER 2
                String player2Name = input.readUTF();
                int player2Health = input.read();
                Character player2 = new Character(player2Name, "temp", player2Health, 10);

                System.out.println("Player " + player2.getName() + " has joined the game!");

                boolean whileInGame = true;
                while(whileInGame) {
                    output.writeUTF("town");
                    Town.townMenu(playerChar, input, output);
                    /*
                    System.out.print("> ");
                    Scanner getCom = new Scanner(System.in);
                    String testCommand = getCom.next();
                    if(testCommand.equalsIgnoreCase("hello")) {
                        System.out.println("You greeted the client");
                        sendText("Greetings from the Server", output);
                    } else if (testCommand.equalsIgnoreCase("quit")){
                        output.writeUTF("quit");
                        whileInGame = false;
                    } else if (testCommand.equalsIgnoreCase("greetme")){
                        output.writeUTF("reply");
                        System.out.println(input.readUTF());
                    } else if (testCommand.equalsIgnoreCase("combat")){
                        output.writeUTF("combat");
                        Enemy testMonster = new Enemy("Kobold", "Adveturer Fodder", 5, 50);
                        Combat.fightSceneServer(input, output, playerChar, player2, testMonster);
                    }
                    else if (testCommand.equalsIgnoreCase("heal")){
                        System.out.print("New Health: ");
                        Scanner getNewHealth = new Scanner(System.in);
                        int newHealth = getNewHealth.nextInt();
                        playerChar.setHealth(newHealth);
                        System.out.println("You have been healed! Your new health is " + newHealth);
                        output.writeUTF("heal");
                        output.write(newHealth);
                    }
                    else if (testCommand.equalsIgnoreCase("printstats")){
                        output.writeUTF("printstats");
                        System.out.print(playerChar.getMenu());
                    }else if (testCommand.equalsIgnoreCase("town")){
                        output.writeUTF("town");
                        Town.townMenu(playerChar);
                    }
                    */

                }
                output.close();
                input.close();
                socket.close();
                clientSocket.close();
            } catch (java.io.IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("INVALID!");
        }
    } //END MAIN FUNCTION


    public static void saveChar(Character charToSave) {
        String fileName = charToSave.getName();
        String line = null;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(charToSave.getName());
            bufferedWriter.newLine();
            bufferedWriter.write(charToSave.getCharClass());
            bufferedWriter.newLine();
            int charHealth = charToSave.getHealth();
            int charDmg = charToSave.getDmg();
            bufferedWriter.write(String.format("%d, %d",charHealth,charDmg));
            bufferedWriter.newLine();
            //Set up the variables to save
            int str = charToSave.getStr();
            int agl = charToSave.getAgl();
            int intl = charToSave.getIntl();
            int wis = charToSave.getWis();
            int cha = charToSave.getCha();
            int con = charToSave.getCon();

            bufferedWriter.write(String.format("%d, %d, %d, %d, %d, %d", str, agl,intl,wis,cha,con));
            bufferedWriter.newLine();

            ArrayList<Item> saveMe = charToSave.getInventory();
            for(int idx=0;idx < saveMe.size();idx++){
                    Item saveWeap = saveMe.get(idx);
                    bufferedWriter.write(String.format("%s, %d, %d",saveWeap.getName(), saveWeap.getiClass(),saveWeap.getValue()));
            }

            bufferedWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }
    } //END SAVECHAR FUNCTION

    public static Character loadChar(String charName) {
        String fileName = charName;
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String name = bufferedReader.readLine();
            String charClass = bufferedReader.readLine();
            Scanner readLine = new Scanner(bufferedReader.readLine()).useDelimiter(", ");
            int health = readLine.nextInt();
            int dmg = readLine.nextInt();
            readLine.close();
            Character loadChar = new Character(name, charClass, health, dmg);
            //load in stats
            Scanner readStats = new Scanner(bufferedReader.readLine()).useDelimiter(", ");
            loadChar.setStr(readStats.nextInt());
            loadChar.setAgl(readStats.nextInt());
            loadChar.setIntl(readStats.nextInt());
            loadChar.setWis(readStats.nextInt());
            loadChar.setCha(readStats.nextInt());
            loadChar.setCon(readStats.nextInt());

            bufferedReader.close();
            return loadChar;

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }

        return null;
    } //END LOADCHAR

    public static void sendText(String text, DataOutputStream output){
        try {
            output.writeUTF("message");
            output.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //SENDS A MESSAGE


    public static Character createChar(){
        System.out.println("Welcome to character creation!");
        System.out.println("What is your character's name?");
        System.out.print(("> "));
        Scanner makeChar = new Scanner(System.in);
        String charName = makeChar.next();
        int health = 10;
        Character playerChar = new Character(charName, "temp", health, 10);
        boolean pickedClass = false;
        do {
            System.out.println("Choose a character class: ");
            System.out.println("1) Warrior");
            System.out.println("2) Assassin");
            System.out.print(("> "));
            int classChoice = makeChar.nextInt();
            if(classChoice == 1){
                playerChar.setCharClass("Warrior");
                playerChar.setStr(7);
                playerChar.setCon(7);
                playerChar.setIntl(3);
                playerChar.setAgl(3);
                pickedClass = true;
            } else if (classChoice == 2){
                playerChar.setCharClass("Assassin");
                playerChar.setAgl(7);
                playerChar.setWis(7);
                playerChar.setCha(3);
                playerChar.setStr(3);
                pickedClass = true;
            }else{
                System.out.println("Please choose a valid class");
            }
        }while(!pickedClass);


        //set class here
        playerChar.setHealth(10 + playerChar.getCon()-5);
       return playerChar;
    }

}

