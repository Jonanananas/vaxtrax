package com.example.vaxtrax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Use this activity to change the user's name and age and welcome the user if the user's info in question
 * has not been saved in SharedPreferences.
 * @author Jonathan Methuen
 * @version 1.0 13/10/2021
 */
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
    private String darkModeText;
    private boolean darkMode;

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

    //        Get the coloration mode from SharedPreferences
        darkMode = prefs.getBoolean("darkMode", true);

    //        Activate the coloration mode set in SharedPreferences
        if(darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            prefEditor.putString("darkModeText", "Aktivoi valomuoto");
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            prefEditor.putString("darkModeText", "Aktivoi pimeämuoto");
        }
        darkModeText = prefs.getString("darkModeText", "");

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
    /**
     * Change and save user data to SharedPreferences when a button with the id of "button_enterInfo"
     * is pressed.
     * @param View v the selected button.
     */
    public void onButtonClicked(View v) {
        switch(v.getId()) {
            case R.id.button_enterInfo:
                Log.d("DEBUGGING", "button_enterInfo pressed");

                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();

                int userBirthDay = -1;
                int userBirthMonth = -1;
                int userBirthYear = -1;

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
//                Create an int array which is initialized with the user's age in years in index 0 and age in
//                months in index 1.
                int[] userAgeArray = calculateAge(userBirthDay, userBirthMonth, userBirthYear);
                int userAge = userAgeArray[0];
                int userAgeMonths = userAgeArray[1];

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
                    prefEditor.putInt("userAgeMonths", userAgeMonths);

                    prefEditor.commit();
                    openMainActivity();
                }
                break;
        }
    }
    /**
     * Go to the device's home screen when the back button is pressed.
     * Credit: https://stackoverflow.com/questions/3724509/going-to-home-screen-programmatically
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    /**
     * Create an options menu button with a menu item to switch between the app's dark and light modes.
     * @param Menu menu an object of the Menu class.
     * @return boolean returns true to display the options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu_welcome_activity, menu);
        Menu optionsMenu = menu;

        MenuItem darkModeSwitchButton = optionsMenu.findItem(R.id.item_changeColoration);
        darkModeSwitchButton.setTitle(darkModeText);
        return true;
    }
    /**
     * Check which options menu button is pressed and do appropriate functions based on that.
     * @param @NonNull MenuItem the selected menu item
     * @return boolean returns true if the menu item was successfully handled. Otherwise return the superclass
     * implementation of onOptionsItemSelected() which default implementation returns false.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_changeColoration:
                if(darkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    prefEditor.putString("darkModeText", "Aktivoi pimeämuoto");
                    item.setTitle(darkModeText);
                    darkMode = false;
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    prefEditor.putString("darkModeText", "Aktivoi valomuoto");
                    item.setTitle(darkModeText);
                    darkMode = true;
                }
                prefEditor.putBoolean("darkMode", darkMode);
                prefEditor.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //    Calculate and return user age in years and months.
    private int[] calculateAge(int day, int month, int year) {
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        int age = -1;
        int months = -1;
        int[] userAgeInfo = new int[2];

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

                months = age * 12 + (today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH));

//                Check if the user has had a birthday this year
                if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
                    age--;
                } else if ((today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)) &&
                        today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
                    age--;
                }

                if(today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
                    months--;
                }
            }
        }
        userAgeInfo[0] = age;
        userAgeInfo[1] = months;
        
        return userAgeInfo;
    }
}