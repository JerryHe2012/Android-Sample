package com.example.hpark4435.a01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.SQLException;

import java.util.ArrayList;

/* FILE         : Database.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This database java file includes all sql queries for this application and objects for creating tables.
 */

public class DataBase {


    // database constants
    public static final String DB_NAME = "Grocery.db";          //Data base name
    public static final int    DB_VERSION = 1;

    //Grocery list constants
    public static final String LIST_TABLE = "GroceryList";      //Grocery List table name

    public static final String LIST_ID = "ListID";              //Grocery List first column. "ListID" a primary key to keep track of the grocery lists a user creates
    public static final int    LIST_ID_COL = 0;

    public static final String LIST_ITEMS = "totalItems";       //Grocery List second column. "totalItems". This column is intended to keep track of the total items in the grocery list
    public static final int    LIST_ITEMS_COL = 1;

    public static final String LIST_DATE = "Date";              //Grocery List third column. "Date" will keep track of the date the grocery list is made for.
    public static final int    LIST_DATE_COL = 2;

    public static final String LIST_STOREID = "StoreID";        //Grocery List fourth column. "StoreID" will keep track of the selected store for the grocery list. foreign key
    public static final int    LIST_STOREID_COL = 3;


    //Store table constants
    public static final String STORE_TABLE = "Store";           //Store table name

    public static final String STORE_ID = "StoreID";            //"StoreID", first column from store table intended to keep track of which store was selected for the list
    public static final int    STORE_ID_COL = 0;

    public static final String STORE_NAME = "StoreName";        //"StoreName", second column from store table used to keep track of the selected stores name
    public static final int    STORE_NAME_COL = 1;

    public static final String STORE_URL = "URL";               //"URL" third column from store table used to keep track of the selected stores website link
    public static final int    STORE_URL_COL = 2;



    // product table constants
    //Table 1 : Keep track of grocery list items
    public static final String PRODUCT_TABLE = "Product";       //Product table name

    public static final String PRODUCT_ID = "ItemID";           //"ItemID" Used to keep track of the product identification number
    public static final int    PRODUCT_ID_COL = 0;

    public static final String PLIST_ID = "ListID";             //"ListID" foreign key from the grocery list table used to reference products from a grocery list
    public static final int    PLIST_ID_COL = 1;

    public static final String PRODUCT_NAME = "ItemName";       //"ItemName" Intended to keep track of the product names
    public static final int    PRODUCT_NAME_COL = 2;

    public static final String PRODUCT_QUANTITY = "Quantity";   //"Quantity" used to keep track of the number of the same products in the grocery list
    public static final int    PRODUCT_QUANTITY_COL = 3;

    public static final String PRODUCT_CHECK = "isChecked";     //"IsChecked" intended to keep track whether or not an item is selected off a grocery list
    public static final int    PRODUCT_CHECK_COL = 4;





    // CREATE and DROP TABLE statements - PRODUCT
    public static final String CREATE_PRODUCT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE + " (" +
                    PRODUCT_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLIST_ID     + " INTEGER NOT NULL," +
                    PRODUCT_NAME + " TEXT NOT NULL , "     +
                    PRODUCT_QUANTITY + " INTEGER NOT NULL, " +
                    PRODUCT_CHECK +" INTEGER NOT NULL," +
                    " FOREIGN KEY ("+PLIST_ID+") REFERENCES "+LIST_TABLE+"("+LIST_ID+")) ";

    public static final String DROP_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + PRODUCT_TABLE;


    // CREATE and DROP TABLE statements - STORE
    public static final String CREATE_STORE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + STORE_TABLE + " (" +
            STORE_ID    + " INTEGER PRIMARY KEY, " +
            STORE_NAME  + " TEXT NOT NULL, " +
            STORE_URL   +" TEXT NOT NULL);";

    public static final String DROP_STORE_TABLE =
            "DROP TABLE IF EXISTS " + STORE_TABLE;




