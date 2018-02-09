/* FILE         : GetStoreActivity.java
 * PROG         : PROG3150 - A01
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 2 - 9
 * DESCRIPTION  : This file handles logic behind GetStoreActivity asking user to select date and store.
 */

package com.example.hpark4435.a01;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

public class GetStoreActivity extends Activity {
    public Button btn_ChooseItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_store);

        final GrocerySingleton grocery = GrocerySingleton.getInstance();

        btn_ChooseItem = (Button)findViewById(R.id.btn_ChooseItem);
        btn_ChooseItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("GetStoreActivity", "Choose Item clicked");
                Spinner theSpinner = (Spinner)findViewById(R.id.spinner);
                String theStore = theSpinner.getSelectedItem().toString();
                grocery.setStoreName(theStore);
                DatePicker thePicker = (DatePicker)findViewById(R.id.datePicker);
                String theDate = Integer.toString(thePicker.getDayOfMonth()) + " " + Integer.toString(thePicker.getMonth()) + " " + Integer.toString(thePicker.getYear());
                grocery.setDate(theDate);
                Intent getthirdpage = new Intent(GetStoreActivity.this, DetailActivity.class);
                startActivity(getthirdpage);
            }});
    }

}
