package com.example.hpark4435.a01;

/* FILE         : GroceryItem.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 2 - 9
 * DESCRIPTION  : This class is used to encapsulate attributes and behaviours of a shopping item.
 */

public class GroceryItem {
    private String itemName;
    private int itemQuantity;
    private int itemID;
    private boolean checkStatus;

    public GroceryItem(int id, String newName, int newQuantity) {
        itemID = id;
        itemName = newName;
        itemQuantity = newQuantity;
        checkStatus = false;
    }

    public int getItemID(){return itemID;}
    public String getItemName(){return itemName;}
    public int getItemQuantity(){return itemQuantity;}
    public boolean isChecked(){return checkStatus;}

    public void setItemID(int newItemID){ itemID = newItemID;}

    public void setItemName(String newName){
        itemName = newName;
    }

    public void setItemQuantity(int newQuantity){
        itemQuantity = newQuantity;
    }

    public void checkItem(){checkStatus = true;}
    public void uncheckItem() {checkStatus = false;}
}

