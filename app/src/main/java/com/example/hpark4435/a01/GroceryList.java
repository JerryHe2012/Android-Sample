package com.example.hpark4435.a01;

/* FILE         : GroceryList.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This class is used for modeling and encapsulating Grocery List.
 */

public class GroceryList {
    int listId;             // ID of the list in database
    int numOfItems;         // how many different products in this list
    String date;            // date of this grocery plan
    int storeId;            // which store

    // METHOD       : Constructor
    // DESCRIPTION  : Default Constructor takes no parameters
    public GroceryList () {
        date = "";
    }

    // METHOD       : Constructor
    // DESCRIPTION  : Constructor take parameters and set number of items, date, and store id.
    public GroceryList(int numOfItems, String date, int storeId){
        this.numOfItems = numOfItems;
        this.date = date;
        this.storeId = storeId;
    }

    // METHOD       : Constructor
    // DESCRIPTION  : Constructor take parameters and set all data members
    public GroceryList(int listId, int numOfItems, String date, int storeId){
        this.listId = listId;
        this.storeId= storeId;
        this.numOfItems = numOfItems;
        this.date = date;
    }

    // Accessors and Mutators for all data members
    public int getListId() { return listId; }
    public void setListId(int listId) { this.listId = listId; }

    public int getNumOfItems() { return numOfItems; }
    public void setNumOfItems(int numOfItems) { this.numOfItems = numOfItems; }

    public int getStoreId() {return  storeId;}
    public void setStoreId(int storeId) { this.storeId = storeId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
