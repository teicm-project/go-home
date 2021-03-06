package makisp.gohome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stergios Tselios on 5/11/2016.
 */

public class DbInventory extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Inventory.db";
        private static final String TABLE_USERITEM = "UserItem";
        private static final String KEY_ID = "ID";
        private static final String KEY_ACTIVEUSER = "Activeuser";
        private static final String KEY_ITEM = "Item";


        public DbInventory(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        ///// Δημιουργία του πίνακα /////
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CREDENTIAL_TABLE = "CREATE TABLE "
                    + TABLE_USERITEM + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_ACTIVEUSER + " TEXT,"
                    + KEY_ITEM + " TEXT" + ")";
            db.execSQL(CREATE_CREDENTIAL_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            ///// Αν υπάρχει παλιός πίνακας τον διαγράφει /////
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERITEM);

            ///// Ξανα Δημιουργεί τον πίνακα /////
            onCreate(db);
        }

        public void addItem(Invetory credential) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            ///// Το όνομα του χρήστη /////
            values.put(KEY_ACTIVEUSER, credential.getActiveUser());
            ///// το item του χρηστη /////
            values.put(KEY_ITEM, credential.getItem());

            ///// Εισαγωγή γραμμής /////
            db.insert(TABLE_USERITEM, null, values);
            db.close();
        }

        public Invetory getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERITEM, new String[]{
                KEY_ID,
                KEY_ACTIVEUSER,
                KEY_ITEM}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Invetory invetory = new Invetory(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
        return invetory;
        }

        ///// Επιστροφή όλων των item /////
        public List<Invetory> getAllItems() {
            List<Invetory> itemsList = new ArrayList<Invetory>();
         ///// Επιλογή όλων/////
        String selectQuery = "SELECT  * FROM " + TABLE_USERITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ///// Επανάληψη σε όλες τις γραμμές και εισχωρηση items στην λίστα /////
        if (cursor.moveToFirst()) {
            do {
                Invetory inventory = new Invetory();
                inventory.setId(Integer.parseInt(cursor.getString(0)));
                inventory.setActiveUser(cursor.getString(1));
                inventory.setItem(cursor.getString(2));
                ///// Εισχώρηση χρηστών στην λίστα /////
                itemsList.add(inventory);
            } while (cursor.moveToNext());
        }

        ///// Επιστροφή της λίστας των item /////
        return itemsList;
        }

        ///// Επιστρέφει το πλήθος των item απο τους χρήστες /////
        public int getItemsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_USERITEM;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            ////// Επιστροφή του πλήθους των χρηστών /////
            return cursor.getCount();
        }

        ///// Ενημέρωση ενός item ενος χρήστη /////
        public int updateItem(Invetory invetory) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_ACTIVEUSER, invetory.getActiveUser());
            values.put(KEY_ITEM, invetory.getItem());

            ///// Ενημέρωση γραμμής /////
            return db.update(TABLE_USERITEM, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(invetory.getId()) });
        }

        ///// Διαγραφή ενός item απο εναν χρήστη /////
        public void deleteItem(Invetory invetory) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_USERITEM, KEY_ID + " = ?",
                    new String[] { String.valueOf(invetory.getId()) });
            db.close();
        }
    }



