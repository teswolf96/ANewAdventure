/**
 * Created by archlord on 11/12/15.
 */
public class Item {
    private String name;
    private int iClass;
    private int value;

    public Item(){
        name = "cats";
        iClass = -1;
        value = 0;
    }

    public Item(String newName, int newiClass){
        name = newName;
        iClass = newiClass;
        value = 0;
    }

    public String getName(){
        return name;
    }

    public int getiClass(){
        return iClass;
    }

    public int getValue() { return value; }
}

