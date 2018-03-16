package com.example.hpark4435.a01;

/* FILE         : Store.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This class is used for modeling and encapsulating Store info
 */
public class Store {
    int storeId;            // store id in database
    String name;            // name of the store
    String URL;             // link to store's website

    // METHOD       : Constructor
    // DESCRIPTION  : Default constructor takes no parameters
    public Store(){
        String name = "";
        String URL = "";
    }

    // METHOD       : Constructor
    // DESCRIPTION  : Constructor takes parameters and set all data members
    public Store(int id, String name, String URL){
        this.storeId = id;
        this.name = name;
        this.URL = URL;
    }

    // Accessors and Mutators of all data members
    public int getStoreId(){ return storeId; }
    public void setStoreId(int id) { this.storeId = id; }

    public String getStoreName() { return name; }
    public void setStoreName(String name) { this.name = name; }

    public String getURL() { return URL; }
    public void setURL(String url) { this.URL = url; }

}
