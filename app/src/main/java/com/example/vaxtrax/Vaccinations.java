package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Vaccinations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinations);
        Bundle b = getIntent().getExtras();
        int i = b.getInt(Travelguide.EXTRA, 0);
        ListView listView = findViewById(R.id.lv_vaccines);
        listView.setAdapter(new ArrayAdapter<Rokote>(
                this,
                R.layout.activity_vaccinations,
                Maa.getInstance().getRokote())
        );

    }
}