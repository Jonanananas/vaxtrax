package com.example.vaxtrax;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/** Class is created to make objects out of given data
 * @author Naima Nowrin
 * @version 1.0 13/10/2021*/
public class JsonData {
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    private ArrayList<String>list;

    /**@param String name is user given vaccine name
     * @param String date is user given date of when vaccine was taken */
    public JsonData(String name, String date) {
        this.name = name;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getList() {
        return list;
    }



    @Override
    public String toString() {
        return  name ;
    }
}
