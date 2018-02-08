package com.example.hpark4435.a01;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final GrocerySingleton grocery = GrocerySingleton.getInstance();
        int j = grocery.getNumberOfItem();
        TableLayout theLayout = (TableLayout)findViewById(R.id.TLfinal);
        final ProgressBar theBar = (ProgressBar)findViewById(R.id.progressBar);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int thewidth = width - 180;
        int theID = -1;

        TableRow theFirstRow = new TableRow(theLayout.getContext());
        theFirstRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        theFirstRow.setGravity(Gravity.CENTER);
        theFirstRow.setId(theID);
        theLayout.addView(theFirstRow);

        TextView newStore = new TextView(theFirstRow.getContext());
        newStore.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 60));
        newStore.setGravity(Gravity.CENTER);
        newStore.setId(theID - 1);
        newStore.setText(grocery.getStoreName());
        theFirstRow.addView(newStore);

        TableRow theSecondRow = new TableRow(theLayout.getContext());
        theSecondRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        theSecondRow.setGravity(Gravity.CENTER);
        theSecondRow.setId(theID - 2);
        theLayout.addView(theSecondRow);

        TextView newDate = new TextView(theSecondRow.getContext());
        newDate.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 60));
        newDate.setGravity(Gravity.CENTER);
        newDate.setId(theID - 3);
        newDate.setText(grocery.getDate());
        theSecondRow.addView(newDate);

        for (int i = 0; i < j; i++)
        {
            if (grocery.getQunatity(i) != 0) {
                TableRow theRow = new TableRow(theLayout.getContext());
                theRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                theRow.setId(i * 4);
                theLayout.addView(theRow);

                TextView newItem = new TextView(theRow.getContext());
                newItem.setLayoutParams(new TableRow.LayoutParams(thewidth, TableLayout.LayoutParams.WRAP_CONTENT));
                newItem.setId((i * 4) + 1);
                newItem.setText(grocery.getItemName(i));
                theRow.addView(newItem);

                TextView newItemCount = new TextView(theRow.getContext());
                newItemCount.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newItemCount.setId((i * 4) + 2);
                newItemCount.setText(Integer.toString(grocery.getQunatity(i)));
                theRow.addView(newItemCount);

                final CheckBox newCheck = new CheckBox(theRow.getContext());
                newCheck.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                newCheck.setId((i * 4) + 3);
                theRow.addView(newCheck);

                newCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Take action.
                        int totalchecked = grocery.getCheckQuantity();
                        int total = grocery.getNumberOfItem();
                        if (newCheck.isChecked()) {
                            totalchecked = totalchecked + 1;
                        } else {
                            totalchecked = totalchecked - 1;
                        }
                        grocery.setCheckQuantity(totalchecked);
                        int theProgress = (100 * totalchecked) / total;
                        theBar.setProgress(theProgress);
                    }
                });
            }
        }
    }
}
