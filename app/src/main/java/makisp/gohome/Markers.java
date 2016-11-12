package makisp.gohome;

/**
 * Created by Stamatis Dachretzis.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Markers {
    private int id;
    private double Latitude;
    private double Longitude;

    ///// Άδειος κατασκευάστης /////
    public Markers(){

    }

    ///// Κατασκευάστης /////
    public Markers(int id, double latitude, double longitude){
        this.id = id;
        this.Latitude = latitude;
        this.Longitude = longitude;
    }

    ///// Κατασκευάστης /////
    public Markers(double latitude, double longitude){
        this.Latitude = latitude;
        this.Longitude = longitude;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setLatitude (double latitude){
        this.Latitude = latitude;
    }

    public void setLongitude (double longitude){
        this.Longitude = longitude;
    }

    public int getId(){
        return id;
    }

    public double getLatitude(){
        return Latitude;
    }

    public double getLongitude(){
        return Longitude;
    }

}
