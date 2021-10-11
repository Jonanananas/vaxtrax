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

        ((TextView) findViewById(R.id.VaccineNameInfo)).setText(VaxDataSingleton.getInstance().getName(index));
        ((TextView) findViewById(R.id.RecivedDate)).setText(VaxDataSingleton.getInstance().getDate(index));


    }
}
