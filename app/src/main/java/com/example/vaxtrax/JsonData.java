package com.example.vaxtrax;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
        return  name + date;
    }
}
