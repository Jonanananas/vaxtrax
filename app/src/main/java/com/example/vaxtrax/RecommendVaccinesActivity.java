package com.example.vaxtrax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecommendVaccinesActivity extends AppCompatActivity {
    private ArrayList recommendedVaccines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_vaccines);

        SharedPreferences prefs = getSharedPreferences("com.example.vaxtrax_userInfo", MODE_PRIVATE);
        int userAgeMonths = prefs.getInt("userAgeMonths", 780);
        Log.d("DEBUGGING", "userAgeMonths: " + userAgeMonths);
        int userAgeYears = prefs.getInt("userAge", 65);
        TextView textView_recommendedGreeting = findViewById(R.id.textView_recommendedGreeting);

        recommendedVaccines = new ArrayList();
        if(userAgeMonths >= 2)
            recommendedVaccines.add("Rotavirus");
        if(userAgeMonths >= 3) {
            recommendedVaccines.add("Pneumokokkikonjugaatti(PCV)");
            recommendedVaccines.add("Viitosrokote(DTaP-IPV-Hib)");
        }
        if(userAgeMonths >= 12)
            recommendedVaccines.add("MPR");
        if(userAgeYears >= 4)
            recommendedVaccines.add("Nelosrokote (DTaP-IPV)");
        if(userAgeYears >= 6)
            recommendedVaccines.add("MPRV ");
        if(userAgeYears >= 10)
            recommendedVaccines.add("HPV");
        if(userAgeYears >= 14)
            recommendedVaccines.add("dtap-tehoste");
        if(userAgeYears >= 45)
            recommendedVaccines.add("dT-tehoste");

        textView_recommendedGreeting.setText(
                getString(R.string.recommended_vaccines_greeting_text, userAgeYears, userAgeMonths - userAgeYears*12)
        );

        ListView lv = findViewById(R.id.listView_recommendedVaccines);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recommendedVaccines));
    }
}