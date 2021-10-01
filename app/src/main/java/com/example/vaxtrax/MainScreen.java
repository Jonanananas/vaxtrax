package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    TextView textViewUserGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        textViewUserGreeting = findViewById(R.id.textView_userGreeting);

//        Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        textViewUserGreeting.setText("Hi " +  UserInfo.getInstance().getFirstName() + " " +
                UserInfo.getInstance().getLastName() + ", you are a " + UserInfo.getInstance().getAge() + "-year-old.");

    }
}