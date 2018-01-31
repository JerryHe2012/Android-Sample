package com.example.hpark4435.a01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingGroup extends Activity {


    Button btn_GoFirstPage;
    Button btn_DetailActivity;
    String account;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_group);


        Bundle extras = getIntent().getExtras();
        TextView tv = (TextView) findViewById(R.id.txt_Account);
        tv.setText(extras.getString("account"));        // Setting up data.




        GoBack_mainPage();
        GoTo_DetailActivity();

    }

    public void GoBack_mainPage()
    {
        btn_GoFirstPage = (Button)findViewById(R.id.btn_GoFirstPage);
        btn_GoFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void GoTo_DetailActivity()
    {
        btn_DetailActivity = (Button)findViewById(R.id.btn_ToThirdPage);
        btn_DetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingGroup.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
