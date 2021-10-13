package com.example.vaxtrax;
/**
 * Class for setting information to vaccine.
 * @author Johanna Lavikainen
 * @version 1.0 13/10/2021
 * @param String vaccine name of vaccine
 * @param String info information about vaccine
 */
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

