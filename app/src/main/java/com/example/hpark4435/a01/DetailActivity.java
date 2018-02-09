package com.example.hpark4435.a01;
//Name: Adam Sosnowski, Hyunbin Park, Jinaqing He, Yinqi Li
//Date: 2/8/2018
//Course: Mobile applicatoin development assignment 1.
//Description: This application will act as a grocery planner/list that a user will be able to use while grocery shopping. It is designed to be simple and easy to use
//This particular file will contain the logic and code user to save items to the singleton class. Here, a user will be able to create/delete/edit new items for their grocery list.

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

                final TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(totalLine * 6);
                theLayout.addView(theRow);
                Log.i("Detail Activity", "Created new table row");

                final EditText newItem = new EditText(theRow.getContext());
                newItem.setLayoutParams(new TableRow.LayoutParams(thewight, TableRow.LayoutParams.WRAP_CONTENT));
                newItem.setId((totalLine * 6) + 1);
                newItem.setText("New Item");
                theRow.addView(newItem);
                Log.i("Detail Activity", "Created new edit text item ");

                final Button btn_PlusButton = new Button(theRow.getContext());
                btn_PlusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_PlusButton.setId((totalLine * 6) + 2);
                btn_PlusButton.setText("+");
                theRow.addView(btn_PlusButton);
                Log.i("Detail Activity", "Created new item increment button ");

                final TextView newCount = new TextView(theRow.getContext());
                newCount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newCount.setId((totalLine * 6) + 3);
                grocery.setQuantityPlus(totalLine);
                newCount.setText(Integer.toString(grocery.getQunatity(totalLine)));
                theRow.addView(newCount);
                Log.i("Detail Activity", "Created new textview object ");

                final Button btn_MinusButton = new Button(theRow.getContext());
                btn_MinusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_MinusButton.setId((totalLine * 6) + 4);
                btn_MinusButton.setText("-");
                theRow.addView(btn_MinusButton);
                Log.i("Detail Activity", "Created new decrement button ");

                final Button btn_deleteItem = new Button(theRow.getContext());
                btn_deleteItem.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_deleteItem.setId((totalLine * 6) + 5);
                btn_deleteItem.setText("X");
                theRow.addView(btn_deleteItem);
                Log.i("Detail Activity", "Created new delete button ");


                btn_deleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        Log.i("Detail Activity", "User deleted item");
                        int itemNum = grocery.getNumberOfItem();
                        int i = btn_deleteItem.getId();
                        grocery.setNumberOfItem(itemNum - 1);
                        grocery.setQuantity((i - 5) / 6);
                        theLayout.removeView((TableRow)findViewById(i - 5));
                    }
                });

                btn_PlusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        Log.i("Detail Activity", "User Incremented Item Quantity");
                        int i = btn_PlusButton.getId();
                        grocery.setQuantityPlus((i - 2)/6);

                        TextView theCount = (TextView)findViewById(i + 1);
                        theCount.setText(Integer.toString(grocery.getQunatity((i - 2)/6)));
                    }
                });

                btn_MinusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        Log.i("Detail Activity", "User Decremented Item Quantity");
                        int i = btn_MinusButton.getId();
                        TextView theCount = (TextView)findViewById(i - 1);
                        if(Integer.parseInt(theCount.getText().toString()) > 1)
                        {
                            grocery.setQuantityMinus((i - 4) / 6);
                            theCount.setText(Integer.toString(grocery.getQunatity((i - 4) / 6)));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Can't have a quantity less than 1!",Toast.LENGTH_SHORT).show();
                            Log.i("Detail Activity", "Cannot specify a quantity less than 1!");
                        }
                    }
                });

                newItem.setOnFocusChangeListener (new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        String currentText = newItem.getText().toString();
                        if (currentText.equals("New Item"))
                        {
                            newItem.setText("");
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

        btn_SavePlan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("Detail Activity", "User clicked on save plan");
                int totalLine = grocery.getActualTotalLine();
                for (int i = 0; i < totalLine; i++)
                {
                    if (grocery.getQunatity(i) != 0)
                    {
                        TextView theCount = (TextView) findViewById((i * 6) + 1);
                        grocery.setItemName(theCount.getText().toString(), i);
                    }
                }
                Intent getfinalpage = new Intent(DetailActivity.this, ResultActivity.class);
                startActivity(getfinalpage);
            }});

    }
}
