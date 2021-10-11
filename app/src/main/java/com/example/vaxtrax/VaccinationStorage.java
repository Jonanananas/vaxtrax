package com.example.vaxtrax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaccinationStorage {

    private List<VaccinationsByCountry> vaccinationsByCountries;
//  Singleton
    private static final VaccinationStorage ourInstance = new VaccinationStorage();
//  Map for vaccines and some information about them
    private Map<String, VaccineInfo> vaccineInfoMap = new HashMap<>();
//  Initializing vaccineInfoMap
    private void initVaccinationInfo(){
        vaccineInfoMap.put("Hepatiitti A", new VaccineInfo("Hepatiitti A", "Hepatiitti A, aiemmin tunnettu nimellä keltatauti, on viruksen aiheuttama tarttuva akuutti maksatulehdus. Rokote kuuluu kansallisessa rokotusohjelmassa vain riskiryhmille."));
        vaccineInfoMap.put("Jäykkäkouristus", new VaccineInfo("Jäykkäkouristus", "Tauti, jonka aiheuttaa maaperässä yleisesti esiintyvä bakteeri. Rokote kuuluu kansalliseen rokotusohjelmaan yhdistelmärokotteen osana."));
        vaccineInfoMap.put("Kurkkumätä", new VaccineInfo("Kurkkumätä", "Bakteerin aiheuttama tulehdus. Bakteerin tuottama myrkky eli toksiini voi vaurioittaa hermoja ja sydänlihasta. Kurkkumätärokotteen sisältävä yhdistelmärokote kuuluu kansalliseen rokotusohjelmaan."));
        vaccineInfoMap.put("Sikotauti", new VaccineInfo("Sikotauti", "Virustauti, jonka oireita ovat kuumeilu, päänsärky, ruokahaluttomuus, tois- tai molemminpuolinen korvanalussylkirauhasten kivulias turvotus ja siitä johtuva turvotus kaulan alueella. Tuhkarokolta, sikotaudilta ja vihurirokolta suojaava MPR-yhdistelmärokote kuuluu kansalliseen rokotusohjelmaan."));
        vaccineInfoMap.put("Tuhkarokko", new VaccineInfo("Tuhkarokko", "Viruksen aiheuttama infektio, joka alkaa usein hengitystieinfektion oirein ja ensioireiden jälkeen vartalolla todetaan leviävä ihottuma. Pelätyin tuhkarokon komplikaatio on aivotulehdus. Tuhkarokolta, sikotaudilta ja vihurirokolta suojaava MPR-yhdistelmärokote kuuluu kansalliseen rokotusohjelmaan."));
        vaccineInfoMap.put("Vihurirokko", new VaccineInfo("Vihurirokko", "Viruksen aiheuttama, usein lievä infektiotauti, joka voi kuitenkin olla vaarallinen raskauden aikana. Tuhkarokolta, sikotaudilta ja vihurirokolta suojaava MPR-yhdistelmärokote kuuluu kansalliseen rokotusohjelmaan."));
        vaccineInfoMap.put("Koronavirus Covid-19", new VaccineInfo("Koronavirus Covid-19", "SARS-CoV2 -viruksen aiheuttama äkillinen hengitystie-infektio, jonka voimakkuus voi vaihdella oireettomasta erittäin vakavaan. Rokote koronavirusta vastaan tarjotaan koko väestölle, mutta aluksi tietyssä järjestyksessä lääketieteellisin perustein."));
    }

    public static VaccinationStorage getInstance() {
        return ourInstance;
    }
//  List of recommended vaccinations by country
    private VaccinationStorage() {
        initVaccinationInfo();
        vaccinationsByCountries = new ArrayList<>();
        vaccinationsByCountries.add(new VaccinationsByCountry("Argentiina", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Belgia", this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Brasilia", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("El Salvador", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Ghana", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Intia", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Japani", this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));
        vaccinationsByCountries.add(new VaccinationsByCountry("Kolumbia", this.vaccineInfoMap.get("Hepatiitti A"), this.vaccineInfoMap.get("Jäykkäkouristus"), this.vaccineInfoMap.get("Kurkkumätä"), this.vaccineInfoMap.get("Sikotauti"), this.vaccineInfoMap.get("Tuhkarokko"), this.vaccineInfoMap.get("Vihurirokko"), this.vaccineInfoMap.get("Koronavirus Covid-19")));

    }
    public List<VaccinationsByCountry> getVaccinationsByCountries(){
        return vaccinationsByCountries;
    }
}
