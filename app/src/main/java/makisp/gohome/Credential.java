package makisp.gohome;

/**
 * Created by Ευάγγελος Πετρόπουλος on 3/11/2016.
 */

public class Credential {
    private int id;
    private String username;
    private String password;

    ///// Άδειος κατασκευάστης /////
    public Credential(){

    }

    ///// Κατασκευάστης /////
    public Credential(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    ///// Κατασκευάστης /////
    public Credential(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
