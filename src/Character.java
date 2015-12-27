import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by archlord on 10/5/15.
 */
public class Character extends Fighter {
    private String name;
    private String charClass;

    private ArrayList<Item> inventory;

    private int health;
    private int maxhealth;
    private int dmg;

    private int str;
    private int strxp;

    //private int agility;
    private int agilityxp;

    private int intl;
    private int intlxp;

    private int cha;
    private int chaxp;

    private int wis;
    private int wisxp;

    private int con;
    private int conxp;

    public Character(String newName, String newCharClass, int newHealth, int newDmg) {
        name = newName;
        charClass = newCharClass;
        maxhealth = newHealth;
        health = maxhealth;
        dmg = newDmg;
        str = 10;
        strxp = 0;
        agility = 10;
        agilityxp = 0;
        intl = 10;
        intlxp = 0;
        cha = 10;
        chaxp = 0;
        wis = 10;
        wisxp = 0;
        con = 10;
        conxp = 0;
        inventory = new ArrayList<>();
        isChar = true;

    }

    public String getName() {
        return name;
    }

    public void getTurn() {
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String newClass) {
        charClass = newClass;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxhealth;
    }

    public int getDmg() {
        return dmg;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setMaxhealth(int newMax) {
        maxhealth = newMax;
    }

    //Str
    public int getStr() {
        return str;
    }

    public void setStr(int newStr) {
        str = newStr;
    }

    public int getStrXp() {
        return strxp;
    }

    public void setStrXp(int newStrXp) {
        strxp = newStrXp;
    }

    public void addStrXp(int addXp) {
        strxp += addXp;
    }

    //Agility
    public int getAgility() {
        return agility;
    }

    public void setAgility(int newAgility) {
        agility = newAgility;
    }

    public int getAgilityXp() {
        return agilityxp;
    }

    public void setAgilityXp(int newAgilityXp) {
        agilityxp = newAgilityXp;
    }

    public void addAgilityXp(int addXp) {
        agilityxp += addXp;
    }

    //Cha
    public int getCha() {
        return cha;
    }

    public void setCha(int newCha) {
        cha = newCha;
    }

    public int getChaXp() {
        return chaxp;
    }

    public void setChaXp(int newChaXp) {
        chaxp = newChaXp;
    }

    public void addChaXp(int addXp) {
        chaxp += addXp;
    }

    //Intl
    public int getIntl() {
        return intl;
    }

    public void setIntl(int newIntl) {
        intl = newIntl;
    }

    public int getIntlXp() {
        return intlxp;
    }

    public void setIntlXp(int newIntlXp) {
        intlxp = newIntlXp;
    }

    public void addIntlXp(int addXp) {
        intlxp += addXp;
    }

    //Wis
    public int getWis() {
        return wis;
    }

    public void setWis(int newWis) {
        wis = newWis;
    }

    public int getWisXp() {
        return wisxp;
    }

    public void setWisXp(int newWisXp) {
        wisxp = newWisXp;
    }

    public void addWisXp(int addXp) {
        wisxp += addXp;
    }

    //Con
    public int getCon() {
        return con;
    }

    public void setCon(int newCon) {
        con = newCon;
    }

    public int getConXp() {
        return conxp;
    }

    public void setConXp(int newConXp) {
        conxp = newConXp;
    }

    public void addConXp(int addXp) {
        conxp += addXp;
    }

    //Inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean isInventoryFull() {
        return inventory.size() == ++str;
    }


    public void getMenu() {
        String printMe = "";
        printMe += (name + "  -  " + health + " hp\n");
        printMe += (charClass + "\n");
        printMe += ("Stats: \n");
        printMe += ("\tStrength: " + str + "\n");
        printMe += ("\tAgitlity: " + agility + "\n");
        printMe += ("\tIntelligence: " + intl + "\n");
        printMe += ("\tCharisma: " + cha + "\n");
        printMe += ("\tWisdom: " + wis + "\n");
        printMe += ("\tConstitution: " + con + "\n");
        printMe += ("Options: \n");
        printMe += ("\t1) Generic Attack\n");

        System.out.print(printMe);

        Scanner menu = new Scanner(System.in);
        if (menu.nextInt() == 1) {
            System.out.println("Choose a target: ");
                /*
                * This needs to be fixed
                * Should only loop through the list once
                * More is unnecessary
                */


        }

    }

}

