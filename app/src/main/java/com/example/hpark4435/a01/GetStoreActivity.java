/* FILE         : GetStoreActivity.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 2 - 9
 * DESCRIPTION  : This file handles logic behind GetStoreActivity asking user to select date and store.
 *
 */

package com.example.hpark4435.a01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GetStoreActivity extends Activity {
    public Button btn_ChooseItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_store);

        final GrocerySingleton grocery = GrocerySingleton.getInstance();

        final String[] stores = getResources().getStringArray(R.array.store_name);
        final String[] storesURL = getResources().getStringArray(R.array.store_url);

        Spinner theSpinner = (Spinner)findViewById(R.id.spinner);
        final CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this, stores);
        theSpinner.setAdapter(spinnerAdapter);
        theSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                TextView description = (TextView)findViewById(R.id.textView2);
                description.setText(storesURL[i]);
                description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String link = getIntent().getStringExtra(storesURL[i]);
                        Uri viewUri = Uri.parse(link);
                        Intent viewIntent = new Intent(Intent.ACTION_VIEW, viewUri);
                        startActivity(viewIntent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                TextView description = (TextView)findViewById(R.id.textView2);
                description.setText("");
            }
        });


        btn_ChooseItem = (Button)findViewById(R.id.btn_ChooseItem);
        btn_ChooseItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try{
                    Log.i("GetStoreActivity", "Choose Item clicked");
                    Spinner theSpinner = (Spinner)findViewById(R.id.spinner);
                    String theStore = theSpinner.getSelectedItem().toString();
                    grocery.setStoreName(theStore);
                    DatePicker thePicker = (DatePicker)findViewById(R.id.datePicker);
                    String theDate = Integer.toString(thePicker.getDayOfMonth()) + " " + Integer.toString(thePicker.getMonth()) + " " + Integer.toString(thePicker.getYear());
                    grocery.setDate(theDate);
                    Intent getthirdpage = new Intent(GetStoreActivity.this, DetailActivity.class);
                    startActivity(getthirdpage);
                }
                catch(Exception e){
                    Log.e("Result Activity", e.toString());
                }
            }});
    }
}
