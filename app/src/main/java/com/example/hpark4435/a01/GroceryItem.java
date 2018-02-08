package com.example.hpark4435.a01;

/**
 * Created by yli7861 on 2/8/2018.
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

