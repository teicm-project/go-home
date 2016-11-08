package makisp.gohome;

/**
 * Created by Stergios Tselios on 5/11/2016.
 */

public class Invetory {
    private int id;
    private String activeuser;
    private String item;

    ///// Άδειος κατασκευάστης /////
    public Invetory(){

    }

    ///// Κατασκευάστης /////
    public Invetory(int id, String username, String item){
        this.id = id;
        this.activeuser = activeuser;
        this.item = item;
    }

    ///// Κατασκευάστης /////
    public Invetory(String activeuser, String item){
        this.activeuser = activeuser;
        this.item = item;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String activeuser){
        this.activeuser = activeuser;
    }

    public void setPassword(String item){
        this.item = item;
    }

    public int getId(){
        return id;
    }

    public String getActiveuser(){
        return activeuser;
    }

    public String getPassword(){
        return item;
    }
}
