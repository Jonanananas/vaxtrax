package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**Avtivity shows list of added vaccine
 * @author Naima Nowrin
 * @version 1.0 13/10/2021*/

public class CheckVax extends AppCompatActivity {
    private ArrayList <String> strJsonlist;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vax);

        ListView listView= findViewById(R.id.vaccineListView);
        try {
            ArrayList<JsonData> readJsonfile= readJsonfile();

            ArrayAdapter<JsonData> adapter= new ArrayAdapter <>(this,android.R.layout.simple_list_item_1,readJsonfile);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent VaccineInfoACtivity = new Intent(CheckVax.this, VaccineDetailsActivity.class);
                    VaccineInfoACtivity.putExtra("name",readJsonfile.get(i).getName());
                    VaccineInfoACtivity.putExtra("date",readJsonfile.get(i).getDate());
                    startActivity(VaccineInfoACtivity);}
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }


}