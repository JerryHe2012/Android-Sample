package com.example.hpark4435.a01;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

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


                int j = grocery.getNumberOfItem();
                TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(j);
                theLayout.addView(theRow);
                j++;
                grocery.setNumberOfItem(j);

                int i = 0;

                Button btn_NewItemButton = new Button(theRow.getContext());
                btn_NewItemButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_NewItemButton.setId(i);
                btn_NewItemButton.setText("+");
                i++;
                theRow.addView(btn_NewItemButton);

                Button btn_NewItemButton2 = new Button(theRow.getContext());
                btn_NewItemButton2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                btn_NewItemButton2.setId(i);
                btn_NewItemButton2.setText("-");
                i++;
                theRow.addView(btn_NewItemButton2);
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
