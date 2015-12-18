/**
 * Created by archlord on 11/12/15.
 */
public class Weapon extends Item{

    private String name;
    private int iClass;
    private int value;

    public Weapon(String newName, int newDmg){
        name = newName;
        iClass = 1;
        value = newDmg;
    }

    public int getValue(){
        return value;
    }

    public String getName() { return name; }

    public int getiClass() { return iClass; }

}