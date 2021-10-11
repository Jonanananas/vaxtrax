package com.example.vaxtrax;

public class VaccineInfo {

    private String vaccine;
    private String info;

    public VaccineInfo(String vaccine, String info) {
        this.vaccine = vaccine;
        this.info = info;
    }
    public String getVaccine() {
        return vaccine;
    }
    public String getInfo() {
        return info;
    }
    public String toString() {
        return this.vaccine;
    }
}

