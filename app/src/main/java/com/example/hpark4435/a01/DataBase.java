package com.example.hpark4435.a01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sosno on 2018-03-13.
 */

public class DataBase {


    // database constants
    public static final String DB_NAME = "Grocery.db";
    public static final int    DB_VERSION = 1;



    //Store table constants
    public static final String STORE_TABLE = "Store";

    public static final String STORE_ID = "StoreID";
    public static final int    STORE_ID_COL = 0;

    public static final String STORE_NAME = "StoreName";
    public static final int    STORE_NAME_COL = 1;

    public static final String STORE_URL = "URL";
    public static final int    STORE_URL_COL = 2;



    // product table constants
    //Table 1 : Kepp track of grocery list items
    public static final String PRODUCT_TABLE = "Product";

    public static final String PRODUCT_ID = "ItemID";
    public static final int    PRODUCT_ID_COL = 0;

    public static final String PLIST_ID = "ListID"; //foreign key
    public static final int    PLIST_ID_COL = 1;

    public static final String PRODUCT_NAME = "ItemName";
    public static final int    PRODUCT_NAME_COL = 2;

    public static final String PRODUCT_QUANTITY = "Quantity";
    public static final int    PRODUCT_QUANTITY_COL = 3;

    public static final String PRODUCT_CHECK = "isChecked";
    public static final int    PRODUCT_CHECK_COL = 4;


    // CREATE and DROP TABLE statements
    public static final String CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + PRODUCT_TABLE + " (" +
                    PRODUCT_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLIST_ID     + " INTEGER FOREIGN KEY NOT NULL, " +
                    PRODUCT_NAME + " TEXT    NOT NULL UNIQUE, "     +
                    PRODUCT_QUANTITY + " INTEGER     NOT NULL, " +
                    PRODUCT_CHECK +" BOOLEAN    NOT NULL)";

    public static final String DROP_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + PRODUCT_TABLE;


    // CREATE and DROP TABLE statements
    public static final String CREATE_STORE_TABLE =
            "CREATE TABLE [IF NOT EXISTS]" + STORE_TABLE + " (" +
                    STORE_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STORE_NAME     + "TEXT NOT NULL, " +
                    STORE_URL +" TEXT    NOT NULL)";

    public static final String DROP_STORE_TABLE =
            "DROP TABLE IF EXISTS " + STORE_TABLE;





    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_PRODUCT_TABLE);
            db.execSQL(CREATE_STORE_TABLE);
            //db.execSQL(CREATE_TASK_TABLE);

            // insert default lists
            //db.execSQL("INSERT INTO list VALUES (0, 'Apples', 3)");
            //db.execSQL("INSERT INTO list VALUES (1, 'Carrots', 7)");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(DataBase.DROP_PRODUCT_TABLE);
            db.execSQL(DataBase.DROP_STORE_TABLE);
            onCreate(db);
        }
    }


    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public DataBase(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }


    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }



    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }


    public GrocerySingleton getTask(int id) {
        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        GrocerySingleton task = getTaskFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        return task;
    }

    private static GrocerySingleton getTaskFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                GrocerySingleton task = new GrocerySingleton(
                        cursor.getInt(PRODUCT_ID_COL),
                        cursor.getInt(PLIST_ID_COL),
                        cursor.getString(PRODUCT_NAME_COL),
                        cursor.getInt(PRODUCT_QUANTITY_COL),
                        cursor.getInt(PRODUCT_CHECK_COL));
                return task;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    public long insertTask(GroceryItem task) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_ID, task.getItemID());
        cv.put(PLIST_ID, task.getListID());
        cv.put(PRODUCT_NAME, task.getItemName());
        cv.put(PRODUCT_QUANTITY, task.getItemQuantity());
        cv.put(PRODUCT_CHECK, task.isChecked());


        this.openWriteableDB(); //Crashes here
        long rowID = db.insert(PRODUCT_TABLE, null, cv);
        this.closeDB();
        return rowID;


    }

    public int updateTask(GrocerySingleton task) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_ID, task.getItemID());
        cv.put(PRODUCT_NAME, task.getItemName(task.getItemID()));
        cv.put(PRODUCT_QUANTITY, task.getQunatity(task.getItemID()));



        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { String.valueOf(task.getNumberOfPlans()) }; //Is number of plans correct?

        this.openWriteableDB();
        int rowCount = db.update(PRODUCT_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteListItem(long id) {
        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(PRODUCT_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
}



