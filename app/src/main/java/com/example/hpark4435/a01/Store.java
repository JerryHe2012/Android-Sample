package com.example.hpark4435.a01;

/**
 * Created by yingqi on 15/03/2018.
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
