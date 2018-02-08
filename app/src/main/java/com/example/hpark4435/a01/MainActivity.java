package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    public Button btn_StartPlan;
    public Button history_button;
    public Button btn_view_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        btn_StartPlan = (Button)findViewById(R.id.btn_Start_Plan);
        btn_StartPlan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent getsecondpage = new Intent(MainActivity.this, GetStoreActivity.class);
                startActivity(getsecondpage);
            }});




        btn_view_plan = (Button)findViewById(R.id.btn_viewPlan);
        btn_view_plan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent getFinalPage = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(getFinalPage);

            }});




        history_button = (Button)findViewById(R.id.btn_history);
        history_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
            }});
    }
}


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


