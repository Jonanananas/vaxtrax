package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.os.Bundle;

public class AddVax extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vax);
        SearchView search = findViewById(R.id.vaccSearch);
    }
}