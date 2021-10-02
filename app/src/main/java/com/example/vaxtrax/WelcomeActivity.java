package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_new_user);

        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        prefEditor = prefs.edit();

//        Clear userinfo for testing purposes:
        prefEditor.clear();
        prefEditor.commit();
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
                openMainActivity();
                break;
        }
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}