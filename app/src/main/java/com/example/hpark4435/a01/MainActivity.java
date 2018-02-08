// File:        MainActivity.java
// Project:     Android-A01
// Date:        Feb. 8 2018
// Programmer:  Jianqing He, Adam Sosnowski, Hyunbin Park, YingQi Li
// Description: This is the first main page of the Android Application.
//              It is used to start the planning and check the plan that already made.
//              (Currently only support one plan. All information will be delete after the application is closed.)
package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends Activity {
    public Button btn_StartPlan;    // button for start plan
    public Button history_button;   // button for check the history
    public Button btn_view_plan;    // button for check the plan already made

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // set up the page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allow the start plan button to get the start plan page
        btn_StartPlan = (Button)findViewById(R.id.btn_Start_Plan);
        btn_StartPlan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("Main Activity", "creating the get store activity page");
                Intent getsecondpage = new Intent(MainActivity.this, GetStoreActivity.class);
                startActivity(getsecondpage);
            }});

        // allow the view plan button to get to the page with made plan
        btn_view_plan = (Button)findViewById(R.id.btn_viewPlan);
        btn_view_plan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("Main Activity", "creating the result activity page");
                Intent getFinalPage = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(getFinalPage);

            }});

        // tell the user it is not implemented yet when the history button clicked
        history_button = (Button)findViewById(R.id.btn_history);
        history_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("Main Activity", "informing the user that history is not implemented yet");
                Toast.makeText(getApplicationContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
            }});
    }
}