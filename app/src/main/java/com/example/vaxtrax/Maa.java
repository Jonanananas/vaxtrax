package com.example.vaxtrax;

import java.util.ArrayList;
import java.util.List;

public class Maa {

    private List<Rokote> maa;
//  Singleton
    private static final Maa ourInstance = new Maa();

    public static Maa getInstance() {
        return ourInstance;
    }
//  List of recommended vaccinations by country
    private Maa() {
        maa = new ArrayList<>();
        maa.add(new Rokote("Argentiina", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
        maa.add(new Rokote("Belgia",  "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19", " "));
        maa.add(new Rokote("Ghana", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
        maa.add(new Rokote("Intia", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
        maa.add(new Rokote("Kolumbia", "Hepatiitti A", "Jäykkäkouristus", "Kurkkumätä", "Sikotauti", "Tuhkarokko", "Vihurirokko", "Koronavirus Covid-19"));
    }

    public List<Rokote> getRokote() {
        return maa;
    }
}
