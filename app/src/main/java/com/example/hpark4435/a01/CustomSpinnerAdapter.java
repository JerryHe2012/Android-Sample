package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by jhe9837 on 3/13/2018.
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

    @Override
    public Object getItem(int i) {
        return countries[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        View row = inflater.inflate(R.layout.spinner_row,null);
        TextView titleTV = (TextView)row.findViewById(R.id.title);
        titleTV.setText(countries[i]);
        return row;
    }
}
