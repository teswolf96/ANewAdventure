/**
 * Created by archlord on 10/7/15.
 */
public class Location {
    private String name;
    private String descrip;

    public Location(String lName, String lDescrip){
        //TODO: This should be automated from a text file
        name = lName;
        descrip = lDescrip;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getDescrip(){
    	return descrip;
    }

}
