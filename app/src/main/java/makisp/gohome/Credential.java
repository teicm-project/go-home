package makisp.gohome;

/**
 * Created by Ευάγγελος Πετρόπουλος on 3/11/2016.
 */

public class Credential {
    private int id;
    private String username;
    private String password;
    private int progress;

    ///// Άδειος κατασκευάστης /////
    public Credential(){

    }

    ///// Κατασκευάστης /////
    public Credential(int id, String username, String password, int progress){
        this.id = id;
        this.username = username;
        this.password = password;
        this.progress = progress;
    }

    ///// Κατασκευάστης /////
    public Credential(String username, String password, int progress){
        this.username = username;
        this.password = password;
        this.progress = progress;
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

    public void setProgress(int progress){
        this.progress= progress;
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

    public int getProgress(){
        return progress;
    }
}
