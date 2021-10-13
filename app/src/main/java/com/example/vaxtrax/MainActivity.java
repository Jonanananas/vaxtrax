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
import android.widget.TextView;

import java.io.File;

/**
 * The app's main menu screen activity. The activity has buttons which open the main screens of the
 * app's central features. Open the WelcomeActivity.java automatically instead of this activity if
 * the user's name and/or age has not been saved in SharedPreferences. If the user presses their
 * device's "Back" button when this activity is open the user is directed to the device's home screen.
 * @author Jonathan Methuen
 * @version 1.0 13/10/2021
 */
public class MainActivity extends AppCompatActivity {
    private TextView textViewUserGreeting;
    private SharedPreferences.Editor prefEditor;
    private SharedPreferences prefs;
    private String darkModeText;
    private Menu optionsMenu;
    private MenuItem darkModeSwitchButton;
    private boolean darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("com.example.vaxtrax_userInfo", MODE_PRIVATE);
        prefEditor = prefs.edit();

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
        int userAge = prefs.getInt("userAge", 0);

//      Open the user welcome screen if user info has not been defined.
        if (userFirstName.equals("")  && userLastName.equals("")) {
            openActivity(WelcomeActivity.class);
        }else {
            setContentView(R.layout.activity_main);

            textViewUserGreeting = findViewById(R.id.textView_userGreeting);
            textViewUserGreeting.setText(
                    getString(R.string.main_menu_greeting_text, userFirstName)
            );
        }
    }
    /**
     * Create an options menu button with a menu item to switch between the app's dark and light modes
     * and a menu item which brings the user to the screen where the user can change their user info.
     * @param Menu menu an object of the Menu class.
     * @return boolean returns true to display the options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        optionsMenu = menu;
        darkModeSwitchButton = optionsMenu.findItem(R.id.item_changeColoration);
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
            case R.id.item_changeUserInfo:
                openActivity(WelcomeActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
     * Open different activities based on which button was pressed or clear the user info data by pressing
     * a button which id is "button_debug_clear_sharedprefs".
     * @param View v
     */
//    Link this function to buttons and add here what the buttons do.
    public void onButtonClicked(View v) {
//        Change and save user data to SharedPreferences when enter button is pressed
        switch(v.getId()) {
            case R.id.button_debug_clear_sharedprefs:
                clearSharedPreferences();
                openActivity(WelcomeActivity.class);
                deleteJsonFile();
                break;
            case R.id.button_checkVaccines:
                Log.d("DEBUGGING", "button_checkVaccines pressed");
                openActivity(CheckVaxActivity.class);
                break;
            case R.id.button_addVaccines:
                Log.d("DEBUGGING", "button_addVaccines pressed");
                openActivity(AddVaxActivity.class);
                break;
            case R.id.button_travelGuide:
                Log.d("DEBUGGING", "button_travelGuide pressed");
                    openActivity(TravelguideActivity.class);
                break;
            case R.id.recommended_vaccines:
                Log.d("DEBUGGING", "recommended_vaccines pressed");
                    openActivity(RecommendVaccinesActivity.class);
                break;
        }
    }
    private void clearSharedPreferences() {
        prefEditor.clear();
        prefEditor.commit();
    }
//    Use this to open an activity by passing the desired activity's name
//    (e.g. "ActivityName.class") to the function as an argument.
    private void openActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void deleteJsonFile(){
        File Jsonfile = new File(this.getFilesDir(),"VaccNameDate.json");
        if (Jsonfile.exists()){
            Jsonfile.delete();
        }
    }
}