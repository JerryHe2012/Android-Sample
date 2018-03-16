package com.example.hpark4435.a01;

/* FILE         : Store.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This Store Class is the form of creating the table for store.
 *              It has three elements : storeID - used for primary key,
 *                                      name - name of the store
 *                                      URL - store's URL website.
 */

public class Store {
    int storeId;
    String name;
    String URL;

    public Store(){
        String name = "";
        String URL = "";
    }

    public Store(int id, String name, String URL){
        this.storeId = id;
        this.name = name;
        this.URL = URL;
    }

    public int getStoreId(){ return storeId; }
    public void setStoreId(int id) { this.storeId = id; }

    public String getStoreName() { return name; }
    public void setStoreName(String name) { this.name = name; }

    public String getURL() { return URL; }
    public void setURL(String url) { this.URL = url; }

}
