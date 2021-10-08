package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.SearchView;
import android.os.Bundle;

public class AddVax extends AppCompatActivity {
    SearchView search;
    EditText VaxName;
    EditText VaxDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vax);

        search = findViewById(R.id.vaccSearch);
        VaxName = findViewById(R.id.vaccEditText);
        /*VaxDate = findViewById(R.id.vaccDate);*/




    }
}