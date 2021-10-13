package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Activity with list of vaccines recommended for each country clicked in the previous activity.
 * The vaccine names can be clicked to get information about them.
 * @author Johanna Lavikainen
 * @version 1.0 13/10/2021
 */


public class TravelVaccinationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_vaccinations);
        Bundle b = getIntent().getExtras();
        int i = b.getInt(TravelguideActivity.EXTRA, 0);
        ((TextView)findViewById(R.id.tv_country))
                .setText(VaccinationStorage.getInstance().getVaccinationsByCountries().get(i).getCountry());
        ListView lv = findViewById(R.id.lv_vaccinationsList);
        ArrayList<VaccineInfo> vaccinations = VaccinationStorage.getInstance().getVaccinationsByCountries().get(i).getVaccinations();
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vaccinations));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Click vaccination on list to go to vaccine info
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TravelVaccinationsActivity.this, VaccineInfoActivity.class);
                VaccineInfo info = vaccinations.get(i);
                intent.putExtra("name", info.getVaccine());
                intent.putExtra("info", info.getInfo());
                startActivity(intent);
            }
        });
    }
}
