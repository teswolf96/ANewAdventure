/**
 * Created by archlord on 11/12/15.
 */
public class Armor extends Item{

    private String name;
    private int iClass;
    private int value;

    public Armor(String newName, int newAC){
        name = newName;
        iClass = 2;
        value = newAC;
    }

    public int getValue(){
        return value;
    }

    public String getName() { return name; }

    public int getiClass() { return iClass; }

}
