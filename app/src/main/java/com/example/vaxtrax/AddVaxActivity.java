package com.example.vaxtrax;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
/**
 * Activity creates Json file and adds Users given Vaccine to file
 * From this activity user can check Added vaccines
 * @author Naima Nowrin
 * @version 1.0 13/10/2021*/

public class AddVaxActivity extends AppCompatActivity {
    private String VaxNamestr;
    private static final String[] Vaxlist = new String[]{
            "DTaP","DTaP-Booster","dT","dT-Booster","Influenza"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vax);

        // Autocomplete textview from given options in vaxlist
        Spinner VaxName= findViewById(R.id.vaxSpinner);
        Button Checkvex = findViewById(R.id.checkVax);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Vaxlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VaxName.setAdapter(adapter);

        VaxName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                VaxNamestr = Vaxlist[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                VaxNamestr= null;
            }
        });

        Checkvex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddVaxActivity.this, CheckVaxActivity.class);
                startActivity(intent);
            }
        });

        
    }
    /** function creates json file adds json string to file
     * @param Jsonstr Json data in string format */
    private void addtoJson(String Jsonstr) throws IOException {
        File jsonfile = new File(this.getFilesDir(),"VaccNameDate.json");
        FileWriter writer= new FileWriter(jsonfile);
        writer.write(Jsonstr);
        writer.close();
    }
    /** Function returns added data from Jsonfile
     * @return data from Json file in JsonData object format
     * JsonData is Class that creates objects out of given data*/
    private ArrayList<JsonData> readJsonfile() throws FileNotFoundException {
        File jsonfile = new File(this.getFilesDir(),"VaccNameDate.json");
        BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<JsonData>>(){}.getType();
        return gson.fromJson(reader,type);
    }

    /** Function checks if Vaccine name and date given
     * Creates JsonData object of Vaccine name and Vaccine date given
     * adds JsonData object to Array
     * Converts Array to Json
     * Adds Json to JsonFile*/
    public void Addtodatabase(View view)  {

        EditText vaxDay = findViewById(R.id.dayEditView);
        String VaxDaystr = vaxDay.getText().toString();

        EditText vaxMonth = findViewById(R.id.monthEditView);
        String VaxMonthstr = vaxMonth.getText().toString();

        EditText vaxYear = findViewById(R.id.yearEditView);
        String VaxYearstr = vaxYear.getText().toString();

        String strVaxDate= " "+VaxDaystr+ "."+ VaxMonthstr+ "."+ VaxYearstr;

        if (VaxNamestr != null && (!VaxDaystr.equals("") && !VaxMonthstr.equals("")&&!VaxYearstr.equals(""))){
            File jsonfile= new File(this.getFilesDir(),"VaccNameDate.json");
            if (jsonfile.exists()){
                try {
                    ArrayList<JsonData> jsonArray = readJsonfile();
                    jsonArray.add(new JsonData(VaxNamestr,strVaxDate));
                    Gson gson = new Gson();
                    String jsonstr = gson.toJson(jsonArray);
                    try {
                        addtoJson(jsonstr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this,"Rokotus lisätty! Tarkista rokotuksesi",Toast.LENGTH_LONG).show();
            }else {
                ArrayList<JsonData> vaxlist = new ArrayList<>();
                vaxlist.add(new JsonData(VaxNamestr,strVaxDate));
                Gson gson = new Gson();

                String jsonstr = gson.toJson(vaxlist);
                try {
                    addtoJson(jsonstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this,"Rokotus lisätty! Tarkista rokotuksesi",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Päivämäärä tai rokotuksen nimi puuttuu!", Toast.LENGTH_LONG).show();
        }






    }







}