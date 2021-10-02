package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    TextView textViewUserGreeting;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String userFirstName =  prefs.getString("userFirstName", "");
        String userLastName =  prefs.getString("userLastName", "");

        textViewUserGreeting = findViewById(R.id.textView_userGreeting);
        textViewUserGreeting.setText("Hi " +  userFirstName + " " +
                userLastName + ", you are a " + UserInfo.getInstance().getAge() + "-year-old.");
    }

//    Go to phone's home screen when back button is pressed
//    Credit: https://stackoverflow.com/questions/3724509/going-to-home-screen-programmatically
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}