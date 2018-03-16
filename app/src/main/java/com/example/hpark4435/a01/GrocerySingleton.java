package com.example.hpark4435.a01;

import java.util.ArrayList;
import java.util.List;

/* FILE         : GrocerySingleton.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This class is used for storing customer order information into Singleton objects.
 */

/*
class       : GrocerySingleton class
Description : ClassicSingleton class maintains a static reference to the lone singleton instance and returns that reference from the static getInstance() method.
 */
public class GrocerySingleton {


    private static GrocerySingleton ourInstance = null;         // Allow to access to each objects in this singleton class.

    private String planDate;                                    // Date of plan to do shopping
    private String storeName;                                   // Name of the store user wants to shop
    private String[] itemName = new String[100];                // Name of item user wants to shop
    private int[] itemQuantity = new int[100];                  // Number of item user wants to buy for each item
    private int NumberOfItem;                                   // Number of total item list
    private int actualTotalLine;                                // Total line in the android screen.
    private int checkQuantity;                                  // How many items that user found in the store.
    private int listID;                                         // Current working Grocery List ID in database
    private boolean tfResult;                                   //

    public DataBase db;


    /* METHOD       : getInstance
     * PARAMETER    : None
     * RETURN       : None
     * DESCRIPTION  : This method is used for giving access to each classes.
     *                Through this method, it enables to acess each item in the different classes.
     */
    public static GrocerySingleton getInstance() {
        if(ourInstance == null)
        {
            ourInstance = new GrocerySingleton();
        }
        return ourInstance;
    }

    // METHOD       : GrocerySingleton Construtor
    // DESCRIPTION  : A private constructor so no instances can be made outside this class
    private GrocerySingleton() {
        tfResult = false;
    } // By using this, no outer class can initialize this class's object



    public GrocerySingleton(int ListID, String name, int Quantity) {
        this.listID = ListID;
        this.itemName[NumberOfItem] = name;
        this.itemQuantity[NumberOfItem] = Quantity;
        this.tfResult =false;

    } // By using this, no outer class can initialize this class's object



    public String getItemName(int index) {return itemName[index];} // getItemName Mutator : Used for accessing the name of the item
    public int getQunatity(int index) {return itemQuantity[index];}// getQuantity Mutator: Used for getting the quantity of each item
    public int getNumberOfItem() {return NumberOfItem; }           // getNumberOfItem mutator: Used for getting the number of item.
    public String getDate() {return planDate; }                    // getDate mutator       : Used for getting the date of plan.
    public String getStoreName() {return storeName; }              // getStoreName mutator  : Used for getting the name of the store.
    public int getCheckQuantity() {return  checkQuantity; }        // getCheckQuantity mutator: used for getting the checkQuantity value.
    public int getActualTotalLine() {return actualTotalLine;}      // getActualTotalLine mutator: getting actualTotalLine value.
    public int getListID(){return listID;}
    public boolean getTfResult() { return tfResult; }

    public void setNumberOfItem(int numItem)
    {
        NumberOfItem = numItem;
    }               // Setter for numItem

    // Setter for itemName
    public void setItemName(String newItem, int index)
    {
        itemName[index] = newItem;
    }

    // Setter for itemQuantity
    public void setQuantity(int index)
    {
        itemQuantity[index] = 0;
    }

    // Setter for itemQuantity value.
    // Description: used for adding the amount of quantity Plus 1
    public void setQuantityPlus(int index)
    {
        itemQuantity[index] = itemQuantity[index] + 1;
    }
    // Setter for itemQuantity
    // Description: Used for subtracting by 1 of itemQuantity.
    public void setQuantityMinus(int index)
    {

        itemQuantity[index] = itemQuantity[index] - 1;

    }

    // Setter for planDate
    // Description: used for setting up the date of plan of shopping day.
    public void setDate(String date)
    {
        planDate = date;
    }


    // Setter for the name of the store
    // Description : Used for setting up the name of store.
    public void setStoreName(String name)
    {
        storeName = name;
    }

    // Setter for CheckQuantity
    // Description : Used for setting up/modifying the checkQuantity
    public void setCheckQuantity(int value)
    {
        checkQuantity = value;
    }


    // Setter for ActualTotalLine
    // Description : Used for setting up Total line of screen.
    public void setActualTotalLine(int value) {actualTotalLine = value; }

    public void setListID(int id) { this.listID = id; }

    public void setTfResult(boolean tfResult) { this.tfResult = tfResult; }
}
