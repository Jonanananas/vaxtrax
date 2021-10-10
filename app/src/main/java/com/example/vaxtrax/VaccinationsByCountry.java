package com.example.vaxtrax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationsByCountry {

    private String country;
    private ArrayList<VaccineInfo> vaccinations;

    public String getCountry() {
        return this.country;
    }
    public VaccinationsByCountry(String country, VaccineInfo ...name) {
        this.country = country;
        List <VaccineInfo> vaccinationsList = Arrays.asList(name);
        this.vaccinations = new ArrayList<VaccineInfo>(vaccinationsList);
    }
    public ArrayList<VaccineInfo> getVaccinations() {
        return this.vaccinations;
    }

    public String toString(){
        return this.country;
    }



}

