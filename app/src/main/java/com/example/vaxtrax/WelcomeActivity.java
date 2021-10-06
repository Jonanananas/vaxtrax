package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefEditor;
    private TextView errorText;
    private TextView welcomeText;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDay;
    private EditText editTextMonth;
    private EditText editTextYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_new_user);

        errorText = findViewById(R.id.textView_errorMessage);
        welcomeText = findViewById(R.id.textView_welcome);

        prefs = getSharedPreferences("com.example.vaxtrax_userInfo", MODE_PRIVATE);
        prefEditor = prefs.edit();

        editTextFirstName = findViewById(R.id.editText_firstName);
        editTextLastName = findViewById(R.id.editText_lastName);
        editTextDay = findViewById(R.id.editTextNumber_day);
        editTextMonth = findViewById(R.id.editTextNumber_month);
        editTextYear = findViewById(R.id.editTextNumber_year);

        String userFirstName = prefs.getString("userFirstName", "");
        String userLastName = prefs.getString("userLastName", "");

//      Change the texts in the activity if user data already exists in the SharedPreferences
        if (!userFirstName.equals("") && !userLastName.equals("")) {
            welcomeText.setText("Muuta käyttäjätietojasi:");

            editTextFirstName.setText(userFirstName);
            editTextLastName.setText(userLastName);
            editTextDay.setText(Integer.toString(prefs.getInt("userBirthDay",0)));
            editTextMonth.setText(Integer.toString(prefs.getInt("userBirthMonth",0)));
            editTextYear.setText(Integer.toString(prefs.getInt("userBirthYear",0)));
        } else {
            welcomeText.setText("Tervetuloa käyttämään VaxTrax-sovellusta! \n Mikä on nimesi ja syntymäaikasi?");
        }
    }
    public void onButtonClicked(View v) {
//        Change and save user data to SharedPreferences when enter button is pressed
        switch(v.getId()) {
            case R.id.button_enterInfo:
                Log.d("DEBUGGING", "button_enterInfo pressed");

                String firstName = "";
                String lastName = "";
                firstName = editTextFirstName.getText().toString();
                lastName = editTextLastName.getText().toString();

                int userBirthDay = -1;
                int userBirthMonth = -1;
                int userBirthYear = -1;
                int userAge = -1;

                if(!editTextDay.getText().toString().equals("")) {
                    if (Integer.parseInt(editTextDay.getText().toString()) > 0)
                        userBirthDay = Integer.parseInt(editTextDay.getText().toString());
                }
                if(!editTextMonth.getText().toString().equals("")) {
                    if (Integer.parseInt(editTextMonth.getText().toString()) > 0)
                        userBirthMonth = Integer.parseInt(editTextMonth.getText().toString());
                }
                if(!editTextYear.getText().toString().equals("")) {
                    if (Integer.parseInt(editTextYear.getText().toString()) > 0)
                        userBirthYear = Integer.parseInt(editTextYear.getText().toString());
                }

                userAge = calculateAge(userBirthDay, userBirthMonth, userBirthYear);

                // Give an error, and don't let the user continue
                // to the next screen or save the user's input data if userAge is less than 0
                // or the user's input is otherwise improper.

                if(lastName.equals("") || firstName.equals("") && (userAge < 0 || userBirthDay
                    < 0 || userBirthMonth < 0 || userBirthYear < 0)) {
                    errorText.setText("Virheellinen syöte. Tarkista, että olet täyttänyt kaikki kentät!");
                    errorText.setVisibility(View.VISIBLE);
                }else if(userAge < 0 || userBirthDay < 0 || userBirthMonth < 0 || userBirthYear < 0) {
                    errorText.setText("Virheellinen syöte. Tarkista syntymäaika!");
                    errorText.setVisibility(View.VISIBLE);
                }else {
                    prefEditor.putString("userFirstName", firstName);
                    prefEditor.putString("userLastName", lastName);
                    prefEditor.putInt("userBirthDay", userBirthDay);
                    prefEditor.putInt("userBirthMonth", userBirthMonth);
                    prefEditor.putInt("userBirthYear", userBirthYear);
                    prefEditor.putInt("userAge", userAge);

                    prefEditor.commit();
                    openMainActivity();
                }
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
        int age = -1;

//        Check for proper date input
        if((month > 0 && month <= 12) && day > 0 && year > 1800 ) {
//            Initialize the birthday calendar with the user's input and the first day of the month
//            because if the user's input day goes over the month's last day the calendar will
//            change the month to the next month or some month after that.
//            Subtract 1 from the month variable because January = 0;
            birthday.set(year, month - 1, 1);

//            Get the last day of the month and check if the user input day is possible within that month
            if (birthday.getActualMaximum(Calendar.DATE) >= day) {
//                Set the calendar day to be the user input day
                birthday.set(Calendar.DAY_OF_MONTH, day);

//                Set the user age if the given date exists
                age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

//                Check if the user has had a birthday this year
                if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
                    age--;
                } else if ((today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)) &&
                        today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
                    age--;
                }
            }
        }
        return age;
    }
}