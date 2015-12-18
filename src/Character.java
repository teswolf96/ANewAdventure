import java.util.ArrayList;

/**
 * Created by archlord on 10/5/15.
 */
public class Character implements CombatAble{
    private String name;
    private String charClass;

    private ArrayList<Item> inventory;

    private int health;
    private int maxhealth;
    private int dmg;

    private int str;
    private int strxp;

    private int agl;
    private int aglxp;

    private int intl;
    private int intlxp;

    private int cha;
    private int chaxp;

    private int wis;
    private int wisxp;

    private int con;
    private int conxp;

    public Character(String newName, String newCharClass, int newHealth, int newDmg){
        name = newName;
        charClass = newCharClass;
        maxhealth = newHealth;
        health = maxhealth;
        dmg = newDmg;
        str = 5;
        strxp = 0;
        agl=5;
        aglxp=0;
        intl=5;
        intlxp=0;
        cha=5;
        chaxp=0;
        wis=5;
        wisxp=0;
        con=5;
        conxp=0;
        inventory = new ArrayList<Item>();

    }

    public String getName(){
        return name;
    }
    
    public void getTurn(){
    	return;
    }

    public String getCharClass() { return charClass; }

    public void setCharClass(String newClass) { charClass = newClass; }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth() { return maxhealth; }

    public int getDmg() { return dmg; }

    public void setHealth(int newHealth){
        health = newHealth;
    }

    public void setMaxhealth(int newMax) { maxhealth = newMax; }

    //Str
    public int getStr() { return str;  }
    public void setStr(int newStr){ str= newStr; }
    public int getStrXp(){ return strxp; }
    public void setStrXp(int newStrXp){ strxp = newStrXp; }
    public void addStrXp(int addXp){ strxp+=addXp; }
    //Agl
    public int getAgl() { return agl; }
    public void setAgl(int newAgl){ agl = newAgl; }
    public int getAglXp(){ return aglxp; }
    public void setAglXp(int newAglXp){ aglxp = newAglXp; }
    public void addAglXp(int addXp){ aglxp+=addXp; }
    //Cha
    public int getCha() { return cha; }
    public void setCha(int newCha){ cha = newCha; }
    public int getChaXp(){ return chaxp; }
    public void setChaXp(int newChaXp){ chaxp = newChaXp; }
    public void addChaXp(int addXp){ chaxp+=addXp; }
    //Intl
    public int getIntl() { return intl; }
    public void setIntl(int newIntl){ intl = newIntl;  }
    public int getIntlXp(){ return intlxp; }
    public void setIntlXp(int newIntlXp){ intlxp = newIntlXp; }
    public void addIntlXp(int addXp){ intlxp+=addXp; }
    //Wis
    public int getWis() { return wis; }
    public void setWis(int newWis){ wis = newWis; }
    public int getWisXp(){ return wisxp; }
    public void setWisXp(int newWisXp){ wisxp = newWisXp; }
    public void addWisXp(int addXp){ wisxp+=addXp; }
    //Con
    public int getCon() { return con; }
    public void setCon(int newCon){ con = newCon; }
    public int getConXp(){ return conxp; }
    public void setConXp(int newConXp){ conxp = newConXp; }
    public void addConXp(int addXp){ conxp+=addXp; }

    //Inventory
    public ArrayList<Item> getInventory() { return inventory; }

    public boolean isInventoryFull(){
        if(inventory.size() == 1+str)
            return true;
        return false;
    }
    
    
    public String getMenu(){
        String printMe = new String();
        printMe += (name + "  -  " + health + " hp\n");
        printMe += (charClass + "\n");
        printMe += ("Stats: \n");
        printMe += ("\tStrength: " + str + "\n");
        printMe += ("\tAgitlity: " + agl + "\n");
        printMe += ("\tIntelligence: " + intl + "\n");
        printMe += ("\tCharisma: " + cha + "\n");
        printMe += ("\tWisdom: " + wis + "\n");
        printMe += ("\tConstitution: " + con + "\n");

        return printMe;

    }

}

