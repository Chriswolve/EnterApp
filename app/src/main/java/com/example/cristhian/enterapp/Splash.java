package com.example.cristhian.enterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash);


            new Thread(new Runnable() {
                public void run() {

                    for (int i = 1; i <= 30; i++) {
                        tareaLarga();

                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            openApp();
                        }
                    });
                }
            }).start();
        }
        catch (Exception e){
            openApp();
        }

    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {}
    }
    private void openApp(){
        Intent i =  new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }




}