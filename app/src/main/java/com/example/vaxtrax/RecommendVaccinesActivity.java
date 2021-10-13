package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An activity which displays the recommended vaccines which the user should have at their age.
 * Vaccines information source:
 * https://thl.fi/fi/web/infektiotaudit-ja-rokotukset/tietoa-rokotuksista/kansallinen-rokotusohjelma/rokotusohjelma-lapsille-ja-aikuisille#rokotusohjelma
 * @author Jonathan Methuen
 * @version 1.0 13/10/2021
 */
public class RecommendVaccinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_vaccines);

        SharedPreferences prefs = getSharedPreferences("com.example.vaxtrax_userInfo", MODE_PRIVATE);
        TextView textView_recommendedGreeting = findViewById(R.id.textView_recommendedGreeting);
        int userAgeMonths = prefs.getInt("userAgeMonths", 780);
        int userAgeYears = prefs.getInt("userAge", 65);

//        Determine the displayed recommended vaccines based on the user's age
        ArrayList<String> recommendedVaccines = new ArrayList<>();
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
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recommendedVaccines));
    }
    /**
     * Go back to the main menu screen
     * @param View v the selected button.
     */
    public void onButtonClicked(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}