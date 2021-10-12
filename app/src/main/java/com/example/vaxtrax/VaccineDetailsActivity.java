package com.example.vaxtrax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VaccineDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);


        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        ((TextView) findViewById(R.id.VaccineNameInfo)).setText(name);
        ((TextView) findViewById(R.id.RecivedDate)).setText(date);


    }
}
