package com.example.hpark4435.a01;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/* FILE         : GroceryPlan.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 2 - 9
 * DESCRIPTION  : This class is used to encapsulate attributes and behaviour of a grocery plan.
 */

public class GroceryPlan {
    private List<GroceryItem> itemList;
    private String store;
    private String date;
    private int numOfItems;
    private int numUnchecked;

    public GroceryPlan(){
        itemList = new ArrayList<>();
        numOfItems = 0;
        numUnchecked = 0;
    }

    public String getStoreName() {return store;}
    public String getDate () {return date;}
    public int getNumOfItems () {return numOfItems;}
    public int getNumUnchecked() {return numUnchecked;}
    public GroceryItem getItem(int index) {return itemList.get(index);}

    public void setStoreName(String newStore) {store = newStore;}
    public void setDate (String newDate) {date = newDate;}

    public void addItem(GroceryItem newItem){
        itemList.add(newItem);
        numOfItems++;

        if(!newItem.isChecked()){
            numUnchecked++;
        }
    }

    public void checkItem(int index){
        itemList.get(index).checkItem();
        numUnchecked--;
    }

    public void uncheckItem(int index){
        itemList.get(index).uncheckItem();
        numUnchecked++;
    }
}
