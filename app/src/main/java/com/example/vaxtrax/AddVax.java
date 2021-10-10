package com.example.vaxtrax;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.os.Bundle;
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
import java.util.Arrays;


public class AddVax extends AppCompatActivity {
    private String VaxNamestr;
    private static final String[] Vaxlist = new String[]{
            "DTaP","DTaP-Booster","dT","dT-Booster","Influenza"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vax);

        AutoCompleteTextView VaxName= findViewById(R.id.vaccEditText);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Vaxlist);
        VaxName.setAdapter(adapter);
        VaxName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VaxNamestr= adapterView.getItemAtPosition(i).toString();
            }
        });

    }

    public void addtoJson(String Jsonstr) throws IOException {
        File jsonfile = new File(this.getFilesDir(),"VaccNameDate.json");
        FileWriter writer= new FileWriter(jsonfile);
        writer.write(Jsonstr);
        writer.close();
    }

    public ArrayList<JsonData> readJsonfile() throws FileNotFoundException {
        File jsonfile = new File(this.getFilesDir(),"VaccNameDate.json");
        BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<JsonData>>(){}.getType();
        return gson.fromJson(reader,type);
    }


    public void Addtodatabase(View view)  {

        EditText vaxDay = findViewById(R.id.dayEditView);
        String VaxDaystr = vaxDay.getText().toString();

        EditText vaxMonth = findViewById(R.id.monthEditView);
        String VaxMonthstr = vaxMonth.getText().toString();

        EditText vaxYear = findViewById(R.id.yearEditView);
        String VaxYearstr = vaxYear.getText().toString();

        String strVaxDate= VaxDaystr+ "."+ VaxMonthstr+ "."+ VaxYearstr;

        //add Vaccine Name and date to SharedPreferences if name and date is added
        if ((!VaxDaystr.equals("") && !VaxMonthstr.equals("")&&!VaxYearstr.equals(""))){
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

                Toast.makeText(this,"Rokotus lisäättyy! Tarkistaa rokotuksen",Toast.LENGTH_LONG).show();

            }else {
                ArrayList<String> vaxlist = new ArrayList<>();
                vaxlist.add(VaxNamestr);
                vaxlist.add(strVaxDate);
                Gson gson = new Gson();

                String jsonstr = gson.toJson(vaxlist);
                try {
                    addtoJson(jsonstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this,"Rokotus lisäättyy! Tarkistaa rokotuksen",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Päivämäärä tai rokotus nimi puuttuu!", Toast.LENGTH_LONG).show();
        }




    }






}