package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Travelguide extends AppCompatActivity {
    public static final String EXTRA = "fi.johanna.vaxtrax.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelguide);


        ListView lv = findViewById(R.id.lv_maat);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Maa.getInstance().getRokote()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("DBG", "onItemClick(" + i + ")");
                Intent intent = new Intent(Travelguide.this, TravelVaccinations.class);
                intent.putExtra(EXTRA, i);
                startActivity(intent);
            }
        });





    }
}