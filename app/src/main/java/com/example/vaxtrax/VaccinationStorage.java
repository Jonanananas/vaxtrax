package com.example.vaxtrax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaccinationStorage {

    private List<VaccinationsByCountry> vaccinationsByCountries;
//  Singleton
    private static final VaccinationStorage ourInstance = new VaccinationStorage();
    private Map<String, VaccineInfo> vaccineInfoMap = new HashMap<>();
    private void initVaccinationInfo(){
        vaccineInfoMap.put("Hepatiitti A", new VaccineInfo("Hepatiitti A", ""));
        vaccineInfoMap.put("Jäykkäkouristus", new VaccineInfo("Jäykkäkouristus", ""));
        vaccineInfoMap.put("Kurkkumätä", new VaccineInfo("Kurkkumätä", ""));
        vaccineInfoMap.put("Sikotauti", new VaccineInfo("Sikotauti", ""));
        vaccineInfoMap.put("Tuhkarokko", new VaccineInfo("Tuhkarokko", ""));
        vaccineInfoMap.put("Vihurirokko", new VaccineInfo("Vihurirokko", ""));
        vaccineInfoMap.put("Koronavirus Covid-19", new VaccineInfo("Koronavirus Covid-19", ""));
    }

    public static VaccinationStorage getInstance() {
        return ourInstance;
    }
//  List of recommended vaccinations by country
    private VaccinationStorage() {
        initVaccinationInfo();
        vaccinationsByCountries = new ArrayList<>();
        vaccinationsByCountries.add(new VaccinationsByCountry("Argentiina", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus")));
/*        vaccinationsByCountries.add(new VaccinationsByCountry("Belgia",  "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19", " "));
        vaccinationsByCountries.add(new VaccinationsByCountry("Ghana", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
        vaccinationsByCountries.add(new VaccinationsByCountry("Intia", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
        vaccinationsByCountries.add(new VaccinationsByCountry("Kolumbia", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
    */
    }

    public List<VaccinationsByCountry> getVaccinationsByCountries(){
        return vaccinationsByCountries;
    }
}