    // CREATE and DROP TABLE statements - LIST
    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE " + LIST_TABLE + " (" +
                    LIST_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LIST_ITEMS + " INTEGER NOT NULL, " +
                    LIST_DATE     + " TEXT NOT NULL, " +
                    LIST_STOREID +" INTEGER NOT NULL," +
                    " FOREIGN KEY ("+LIST_STOREID+") REFERENCES "+STORE_TABLE+"("+STORE_ID+"))";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS " + LIST_TABLE;





    /* METHOD       : DBHelper
        SubClass    : SQLiteOpenHelper
       DESCRIPTION  : This DBHelper class is used for constructing DBHelper.
     */
    private static class DBHelper extends SQLiteOpenHelper {
        private Context context;

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_STORE_TABLE);
            db.execSQL(CREATE_LIST_TABLE);
            db.execSQL(CREATE_PRODUCT_TABLE);

            // fill up store table
            String[] storeNames = context.getResources().getStringArray(R.array.store_name);
            String[] storeURLs = context.getResources().getStringArray(R.array.store_url);
            int i = 0;
            String sql;

            for(String name : storeNames ){
                sql = "INSERT INTO "+STORE_TABLE+" ("+STORE_ID+","+STORE_NAME+","+STORE_URL+
                        ") VALUES ("+Integer.toString(i+1)+", '"+name+"', '"+storeURLs[i]+"')";
                db.execSQL(sql);
                i++;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Grocery List", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(DataBase.DROP_PRODUCT_TABLE); //Re create the tables on database upgrade
            db.execSQL(DataBase.DROP_STORE_TABLE);
            db.execSQL(DataBase.DROP_LIST_TABLE);
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

    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }


    //private method to close the cursor object used to read data from data base
    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }





// ----------- Operations on Product table -----------------
    public Cursor getProductCursor (int listId){
        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { Integer.toString(listId)};

        this.openReadableDB();
        Cursor cursor = db.query(PRODUCT_TABLE, null,
                where, whereArgs, null, null, null);

        return cursor;
    }

    /* METHOD           : getProducts
       PARAMETER        : int listId - the number of id of product table.
       RETURN           : ArrayList - Returning all of items in the Products table.
       DESCRIPTION      : This method is used for returning all items in the product table.
     */
    public ArrayList<Product> getProducts(int listId){
        String where = LIST_ID + "= ?";
        String[] whereArgs = { Integer.toString(listId)};

        this.openReadableDB();
        Cursor cursor = db.query(PRODUCT_TABLE, null,
                where, whereArgs, null, null, null);
        ArrayList<Product> products= new ArrayList();
        while(cursor.moveToNext()){
            products.add((getProductFromCursor(cursor)));
        }

        this.closeCursor(cursor); //Clean up
        this.closeDB();             //Finish reading from data base

        return products;        // return result object
    }

    //Function name :   getProduct()
    //Parameters    :   int id; parameter used as the product item ID
    //Returns:      :   returns the product object
    //Description   : This method is used for returning specific product(item ID).
    public Product getProduct(int id) {
        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Product product = getProductFromCursor(cursor);
        this.closeCursor(cursor);
        this.closeDB();

        return product;
    }

