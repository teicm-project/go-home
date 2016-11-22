package makisp.gohome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ευάγγελος Πετρόπουλος on 3/11/2016.
 */

public class DbCredentials extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "CredentialsManager.db";
    private static final String TABLE_CREDENTIALS = "Credential";
    private static final String KEY_ID = "ID";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_PROGRESS = "Progress";

     // column for Table Markers

    private static final String TABLE_MARKERS = "Markers";
    private static final String ID = "ID";
    private static final String KEY_X = "Latitude";
    private static final String KEY_Y = "Longitude";



    public DbCredentials(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    ///// Δημιουργία πίνακα /////
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CRIDENTIAL_TABLE = "CREATE TABLE " + TABLE_CREDENTIALS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_PROGRESS + " TEXT"
                + ")";
        db.execSQL(CREATE_CRIDENTIAL_TABLE);

        String  CREATE_TABLE_MARKERS = "CREATE TABLE " + TABLE_MARKERS + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_X + " DOUBLE ,"
                + KEY_Y + " DOUBLE "
                + ")";
        db.execSQL(CREATE_TABLE_MARKERS);
    }

    ///// Αναβάθμιση βάσης /////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ///// Διαγραφή παλίου πίνακα αν υπάρχει /////
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKERS );

        ///// Δημιουργία πίνακα ξανά /////
        onCreate(db);
    }

    /**
     * Όλες CRUD(Create, Read, Update, Delete) Λειτουργίες
     */

    ///// Εισχώρηση νέου χρήστη /////
    public void addCredential(Credential credential){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        ///// Το όνομα του χρήστη /////
        values.put(KEY_USERNAME, credential.getUsername());
        ///// Ο κωδικός του χρήστη /////
        values.put(KEY_PASSWORD, credential.getPassword());
        ///// Ο κωδικός του χρήστη /////
        values.put(KEY_PROGRESS, credential.getProgress());

        ///// Εισαγωγή γραμμής /////
        db.insert(TABLE_CREDENTIALS, null, values);
        db.close();
    }


     ///// Εκχώρηση νέου Marker /////

    public void addMarkers(Markers markers){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        ///// Το όνομα του χρήστη /////

        values.put(KEY_X, markers.getLatitude());

        ///// Ο κωδικός του χρήστη /////

        values.put(KEY_Y, markers.getLongitude());

        ///// Εισαγωγή γραμμής  /////
        ///// Αν δεν υπάρχει στον πίνακα /////
        if(isMarkerExists(db, markers.getId()) == false) {
            db.insert(TABLE_MARKERS, null, values);
            db.close();
        }
    }


    ///// Επιστροφή ενός συγκεκριμένου χρήστη /////
    public Credential getCredential(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CREDENTIALS, new String[]{
                        KEY_ID,
                        KEY_USERNAME,
                        KEY_PASSWORD,
                        KEY_PROGRESS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Credential credential = new Credential(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        return credential;
    }


    ///// Επιστροφή ενός συγκεκριμένου Marker /////
    public Markers getMarkers(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MARKERS, new String[]{ID,
                        KEY_X, KEY_Y}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Markers markers = new Markers(Integer.parseInt(cursor.getString(0)),
                cursor.getDouble(1), cursor.getDouble(2));
        return markers;
    }

    ///// Επιστροφή όλων των χρηστών /////
    public List<Credential> getAllCredentials() {
        List<Credential> credentialList = new ArrayList<Credential>();
        ///// Eρώτημα SQL για επιλογή όλων /////
        String selectQuery = "SELECT  * FROM " + TABLE_CREDENTIALS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ///// Επανάληψη σε όλες τις γραμμές και εισχωρηση στην λίστα /////
        if (cursor.moveToFirst()) {
            do {
                Credential credential = new Credential();
                credential.setId(cursor.getInt(0));
                credential.setUsername(cursor.getString(1));
                credential.setPassword(cursor.getString(2));
                credential.setProgress(cursor.getInt(3));
                ///// Εισχώρηση χρηστών στην λίστα /////
                credentialList.add(credential);
            } while (cursor.moveToNext());
        }

        ///// Επιστροφή της λίστας των χρηστών /////
        return credentialList;
    }




    ///// Επιστροφή όλων των Markers /////

    public List<Markers> getAllMarkers() {
        List<Markers> markersList = new ArrayList<Markers>();

        ///// Eρώτημα SQL για επιλογή όλων /////

        String selectQuery = "SELECT  * FROM " + TABLE_MARKERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ///// Επανάληψη σε όλες τις γραμμές και εισχωρηση στην λίστα /////

        if (cursor.moveToFirst()) {
            do {
                Markers markers = new Markers();
                markers.setId(Integer.parseInt(cursor.getString(0)));
                markers.setLatitude(Double.parseDouble(String.valueOf(cursor.getDouble(1))));
                markers.setLongitude(Double.parseDouble(String.valueOf(cursor.getDouble(2))));

                ///// Εκχώρηση των Markers στην λίστα /////

                markersList.add(markers);
            } while (cursor.moveToNext());
        }

        ///// Επιστροφή της λίστας των Markers /////

        return markersList;
    }





    ///// Επιστρέφει το πλήθος των χρηστών /////
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CREDENTIALS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        ////// Επιστροφή του πλήθους των χρηστών /////
        return cursor.getCount();
    }




    ///// Επιστρέφει το πλήθος των Markers /////

    public int getMarkersCount() {
        String markerQuery = "SELECT  * FROM " + TABLE_MARKERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(markerQuery, null);
        cursor.close();

        ////// Επιστροφή του πλήθους των χρηστών /////

        return cursor.getCount();
    }




    ///// Ενημέρωση ενός χρήστη /////
    public int updateCredential(Credential credential, String paok) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, credential.getUsername());
        values.put(KEY_PASSWORD, credential.getPassword());

        ///// Ενημέρωση γραμμής /////
        return db.update(TABLE_CREDENTIALS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(credential.getId()) });
    }

    ///// Ενημέρωση τη πρόοδος του χρήστη /////
    public int updateProgress(Credential credential, String activeUser, int i) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROGRESS, i);

        ///// Ενημέρωση γραμμής /////
        return db.update(TABLE_CREDENTIALS, values, KEY_USERNAME + " = ?",
                new String[]{ activeUser });
    }


    ///// Ενημέρωση ενός Marker /////

    public int updateMarkers(Markers markers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_X, markers.getLatitude());
        values.put(KEY_Y, markers.getLongitude());

        ///// Ενημέρωση γραμμής /////

        return db.update(TABLE_MARKERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(markers.getId()) });
    }





    ///// Διαγραφή ενός χρήστη /////
    public void deleteCredential(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CREDENTIALS, KEY_ID + " = ?",
                new String[] { String.valueOf(credential.getId()) });
        db.close();
    }


    ///// Διαγραφή ενός Marker /////

    public void deleteMarkers(Markers markers) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MARKERS, KEY_ID + " = ?",
                new String[] { String.valueOf(markers.getId()) });
        db.close();
    }





    // Created by kevintso 20  11-4-2016
    public String SearchUsername(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String  query = "select Username from " +TABLE_CREDENTIALS;


        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "not found;";
        if(cursor.moveToFirst())
        {
            do {
                    a = cursor.getString(0);

                    if(a.equals(username))
                    {
                        b = cursor.getString(0);
                        break;
                    }
            }while(cursor.moveToNext());
        }
    return b;
    }

    ///// Έλεγχος αν το σημάδι υπάρχει επιστρέφη αληθές ή ψευδές /////
    public boolean isMarkerExists(SQLiteDatabase db, int id) {

        ///// SQL ερώτημα αν το σημάδι υπάρχει μέσα στον πίνακα /////
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MARKERS
                + " WHERE " + ID + " = " + id , null);
        ///// Αν ο δείκτης είναι μεγάλυτερος το 0 σημαίνει οτι υπάρχει /////
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }


}
