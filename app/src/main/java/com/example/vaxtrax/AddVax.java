package com.example.vaxtrax;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
        VaxNamestr=VaxName.getText().toString();

        Context context = getApplicationContext();
        File file = context.getExternalFilesDir(null);
        Log.d("vaxname", VaxNamestr);
    }

    public void addtoJson(String Jsonstr) throws IOException {
        Context context = getApplicationContext();
        File rootfile= context.getExternalFilesDir(null);
        File jsonfile = new File(rootfile,"VaccNameDate.json");
        FileWriter writer= new FileWriter(jsonfile);
        writer.write(Jsonstr);
        writer.close();
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
            File file = this.getExternalFilesDir(null);
            File jsonfile= new File(file,"VaccNameDate.json");
            if (jsonfile.exists()){
                // get json id
                // add and entry to json
                // read json file

            }else {
                JsonData jd = new JsonData(1,VaxNamestr,strVaxDate);
                Gson gson = new Gson();

                String jsonstr = gson.toJson(jd);
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