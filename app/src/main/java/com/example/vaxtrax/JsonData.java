package com.example.vaxtrax;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/** Class is created to make objects out of given data
 * @author Naima Nowrin
 * @version 1.0 13/10/2021
 * @param String name, String date takes vaccine name and date as parameter*/
public class JsonData {
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    private ArrayList<String>list;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
