package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    public Button btn_Second;
    public ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




       // listView = (ListView) findViewById(R.id.listViewGame);
//        String[] values = new String[]{
//                "Start Plan",
  //              "Check Plan",
   //             "History"

      //  };
        // Defining a new adapter
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
       // listView.setAdapter(adapter); // Assign adapter to ListView
        // ListView Item Click Listener
        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //        @Override
  //          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //        int itemPosition = position;
        //        String itemValue = (String) listView.getItemAtPosition(position).toString();
        //        if(itemValue == null)
        //        {

//                }


                //Toast.makeText(getApplicationContext(), "Position: " +itemPosition + " ListItem : " + itemValue, Toast.LENGTH_LONG).show();



//            }
//        });
    }
}
