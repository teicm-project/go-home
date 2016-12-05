package makisp.gohome;

/**
 * Created by Evangelos on 2/12/2016.
 */

public class Inventory {
    private int id;
    private String activeUser;
    private String item;

    ///// Άδειος κατασκευάστης /////
    public Inventory(){

    }

    ///// Κατασκευάστης /////
    public Inventory(int id, String activeUser, String item){
        this.id = id;
        this.activeUser = activeUser;
        this.item = item;
    }

    ///// Κατασκευάστης /////
    public Inventory(int id, String item){
        this.id = id;
        this.item = item;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setActiveUser(String activeUser){
        this.activeUser = activeUser;
    }

    public void setItem(String item){
        this.item = item;
    }

    public int getId(){
        return id;
    }

    public String getActiveUser(){
        return activeUser;
    }

    public String getItem(){
        return item;
    }
}
