package com.example.hpark4435.a01;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/**
 * Created by yli7861 on 2/8/2018.
 */

public class GroceryPlan {
    private List<GroceryItem> itemList;
    private String store;
    private LocalDate date;
    private int numOfItems;
    private int numUnchecked;

    public GroceryPlan(){
        itemList = new ArrayList<>();
        numOfItems = 0;
        numUnchecked = 0;
    }

    public String getStoreName() {return store;}
    public LocalDate getDate () {return date;}
    public int getNumOfItems () {return numOfItems;}
    public int getNumUnchecked() {return numUnchecked;}
    public GroceryItem getItem(int index) {return itemList.get(index);}

    public void setStoreName(String newStore) {store = newStore;}
    public void setDate (LocalDate newDate) {date = newDate;}

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
