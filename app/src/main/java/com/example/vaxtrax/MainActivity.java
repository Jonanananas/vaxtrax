package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_user_info);
    }
    public void onButtonClicked(View v){
        switch(v.getId()) {
            case R.id.button_enterInfo:
                Log.d("DEBUGGING", "button_enterInfo pressed");
                Intent intent = new Intent(this, MainScreen.class);
                EditText editTextFirstName = (EditText) findViewById(R.id.editText_firstName);
                EditText editTextLastName = (EditText) findViewById(R.id.editText_lastName);
                UserInfo.getInstance().setFirstName(editTextFirstName.getText().toString());
                UserInfo.getInstance().setLastName(editTextLastName.getText().toString());
                startActivity(intent);
                break;
        }
    }
}