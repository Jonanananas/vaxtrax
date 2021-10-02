package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewUserGreeting;
    SharedPreferences.Editor prefEditor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        prefEditor = prefs.edit();

//        Clear userinfo for testing purposes:
//        prefEditor.clear();
//        prefEditor.commit();

        String userFirstName = prefs.getString("userFirstName", "");
        String userLastName = prefs.getString("userLastName", "");

//      Open the user welcome screen if user info has not been defined.
        if (userFirstName == "" && userLastName == "") {
            openWelcomeActivity();
        } else {
            setContentView(R.layout.activity_main_screen);

            textViewUserGreeting = findViewById(R.id.textView_userGreeting);
            textViewUserGreeting.setText(
                    getString(R.string.main_menu_greeting_text, userFirstName, UserInfo.getInstance().getAge())
            );
        }
    }

    public void openWelcomeActivity(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
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