    private static Product getProductFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Product product = new Product(
                        cursor.getInt(PRODUCT_ID_COL),
                        cursor.getInt(PLIST_ID_COL),
                        cursor.getString(PRODUCT_NAME_COL),
                        cursor.getInt(PRODUCT_QUANTITY_COL),
                        cursor.getInt(PRODUCT_CHECK_COL));
                return product;
            }
            catch(Exception e) {
                return null;
            }
        }
    }


    public long insertProduct(Product product) {
        ContentValues cv = new ContentValues();
        cv.put(PLIST_ID, product.getListId());
        cv.put(PRODUCT_NAME, product.getName());
        cv.put(PRODUCT_QUANTITY, product.getQuantity());
        cv.put(PRODUCT_CHECK, product.getChecked());


        this.openWritableDB();
        long rowID = db.insert(PRODUCT_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }


    public int updateProduct(Product product){
        ContentValues cv = new ContentValues();
        cv.put(PLIST_ID, product.getListId());
        cv.put(PRODUCT_NAME, product.getName());
        cv.put(PRODUCT_QUANTITY, product.getQuantity());
        cv.put(PRODUCT_CHECK, product.getChecked());



        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { String.valueOf(product.getProductId()) };

        this.openWritableDB();
        int rowCount = db.update(PRODUCT_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int updateProductChecked(int pId, int checked){
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_CHECK, checked);

        String where = PRODUCT_ID + "= ?";
        String[] whereArgs = { String.valueOf(pId)};

        this.openWritableDB();
        int rowCount = db.update(PRODUCT_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteProduct(int productId){
        String where = productId + "= ?";
        String[] whereArgs = {String.valueOf(productId)};

        this.openWritableDB();
        int rowCount = db.delete(PRODUCT_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }



    // ----------- Operations on GroceryList table -----------------
    public ArrayList<GroceryList> getGroceryLists() {
        ArrayList<GroceryList> lists = new ArrayList();
        openReadableDB();

        Cursor cursor = db.query(LIST_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()){
            GroceryList list = new GroceryList();
            list.setListId(cursor.getInt(LIST_ID_COL));
            list.setDate(cursor.getString(LIST_DATE_COL));
            list.setNumOfItems(cursor.getInt(LIST_ITEMS_COL));
            list.setStoreId(cursor.getInt(LIST_STOREID_COL));

            lists.add(list);
        }
        closeCursor(cursor);
        closeDB();

        return lists;
    }

    public GroceryList getGroceryList (int listId){
        String where = LIST_ID + "= ?";
        String[] whereArgs = {String.valueOf(listId)};

        openReadableDB();
        Cursor cursor = db.query(LIST_TABLE, null,
                where, whereArgs, null, null, null);
        GroceryList list = null;

        cursor.moveToFirst();

        list = new GroceryList(cursor.getInt(LIST_ID_COL),
                               cursor.getInt(LIST_ITEMS_COL),
                               cursor.getString(LIST_DATE_COL),
                               cursor.getInt(LIST_STOREID_COL));

        this.closeCursor(cursor);
        this.closeDB();

        return list;
    }

    public long insertGroceryList(GroceryList list) {
        ContentValues cv = new ContentValues();
        cv.put(LIST_ITEMS, list.getNumOfItems());
        cv.put(LIST_DATE, list.getDate());
        cv.put(LIST_STOREID, list.getStoreId());

        this.openWritableDB();
        long rowID = db.insert(LIST_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }


    public int updateGroceryList(GroceryList list){
        ContentValues cv = new ContentValues();
        cv.put(LIST_ITEMS, list.getNumOfItems());
        cv.put(LIST_DATE, list.getDate());
        cv.put(LIST_STOREID, list.getStoreId());



        String where = LIST_ID + "= ?";
        String[] whereArgs = { String.valueOf(list.getListId()) };

        this.openWritableDB();
        int rowCount = db.update(LIST_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }



    // -------------------- Operations on store table --------------------

    public long insertStore(int storeId, String name, String URL){
        ContentValues cv = new ContentValues();
        cv.put(STORE_ID, storeId);
        cv.put(STORE_NAME, name);
        cv.put(STORE_URL, URL);

        this.openWritableDB();;
        long rowID = db.insert(STORE_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }

    public Store getStore(int storeId){
        String where = STORE_ID + "= ?";
        String[] whereArgs = {String.valueOf(storeId)};

        openReadableDB();
        Cursor cursor = db.query(STORE_TABLE, null,
                where, whereArgs, null, null, null);
        Store store = null;
        cursor.moveToFirst();
        store = new Store(cursor.getInt(STORE_ID_COL),
                cursor.getString(STORE_NAME_COL),
                cursor.getString(STORE_URL_COL)
                );

        this.closeCursor(cursor);
        this.closeDB();

        return store;
    }

    public Store getStore(String name){
        String where = STORE_NAME + "= ?";
        String[] whereArgs = { name };

        openReadableDB();
        Cursor cursor = db.query(STORE_TABLE, null,
                where, whereArgs, null, null, null);
        Store store = null;
        cursor.moveToFirst();
        store = new Store(cursor.getInt(STORE_ID_COL),
                cursor.getString(STORE_NAME_COL),
                cursor.getString(STORE_URL_COL)
        );

        this.closeCursor(cursor);
        this.closeDB();

        return store;
    }
}



