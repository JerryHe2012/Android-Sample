package com.example.hpark4435.a01;

/**
 * Created by yingqi on 15/03/2018.
 */

public class GroceryList {
    int listId;
    int numOfItems;
    String date;
    int storeId;

    public GroceryList () {
        date = "";
    }

    public GroceryList(int numOfItems, String date, int storeId){
        this.numOfItems = numOfItems;
        this.date = date;
        this.storeId = storeId;
    }

    public GroceryList(int listId, int numOfItems, String date, int storeId){
        this.listId = listId;
        this.storeId= storeId;
        this.numOfItems = numOfItems;
        this.date = date;
    }

    public int getListId() { return listId; }
    public void setListId(int listId) { this.listId = listId; }

    public
}
