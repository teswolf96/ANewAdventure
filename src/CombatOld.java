import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Name: Thomas Shaw
 */
public class CombatOld {

    public static void fightSceneServer(DataInputStream input, DataOutputStream output, Character local, Character remote, Enemy monster){
        boolean inFight = true;
        while(inFight) {
            try {
                String scene = monster.printMonster();
                System.out.println(scene);
                sendScene(output, scene);
                //For Testing, Server goes first
                local.getMenu();
                Scanner getTurn = new Scanner(System.in);
                int yourCom = getTurn.nextInt();
                if (yourCom == 1) {
                    int dmg = local.getDmg();
                    System.out.println("You hit the " + monster.getName() + " for " + dmg + " damage!");
                    sendAction(output, local.getName() + " hit the " + monster.getName() + " for " + dmg + " damage!");
                    monster.modHealth(-dmg);
                    if(monster.getHealth()<=0){
                        String finMessage = ("You have defeated the " + monster.getName() + "!\n");
                        System.out.println(finMessage);
                        combatOver(output, finMessage);
                        inFight=false;
                        break;
                    }

                }

                //Next let the monster go

                int monDmg = monster.getDmg(); //this will eventually be randomish
                Character hitPlayer = getMaxHealth(local, remote);
                hitPlayer.setHealth(hitPlayer.getHealth()-monDmg);
                if(hitPlayer.equals(remote)){
                    sendHit(output, hitPlayer.getHealth());
                }
                String monAttack = (monster.getName() + " hit " + hitPlayer.getName() + " for " + monDmg);
                System.out.println(monAttack);
                sendAction(output, monAttack);

                if(monster.getHealth()<=0){
                    String finMessage = ("You have defeated the " + monster.getName() + "!\n");
                    System.out.println(finMessage);
                    combatOver(output, finMessage);
                    inFight=false;
                    break;
                }



                //Finally player2 goes

                output.writeUTF("yourturn");
                String move = input.readUTF();
                if(move.equalsIgnoreCase("attack")) {
                    int dmg = input.read();
                    String theirMove = (remote.getName() + " hit the " + monster.getName() + " for " + dmg + " health!");
                    monster.modHealth(-dmg);
                    System.out.println(theirMove);
                    output.writeUTF(theirMove);
                }



            }catch (java.io.IOException e){
                System.out.println(e + "Problem!");
            }
        }

    }

    public static void fightSceneClient(DataInputStream input, DataOutputStream output, Character local){
        try {
            boolean inFight = true;
            while(inFight){
                String getCom = input.readUTF();
                if(getCom.equalsIgnoreCase("exit")){
                    inFight=false;
                }
                else if(getCom.equalsIgnoreCase("scene")){
                    System.out.printf(input.readUTF());
                }
                else if(getCom.equalsIgnoreCase("action")){
                    System.out.println(input.readUTF());
                }
                else if(getCom.equalsIgnoreCase("hit")){
                    local.setHealth(input.read());
                }
                else if(getCom.equalsIgnoreCase("yourturn")){
                    local.getMenu();
                    Scanner yourMove = new Scanner(System.in);
                    int yourCom = yourMove.nextInt();
                    if (yourCom == 1) {
                        int dmg = local.getDmg();
                        output.writeUTF("attack");
                        output.write(dmg);
                        System.out.println(input.readUTF());
                    }

                }
                else if(getCom.equalsIgnoreCase("finished")){
                    inFight = false;
                    System.out.println(input.readUTF());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendScene(DataOutputStream output, String scene){
        try {
            output.writeUTF("scene");
            output.writeUTF(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendAction(DataOutputStream output, String action){
        try {
            output.writeUTF("action");
            output.writeUTF(action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Character getMaxHealth(Character p1, Character p2){
        if(p1.getHealth()>p2.getHealth())
            return p1;
        else
            return p2;
    }

    public static void sendHit(DataOutputStream output, int newHealth){
        try {
            output.writeUTF("hit");
            output.write(newHealth);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void combatOver(DataOutputStream output, String finMessage){
        try {
            output.writeUTF("finished");
            output.writeUTF(finMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

