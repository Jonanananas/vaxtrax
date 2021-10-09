package com.example.vaxtrax;

public class JsonData {

    private int key;
    private String name;
    private String date;

    public JsonData(int key, String name, String date) {
        this.key = key;
        this.name = name;
        this.date = date;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return key + name + date;
    }
}
