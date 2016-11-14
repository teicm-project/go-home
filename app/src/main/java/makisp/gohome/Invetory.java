package makisp.gohome;

/**
 * Created by Stergios Tselios on 5/11/2016.
 */

public class Invetory {
    private int id;
    private String activeUser;
    private String item;

    ///// Άδειος κατασκευάστης /////
    public Invetory(){

    }

    ///// Κατασκευάστης /////
    public Invetory(int id, String activeUser, String item){
        this.id = id;
        this.activeUser = activeUser;
        this.item = item;
    }

    ///// Κατασκευάστης /////
    public Invetory(String activeUser, String item){
        this.activeUser = activeUser;
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
