package com.example.vaxtrax;

import java.util.ArrayList;
import java.util.List;

public class Maa {

    private ArrayList<Rokote> list = new ArrayList<Rokote>();
    private String nimi;

    public Maa (String nimi){
        this.nimi = nimi;
    }
    public void setRokote(Rokote rokote){
        list.add(rokote);
    }
    public ArrayList<Rokote> getRokotteet(){
        return this.list;
    }
    public String getNimi(){
        return this.nimi;
    }
}

