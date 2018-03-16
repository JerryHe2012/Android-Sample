package com.example.hpark4435.a01;

import java.util.Queue;

/* FILE         : Product.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This Product file/class is used for setting up class in order to generate Product table in the database.
 */

public class Product {
    private int productId;
    private int listId;
    private String name;
    private int quantity;
    private int checked;

    public static final int TURE = 1;
    public static final int FALSE = 0;

    public Product() {
        name = "";
        quantity = 0;
        checked = FALSE;
    }

    public Product(int listId, String name, int quantity, int checked){
        this.listId = listId;
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
    }

    public Product(int productId, int listId, String name, int quantity, int checked){
        this.productId = productId;
        this.listId = listId;
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getListId() { return listId; }
    public void setListId(int listId) { this.listId = listId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {this.quantity = quantity; }

    public int getChecked() { return checked; }
    public void setChecked(int checked) { this.checked = checked; }


}
