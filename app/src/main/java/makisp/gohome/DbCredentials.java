package makisp.gohome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;

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



    public DbCredentials(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    ///// Δημιουργία πίνακα /////
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CRIDENTIAL_TABLE = "CREATE TABLE " + TABLE_CREDENTIALS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_CRIDENTIAL_TABLE);

    }

    ///// Αναβάθμιση βάσης /////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ///// Διαγραφή παλίου πίνακα αν υπάρχει /////
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);

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

        ///// Εισαγωγή γραμμής /////
        db.insert(TABLE_CREDENTIALS, null, values);
        db.close();
    }


    ///// Επιστροφή ενός συγκεκριμένου χρήστη /////
    public Credential getCredential(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CREDENTIALS, new String[]{KEY_ID,
        KEY_USERNAME, KEY_PASSWORD}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Credential credential = new Credential(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return credential;
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
                credential.setId(Integer.parseInt(cursor.getString(0)));
                credential.setUsername(cursor.getString(1));
                credential.setPassword(cursor.getString(2));
                ///// Εισχώρηση χρηστών στην λίστα /////
                credentialList.add(credential);
            } while (cursor.moveToNext());
        }

        ///// Επιστροφή της λίστας των χρηστών /////
        return credentialList;
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

    ///// Ενημέρωση ενός χρήστη /////
    public int updateCredential(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, credential.getUsername());
        values.put(KEY_PASSWORD, credential.getPassword());

        ///// Ενημέρωση γραμμής /////
        return db.update(TABLE_CREDENTIALS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(credential.getId()) });
    }

    ///// Διαγραφή ενός χρήστη /////
    public void deleteCredential(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CREDENTIALS, KEY_ID + " = ?",
                new String[] { String.valueOf(credential.getId()) });
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


}
