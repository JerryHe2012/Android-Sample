package com.example.hpark4435.a01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ResultActivity extends Activity {

    EditText theText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        GrocerySingleton grocery = GrocerySingleton.getInstance();
        theText = (EditText)findViewById(R.id.editText);
        theText.setText("total number:" + grocery.getNumberOfItem());
    }
}
