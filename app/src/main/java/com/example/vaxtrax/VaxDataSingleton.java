package com.example.vaxtrax;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class VaxDataSingleton {
    private ArrayList<JsonData> jd;
    private static final VaxDataSingleton ourInstance = new VaxDataSingleton();

    public static VaxDataSingleton getInstance() {
        return ourInstance;
    }
    private VaxDataSingleton(){
        @SuppressLint("SdCardPath") File jsonfile = new File("/data/data/com.example.vaxtrax/files","VaccNameDate.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonfile));
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<JsonData>>(){}.getType();
            jd = gson.fromJson(reader,type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<JsonData> getJsonData() {
        return jd;
    }
    public String getName(int i) {
        return jd.get(i).getName();
    }

    public String getDate(int i) {
        return jd.get(i).getDate();
    }
}
