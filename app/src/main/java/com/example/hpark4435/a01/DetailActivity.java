package com.example.hpark4435.a01;

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

public class DetailActivity extends Activity {
    public Button btn_AddButton;
    public Button btn_SavePlan;




    GrocerySingleton grocery = GrocerySingleton.getInstance();

   // GrocerySingleton grocerySingleton = new GrocerySingleton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int quantity = 0;
        final TableLayout theLayout = (TableLayout)findViewById(R.id.LLgoods);
        grocery.setNumberOfItem(0);
        btn_AddButton = (Button)findViewById(R.id.btn_AddButton);
        btn_SavePlan = (Button)findViewById(R.id.btn_SavePlan);
        btn_AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                int j = grocery.getNumberOfItem();
                grocery.setQuantity();
                int thewight = theLayout.getWidth();
                thewight = thewight - 380;
                TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(j * 5);
                theLayout.addView(theRow);

                EditText newItem = new EditText(theRow.getContext());
                newItem.setLayoutParams(new TableRow.LayoutParams(thewight, TableRow.LayoutParams.WRAP_CONTENT));
                newItem.setId((j * 5) + 1);
                newItem.setText("New Item");
                theRow.addView(newItem);

                final Button btn_PlusButton = new Button(theRow.getContext());
                btn_PlusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_PlusButton.setId((j * 5) + 2);
                btn_PlusButton.setText("+");
                theRow.addView(btn_PlusButton);

                TextView newCount = new TextView(theRow.getContext());
                newCount.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newCount.setId((j * 5) + 3);
                newCount.setText(Integer.toString(grocery.getQunatity()));
                theRow.addView(newCount);

                final Button btn_MinusButton = new Button(theRow.getContext());
                btn_MinusButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_MinusButton.setId((j * 5) + 4);
                btn_MinusButton.setText("-");
                theRow.addView(btn_MinusButton);

                btn_PlusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        grocery.setQuantityPlus();
                        int i = btn_PlusButton.getId();
                        TextView theCount = (TextView)findViewById(i + 1);
                        theCount.setText(Integer.toString(grocery.getQunatity()));
                    }
                });

                btn_MinusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        grocery.setQuantityMinus();
                        int i = btn_MinusButton.getId();
                        TextView theCount = (TextView)findViewById(i - 1);
                        theCount.setText(Integer.toString(grocery.getQunatity()));
                    }
                });

                j++;
                grocery.setNumberOfItem(j);
            }});

        btn_SavePlan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent getfinalpage = new Intent(DetailActivity.this, ResultActivity.class);
                startActivity(getfinalpage);
            }});

    }
}
