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
    private boolean checkStatus;

    public GroceryItem(String newName, int newQuantity) {
        itemName = newName;
        itemQuantity = newQuantity;
        checkStatus = false;
    }

    public String getItemName(){return itemName;}
    public int getItemQuantity(){return itemQuantity;}
    public boolean isChecked(){return checkStatus;}

    public void setItemName(String newName){
        itemName = newName;
    }

    public void setItemQuantity(int newQuantity){
        itemQuantity = newQuantity;
    }

    public void checkItem(){checkStatus = true;}
    public void uncheckItem() {checkStatus = false;}
}

