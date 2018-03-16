package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/* FILE         : CustomerSpinnerAdapter
 * PROG         : PROG3150 - A02
 * PROGRAMMER   : Jerry He, Kevin Park, Adam Sosnowski, Yingqi Li
 * DATE         : 2018 - 3 - 16
 * DESCRIPTION  : This java file is connecting with Spinner in the GetStoreActivity java file.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    Activity activity;
    String [] countries;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Activity activity, String[] countries){
        this.activity = activity;
        this.countries = countries;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return  countries.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    /* METHOD       : getItem
     * PARAMETER    : int i - number of item in the spinner
     * RETURN       : Object - the object value of item
     * DESCRIPTION  : this is getter of this CustomSpinner class.
     */
    @Override
    public Object getItem(int i) {
        return countries[i];
    }


    /* METHOD       : getView
       PARAMETER    : int i - the number of item that wants to see.=
        RETURN      : View - Returning row that user wants to see.
        DESCRIPTION :  This method is used for getting the number of index number user wants to see and return into View object.
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        View row = inflater.inflate(R.layout.spinner_row,null);
        TextView titleTV = (TextView)row.findViewById(R.id.title);
        titleTV.setText(countries[i]);
        return row;
    }
}
