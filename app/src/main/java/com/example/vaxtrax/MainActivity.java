package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Maa argentiina = new Maa("Argentiina");
        Maa ghana = new Maa("Ghana");
        Maa portugali = new Maa("Portugali");
        Maa costarica = new Maa("Costa Rica");
        Maa japani = new Maa("Japani");


        Rokote kurkkumata = new Rokote("Kurkkumätä");
        Rokote hepatiittia = new Rokote("Hepatiitti A");
        Rokote jaykkakouristus = new Rokote("Jäykkäkouristus");
        Rokote sikotauti = new Rokote("Sikotauti");
        Rokote tuhkarokko = new Rokote("Tuhkarokko");
        Rokote vihurirokko = new Rokote("Vihurirokko");
        Rokote covid = new Rokote("Koronavirus Covid-19");
        Rokote polio = new Rokote("Polio");
        Rokote kolera = new Rokote("Kolera");
        Rokote keltakuume = new Rokote("Keltakuume");
        Rokote lavantauti = new Rokote("Lavantauti");

        argentiina.setRokote(hepatiittia);
        argentiina.setRokote(kurkkumata);
        argentiina.setRokote(jaykkakouristus);
        argentiina.setRokote(sikotauti);
        argentiina.setRokote(tuhkarokko);
        argentiina.setRokote(vihurirokko);
        argentiina.setRokote(covid);

    }


}