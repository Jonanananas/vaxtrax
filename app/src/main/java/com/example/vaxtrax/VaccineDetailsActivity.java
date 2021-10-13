package com.example.vaxtrax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**Activity shows information form CheckVax Listview
 * @author Naima Nowrin
 * @version 1.0 13/10/2021*/
public class VaccineDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        ((TextView) findViewById(R.id.VaccineNameInfo)).setText(name);
        ((TextView) findViewById(R.id.RecivedDate)).setText(date);


    }
}
