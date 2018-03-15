package com.example.hpark4435.a01;
//Name: Adam Sosnowski, Hyunbin Park, Jinaqing He, Yinqi Li
//Date: 2/8/2018
//Course: Mobile applicatoin development assignment 1.
//Description: This application will act as a grocery planner/list that a user will be able to use while grocery shopping. It is designed to be simple and easy to use
//This particular file will contain the logic and code user to save items to the singleton class. Here, a user will be able to create/delete/edit new items for their grocery list.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
public class DetailActivity extends Activity {
    public Button btn_AddButton;
    public Button btn_SavePlan;




    GrocerySingleton grocery = GrocerySingleton.getInstance();

   // GrocerySingleton grocerySingleton = new GrocerySingleton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final StringBuilder sb = new StringBuilder();


        final TableLayout theLayout = (TableLayout)findViewById(R.id.LLgoods);
        grocery.setNumberOfItem(0);
        btn_AddButton = (Button)findViewById(R.id.btn_AddButton);
        btn_SavePlan = (Button)findViewById(R.id.btn_SavePlan);
        btn_AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int totalLine = 0;
                totalLine = grocery.getNumberOfItem();
                grocery.setQuantity(totalLine);
                int thewight = theLayout.getWidth();
                thewight = thewight - 525;

                //Create a table row object to place ann the buttons and text boxes for new items
                final TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(totalLine * 6); //Ensure that each newly created object will have a unique ID.
                theLayout.addView(theRow);      //We can enforce this by multiply the amount of items by the amount of widgets on the table row
                Log.i("Detail Activity", "Created new table row");

                //Add the edit text object for a new item where a user can edit.
                final EditText newItem = new EditText(theRow.getContext());
                newItem.setLayoutParams(new TableRow.LayoutParams(thewight, TableRow.LayoutParams.WRAP_CONTENT));
                newItem.setSingleLine(true);
                newItem.setId((totalLine * 6) + 1); //After multiplying to get a unique ID, add another value for each widget index.
                newItem.setText("New Item");
                theRow.addView(newItem);
                Log.i("Detail Activity", "Created new edit text item ");

                //Created the increment button that will allow a user to increase the quantity of an item
                final Button btn_PlusButton = new Button(theRow.getContext());
                btn_PlusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_PlusButton.setId((totalLine * 6) + 2);
                btn_PlusButton.setText("+");
                theRow.addView(btn_PlusButton);
                Log.i("Detail Activity", "Created new item increment button ");

                //Create the text view that displays the quantity of an item
                final TextView newCount = new TextView(theRow.getContext());
                newCount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newCount.setId((totalLine * 6) + 3);
                grocery.setQuantityPlus(totalLine);
                newCount.setText(Integer.toString(grocery.getQunatity(totalLine)));
                theRow.addView(newCount);
                Log.i("Detail Activity", "Created new textview object ");

                //Created the decrement button that will allow a user to decrease the quantity of an item
                final Button btn_MinusButton = new Button(theRow.getContext());
                btn_MinusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_MinusButton.setId((totalLine * 6) + 4);
                btn_MinusButton.setText("-");
                theRow.addView(btn_MinusButton);
                Log.i("Detail Activity", "Created new decrement button ");

                //And create a deletion button that can allow a user to remove an item from their grocery list.
                final Button btn_deleteItem = new Button(theRow.getContext());
                btn_deleteItem.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_deleteItem.setId((totalLine * 6) + 5);
                btn_deleteItem.setText("X");
                theRow.addView(btn_deleteItem);
                Log.i("Detail Activity", "Created new delete button ");

                //A listener for the delete button that will execute any time the delete button is clicked
                btn_deleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        try{
                            Log.i("Detail Activity", "User deleted item");
                            int itemNum = grocery.getNumberOfItem();
                            int i = btn_deleteItem.getId();
                            grocery.setNumberOfItem(itemNum - 1);
                            grocery.setQuantity((i - 5) / 6); //Change the quantity of the item
                            theLayout.removeView((TableRow)findViewById(i - 5)); //Delete the current row and adjust the ID's
                        }
                        catch(Exception e){
                            Log.e("Result Activity", e.toString());
                        }
                    }
                });
                //A listener for the plus button that will execute any time the plus button is clicked
                btn_PlusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            // Take action.
                            Log.i("Detail Activity", "User Incremented Item Quantity");
                            int i = btn_PlusButton.getId();
                            grocery.setQuantityPlus((i - 2)/6); //Increment the quantity

                            TextView theCount = (TextView)findViewById(i + 1);
                            theCount.setText(Integer.toString(grocery.getQunatity((i - 2)/6)));
                        }
                        catch(Exception e){
                            Log.e("Result Activity", e.toString());
                        }

                    }
                });

                //A listener for the minus button that will execute any time the minus button is clicked
                btn_MinusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            // Take action.
                            Log.i("Detail Activity", "User Decremented Item Quantity");
                            int i = btn_MinusButton.getId();
                            TextView theCount = (TextView)findViewById(i - 1);
                            if(Integer.parseInt(theCount.getText().toString()) > 1)
                            {
                                grocery.setQuantityMinus((i - 4) / 6); //decrement the quantity
                                theCount.setText(Integer.toString(grocery.getQunatity((i - 4) / 6)));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Can't have a quantity less than 1!",Toast.LENGTH_SHORT).show();
                                Log.i("Detail Activity", "Cannot specify a quantity less than 1!");
                            }
                        }
                        catch(Exception e){
                            Log.e("Result Activity", e.toString());
                        }
                    }
                });

                //Create a listener for the edit text
                //This will clear the text to allot a user to easily modify an item
                newItem.setOnFocusChangeListener (new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        try{
                            String currentText = newItem.getText().toString();
                            if (currentText.equals("New Item"))
                            {
                                newItem.setText("");
                            }
                        }
                        catch(Exception e){
                            Log.e("Result Activity", e.toString());
                        }
                    }
                });

                totalLine++;
                int totalItem = grocery.getNumberOfItem();
                grocery.setNumberOfItem(totalItem + 1);
                grocery.setActualTotalLine(totalLine);
               // totalLine = 1 + grocery.getNumberOfItem();
                //grocery.setNumberOfItem(totalLine);



            }});

        //Finally, a save button listener
        //This will save the contents on current page to the singleton
        btn_SavePlan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try{
                    Log.i("Detail Activity", "User clicked on save plan");
                    int totalLine = grocery.getActualTotalLine();

                    // build new plan
                    GroceryPlan newPlan = new GroceryPlan();
                    newPlan.setDate(grocery.getDate());

                    for (int i = 0; i < totalLine; i++)
                    {
                        if (grocery.getQunatity(i) != 0) //Ensure each item has a quantity of at least 1
                        {
                            TextView theCount = (TextView) findViewById((i * 6) + 1);
                            grocery.setItemName(theCount.getText().toString(), i);

                            GroceryItem newItem = new GroceryItem(i+1,1,grocery.getItemName(i), grocery.getQunatity(i));
                            newPlan.addItem(newItem);
                            long insertId = db.insertTask(newItem);
                            if (insertId > 0) {
                                sb.append("Row inserted! Insert Id: " + insertId + "\n");
                                Toast.makeText(getApplicationContext(),"Saved to DB",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    // add new plan to the list
                    grocery.addPlan(newPlan);
                
                    Intent getfinalpage = new Intent(DetailActivity.this, ResultActivity.class);
                    startActivity(getfinalpage); //Go to the next page
                }
                catch(Exception e){
                    Log.e("Result Activity", e.toString());
                }
            }});

    }
}
