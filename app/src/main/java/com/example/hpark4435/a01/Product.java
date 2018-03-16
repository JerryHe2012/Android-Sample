package com.example.hpark4435.a01;

import java.util.Queue;

/* FILE         : Product.java
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This class is used for modeling and encapsulating Product
 */

public class Product {
    private int productId;          // product id in database
    private int listId;             // which list it belongs to
    private String name;            // name of the product
    private int quantity;           // quantity of this product in its grocery list
    private int checked;            // is this product checked

    // constant TRUE & FALSE (for SQLITE database)
    public static final int TURE = 1;
    public static final int FALSE = 0;

    // METHOD       : Constructor
    // DESCRIPTION  : Default constructor takes no parameters
    public Product() {
        name = "";
        quantity = 0;
        checked = FALSE;
    }

    // METHOD       : Constructor
    // DESCRIPTION  : Constructor take parameters and set listId, name, quantity and check status
    public Product(int listId, String name, int quantity, int checked){
        this.listId = listId;
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
    }


    // METHOD       : Constructor
    // DESCRIPTION  : Constructor take parameters and set all data members
    public Product(int productId, int listId, String name, int quantity, int checked){
        this.productId = productId;
        this.listId = listId;
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
    }

    // Accessors and Mutators for all data members
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
