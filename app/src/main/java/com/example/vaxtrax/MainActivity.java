package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
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

        String userFirstName =  prefs.getString("userFirstName", "");
        String userLastName =  prefs.getString("userLastName", "");

//      Open the main menu screen if user data has already been defined.
        if(userFirstName != "" && userLastName != ""){
            openMainMenu();
        }else
            setContentView(R.layout.activity_set_user_info);
    }
    public void onButtonClicked(View v) {
//        Change and save user data to SharedPreferences when enter button is pressed
        switch(v.getId()) {
            case R.id.button_enterInfo:
                Log.d("DEBUGGING", "button_enterInfo pressed");
                EditText editTextFirstName = (EditText) findViewById(R.id.editText_firstName);
                EditText editTextLastName = (EditText) findViewById(R.id.editText_lastName);

                prefEditor.putString("userFirstName", editTextFirstName.getText().toString());
                prefEditor.putString("userLastName", editTextLastName.getText().toString());

                prefEditor.commit();
                openMainMenu();
                break;
        }
    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}