package com.example.talal.androidlabs;



        import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        buttonLogin = (Button) findViewById(R.id.button2);
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveLogin();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        textViewEmail = (TextView) findViewById(R.id.editText);

        loadSavedLogin();


        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    private void loadSavedLogin() {
        SharedPreferences preferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        Editor editor = preferences.edit();
        emailStored = preferences.getString(STORED_EMAIL, emailStored);
        editor.commit();
        textViewEmail.setText(emailStored);
    }

    private void saveLogin() {
        SharedPreferences preferences = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        emailStored = textViewEmail.getText().toString();
        editor.putString(STORED_EMAIL,emailStored);
        editor.commit();
    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }

    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.

        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");

    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    protected static final String ACTIVITY_NAME = "LoginActivity";

    //private static final String EMAILSTOREDSETTING = "com.example.victo.lab1.email_stored";
    public static final String SETTINGS = "com.example.victo.lab1.settings";
    public static final String STORED_EMAIL = "storedEmail";
    //public static final String STORED_EMAIL = "com.example.victo.lab1.storedEmail";
    //SharedPreferences preferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
    //Editor editor = preferences.edit();

    private Button buttonLogin;
    private String emailStored = "email@domain.com";
    private TextView textViewEmail;


}