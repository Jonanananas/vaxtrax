package com.example.vaxtrax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewUserGreeting;
    private SharedPreferences.Editor prefEditor;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("com.example.vaxtrax", MODE_PRIVATE);
        prefEditor = prefs.edit();

        String userFirstName = prefs.getString("userFirstName", "");
        String userLastName = prefs.getString("userLastName", "");
        int userAge = prefs.getInt("userAge", 0);

//      Open the user welcome screen if user info has not been defined.
        if (userFirstName.equals("")  && userLastName.equals("")) {
            openActivity(WelcomeActivity.class);
        } else {
            setContentView(R.layout.activity_main);

            textViewUserGreeting = findViewById(R.id.textView_userGreeting);
            textViewUserGreeting.setText(
                    getString(R.string.main_menu_greeting_text, userFirstName, userAge)
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        openActivity(WelcomeActivity.class);
        return super.onOptionsItemSelected(item);
    }
//    Use this to open an activity by passing the desired activity's name
//    (e.g. "ActivityName.class") to the function as an argument.
    public void openActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
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
//    Link this function to buttons and add here what the buttons do.
    public void onButtonClicked(View v) {
//        Change and save user data to SharedPreferences when enter button is pressed
        switch(v.getId()) {
            case R.id.button_debug_clear_sharedprefs:
                clearSharedPreferences();
                openActivity(WelcomeActivity.class);
                break;
            case R.id.button_checkVaccines:
                Log.d("DEBUGGING", "button_checkVaccines pressed");
//                TODO: go to vaccine list activity.
                break;
            case R.id.button_addVaccines:
                Log.d("DEBUGGING", "button_addVaccines pressed");
//                TODO: go to add vaccines activity.
                break;
            case R.id.button_travelGuide:
                Log.d("DEBUGGING", "button_travelGuide pressed");
//                TODO: go to travel guide activity.
                break;
        }
    }
    public void clearSharedPreferences(){
        prefEditor.clear();
        prefEditor.commit();
    }
}