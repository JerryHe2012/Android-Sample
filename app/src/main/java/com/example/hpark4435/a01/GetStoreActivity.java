package com.example.hpark4435.a01;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class GetStoreActivity extends Activity {
    public Button btn_ChooseItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_store);

        btn_ChooseItem = (Button)findViewById(R.id.btn_ChooseItem);
        btn_ChooseItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent getthirdpage = new Intent(GetStoreActivity.this, DetailActivity.class);
                startActivity(getthirdpage);
            }});
    }

}
