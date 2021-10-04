package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_new_user);

        prefs = getSharedPreferences("com.example.vaxtrax", MODE_PRIVATE);
        prefEditor = prefs.edit();

//        Clear userinfo for testing purposes:
//        prefEditor.clear();
//        prefEditor.commit();
    }
    public void onButtonClicked(View v) {
//        Change and save user data to SharedPreferences when enter button is pressed
        switch(v.getId()) {
            case R.id.button_enterInfo:
                Log.d("DEBUGGING", "button_enterInfo pressed");
                EditText editTextFirstName = (EditText) findViewById(R.id.editText_firstName);
                EditText editTextLastName = (EditText) findViewById(R.id.editText_lastName);
                EditText editTextDay = (EditText) findViewById(R.id.editTextNumber_day);
                EditText editTextMonth = (EditText) findViewById(R.id.editTextNumber_month);
                EditText editTextYear = (EditText) findViewById(R.id.editTextNumber_year);

                int userBirthDay = Integer.parseInt(editTextDay.getText().toString());
                int userBirthMonth = Integer.parseInt(editTextMonth.getText().toString());
                int userBirthYear = Integer.parseInt(editTextYear.getText().toString());
                int userAge = calculateAge(userBirthDay, userBirthMonth, userBirthYear);

                //TODO: Give an error, and don't let the user continue
                // to the next screen or save the user's input data if userAge is less than 0
                // or the user's input is otherwise improper.

                prefEditor.putString("userFirstName", editTextFirstName.getText().toString());
                prefEditor.putString("userLastName", editTextLastName.getText().toString());
                prefEditor.putInt("userBirthDay", userBirthDay);
                prefEditor.putInt("userBirthMonth", userBirthMonth);
                prefEditor.putInt("userBirthYear", userBirthYear);
                prefEditor.putInt("userAge", userAge);

                prefEditor.commit();
                openMainActivity();
                break;
        }
    }
//    Go to phone's home screen when back button is pressed.
//    Credit: https://stackoverflow.com/questions/3724509/going-to-home-screen-programmatically
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//    Calculate user age.
    private int calculateAge(int day, int month, int year) {
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();

        birthday.set(year, month-1, day);
        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        if(today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
            age--;
        }else if ((today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)) &&
            today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }
}