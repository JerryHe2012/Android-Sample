package com.example.hpark4435.a01;

/**
 * Created by Hpark4435 on 2/6/2018.
 */


// ClassicSingleton class maintains a static reference to the lone singleton instance and returns that reference from the static getInstance() method.
public class GrocerySingleton {
    private static GrocerySingleton ourInstance = null;

    private  String planDate;
    private String storeName;
    private String[] itemName = new String[100];
    private int[] itemQuantity = new int[100];
    private int NumberOfItem;


    public static GrocerySingleton getInstance() {
        if(ourInstance == null)
        {
            ourInstance = new GrocerySingleton();
        }
        return ourInstance;
    }

    //a private constructor so no instances can be made outside this class
    private GrocerySingleton() {} // By using this, no outer class can initialize this class's object

    public String getItemName(int index) {return itemName[index];}
    public int getQunatity(int index) {return itemQuantity[index];}
    public int getNumberOfItem() {return NumberOfItem; }
    public String getDate() {return planDate; }
    public String getStoreName() {return storeName; }


    public void setNumberOfItem(int numItem)
    {
        NumberOfItem = numItem;
    }

    public void setItemName(String newItem, int index)
    {
        itemName[index] = newItem;
    }


    public void setQuantity(int index)
    {
        itemQuantity[index] = 0;
    }

    public void setQuantityPlus(int index)
    {

        itemQuantity[index] = itemQuantity[index] + 1;

    }

    public void setQuantityMinus(int index)
    {

        itemQuantity[index] = itemQuantity[index] - 1;

    }

    public void setDate(String date)
    {

        planDate = date;

    }
    public void setStoreName(String name)
    {

        storeName = name;

    }
}
