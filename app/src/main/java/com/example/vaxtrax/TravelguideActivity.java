package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * Activity with list of countries user can choose from to see which vaccines are recommended when traveling to that country
 * @author Johanna Lavikainen
 * @version 1.0 13/10/2021
 */
public class TravelguideActivity extends AppCompatActivity {
    public static final String EXTRA = "fi.johanna.vaxtrax.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelguide);

        ListView lv = findViewById(R.id.lv_countries);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, VaccinationStorage.getInstance().getVaccinationsByCountries()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      Go to next activity when country on list clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("DBG", "onItemClick(" + i + ")");
                Intent intent = new Intent(TravelguideActivity.this, TravelVaccinationsActivity.class);
                intent.putExtra(EXTRA, i);
                startActivity(intent);
            }
        });
    }
}