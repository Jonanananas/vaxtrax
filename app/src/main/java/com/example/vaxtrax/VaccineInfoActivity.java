package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
// Information about the vaccine the user clicked on
//Vaccine information source:
//https://www.rokote.fi/matkailijat/rokotteilla-ehkaistavat-taudit/
public class VaccineInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        String info = b.getString("info");
        TextView rokotenimi = findViewById(R.id.tv_vaccine_name);
        TextView rokoteinfo = findViewById(R.id.tv_vaccine_info);
        rokotenimi.setText(name);
        rokoteinfo.setText(info);
    }
}