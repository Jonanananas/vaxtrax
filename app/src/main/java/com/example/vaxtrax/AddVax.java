package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddVax extends AppCompatActivity {
    private String VaxNamestr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vax);

        SearchView search = findViewById(R.id.searchVax);
        TextView VaxName= findViewById(R.id.vaccEditText);

        //List view add

        ArrayList<String> Vaxlist = new ArrayList<>();
        Vaxlist.add("DTaP");
        Vaxlist.add("dT");
        Vaxlist.add("Influenza");

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(Vaxlist.contains(query)){
                    VaxName.setText(query);
                    VaxNamestr=query;
                }else{
                    Toast.makeText(AddVax.this,"Rokotus ei löytyy meidän tiedostöstä",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        


    }

    public void Addtodatabase(View view){

        /*VaxName = findViewById(R.id.vaccEditText);
        String VaxNamestr= VaxName.getText().toString();*/

        EditText vaxDay = findViewById(R.id.dayEditView);
        String VaxDaystr = vaxDay.getText().toString();

        EditText vaxMonth = findViewById(R.id.monthEditView);
        String VaxMonthstr = vaxMonth.getText().toString();

        EditText vaxYear = findViewById(R.id.yearEditView);
        String VaxYearstr = vaxYear.getText().toString();

        String strVaxDate= VaxDaystr+ "."+ VaxMonthstr+ "."+ VaxYearstr;

        //add Vaccine Name and date to SharedPreferences if name and date is added
        if ((!VaxDaystr.equals("") && !VaxMonthstr.equals("") && !VaxYearstr.equals(""))){
            File SPfile= new File("/data/data/com.example.vaxtrax/shared_prefs/"+VaxNamestr+".xml");
            if (SPfile.exists()){
                Context context= getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context,VaxNamestr+" Rokotus on jo Lisäätty",duration);
                toast.show();
            }else {
                // adding to SharedPreferences
                SharedPreferences nameDate = getSharedPreferences(VaxNamestr, MODE_PRIVATE);
                SharedPreferences.Editor nameDateEdit = nameDate.edit();

                nameDateEdit.putString("Vaccine_Name",VaxNamestr);
                nameDateEdit.putString("Date_in_string", strVaxDate);
                nameDateEdit.apply();
                Log.d("Date in long formate",  strVaxDate);

                Context context= getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context,"Rokotus lisäättyy! Tarkistaa rokotuksen",duration);
                toast.show();
            }

        }else{
            Context context= getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context,"Päivämäärä puuttuu!",duration);
            toast.show();
        }




    }




}