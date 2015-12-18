/**
 * Created by archlord on 10/7/15.
 */
public class Enemy{
    private String name;
    private String descrip;
    private int dmg;
    private int health;
    private int agility;

    public Enemy(String newName, String newDescrip, int newDmg, int newHealth, int newAgil) {
        //TODO: Automate enemy creation from file
        name = newName;
        descrip = newDescrip;
        dmg = newDmg;
        health = newHealth;
        agility= newAgil;
    }

    public int getDmg(){
        return dmg;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){ return health; }

    public void modHealth(int modHealth){ health += modHealth;}

    public String getDescrip(){
        return descrip;
    }

    public int getAgility() { return agility; }

    public void setAgility(int newAgility) { agility = newAgility; }

    public String printMonster(){
        String returnMe = new String();
        returnMe += (name + "\n");
        returnMe += (descrip + "\n");
        returnMe += ("HP: " + health + "\n");
        return returnMe;
    }
}
