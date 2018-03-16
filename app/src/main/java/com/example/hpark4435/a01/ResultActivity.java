package com.example.hpark4435.a01;

/* FILE         : ResultActivity.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 2 - 9
 * DESCRIPTION  : This java file is used for displaying result of shopping list. It displays what user chose and saved.
 *                After that, user is able to see shopping list in this page, and can check if they found it in the market.
 *                Finally, progress bar will tell user how many items they found it.
 */



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends Activity {

    public Button btn_GoFirstScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final GrocerySingleton grocery = GrocerySingleton.getInstance();

        // get grocery list from database
        int listId = grocery.getListID();
        GroceryList list = grocery.db.getGroceryList(listId);
        int totalLine = list.getNumOfItems();
        grocery.setNumberOfItem(list.getNumOfItems());
        int checked = 0;

        // get all product from database
        ArrayList<Product> products = grocery.db.getProducts(listId);

        TableLayout theLayout = (TableLayout)findViewById(R.id.TLfinal);

        // Declare and Initialize of progress Bar
        final ProgressBar theBar = (ProgressBar)findViewById(R.id.progressBar);
        DisplayMetrics displayMetrics = new DisplayMetrics();    // Using DisplayMetrics for creating last page
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;                  // Total screen width
        int itemWidth = width - 200;                             // Width of each item.
        int theID = -1;
        Log.i("Result Activity", "ACTIVITY GROCERY ACTIVATE");

        TableRow theFirstRow = new TableRow(theLayout.getContext()); // Creating first line place.
        theFirstRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        theFirstRow.setGravity(Gravity.CENTER);
        theFirstRow.setId(theID);                                    // Set up ID Value
        theLayout.addView(theFirstRow);
        Log.i("Result Activity", "Set up First Line(Used for store)");

        TextView newStore = new TextView(theFirstRow.getContext());  // Declare and Initialize TextView
        newStore.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 60));
        newStore.setGravity(Gravity.CENTER);
        newStore.setId(theID - 1);
        newStore.setText(grocery.getStoreName());                   // Get Store name from Singleton
        theFirstRow.addView(newStore);
        Log.i("Result Activity", "See store Information");

        TableRow theSecondRow = new TableRow(theLayout.getContext()); // Setting up second line (Going to be used for Date)
        theSecondRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        theSecondRow.setGravity(Gravity.CENTER);
        theSecondRow.setId(theID - 2);
        theLayout.addView(theSecondRow);
        Log.i("Result Activity", "Set up Second Line(Used for Date)");



        TextView newDate = new TextView(theSecondRow.getContext()); // Setting up Date in second line
        newDate.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 60));
        newDate.setGravity(Gravity.CENTER);
        newDate.setId(theID - 3);
        newDate.setText(grocery.getDate());                         // Getting date from Singleton and set up.
        theSecondRow.addView(newDate);
        Log.i("Result Activity", "See date Information");


        Product product = null;
        for (int i = 0; i < totalLine; i++)
        {
            product = products.get(i);

            // if total quantity is not Zero
            if (product.getQuantity() != 0) {

                // Setting up ID number.
                TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(product.getProductId() * 4);
                theLayout.addView(theRow);
                Log.i("Result Activity", "Setting up ID Value");


                // Setting up each part with item they added.
                TextView newItem = new TextView(theRow.getContext());
                newItem.setLayoutParams(new TableRow.LayoutParams(itemWidth, TableLayout.LayoutParams.WRAP_CONTENT));
                newItem.setId((product.getProductId() * 4) + 1);
                newItem.setText(product.getName());                // getting name of item in the singleton object.
                theRow.addView(newItem);
                Log.i("Result Activity", "Got Item Name and displayed");


                // Setting up number of each item.
                TextView newItemCount = new TextView(theRow.getContext());
                newItemCount.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newItemCount.setId((product.getProductId() * 4) + 2);
                newItemCount.setText(Integer.toString(product.getQuantity())); // getting quantity of each item user tried to buy.
                theRow.addView(newItemCount);
                Log.i("Result Activity", "Getting how many items");


                // Creating Checkbox in the each line.
                final CheckBox newCheck = new CheckBox(theRow.getContext());
                newCheck.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newCheck.setId((product.getProductId() * 4) + 3);
                if(product.getChecked()==Product.TURE){
                    newCheck.setChecked(true);
                    checked++;
                }
                theRow.addView(newCheck);

                newCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try{
                            // get product id in database
                            int productId = view.getId();
                            productId = (productId - 3) / 4;

                            // Take action.
                            Log.i("Result Activity", "[Log] Inside of setOnClickListner Checking.");
                            int totalchecked = grocery.getCheckQuantity();
                            int total = grocery.getNumberOfItem();
                            if (newCheck.isChecked()) {             // If user found item, they checked it.
                                grocery.db.updateProductChecked(productId, 1);      // update to database
                                totalchecked = totalchecked + 1;    // Increase +1 for progress bar displaying
                            } else {
                                grocery.db.updateProductChecked(productId, 0);  // update to database
                                totalchecked = totalchecked - 1;
                            }
                            grocery.setCheckQuantity(totalchecked);
                            int totalProgress = (100 * totalchecked) / total;      // Value of Total progress
                            theBar.setProgress(totalProgress);                      // implement in the progressBar
                            Log.i("Result Activity", "Displaying progress bar depends on user choose.");
                        }
                        catch(Exception e){
                            Log.e("Result Activity", e.toString());
                        }
                    }
                });
            }
        }

        int totalProgress = (100 * checked) / list.getNumOfItems();
        theBar.setProgress(totalProgress);
        grocery.setCheckQuantity(checked);

        btn_GoFirstScreen = (Button) findViewById(R.id.btn_GoingFirstPage);
        btn_GoFirstScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Log.i("Result Activity", "Clicking Button for Going the first page. ");
                    Intent GoBackFirst = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(GoBackFirst);
                }catch(Exception ex)
                {
                    Log.e("Result Activity", ex.toString());
                }
            }
        });

    }
}
