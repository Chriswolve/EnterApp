package com.example.cristhian.enterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cco_onclick(View view){
        Intent i =  new Intent(this, CCOActivity.class);
        startActivity(i);
    }

    public void salir_onclick(View view){
        setResult(RESULT_OK);
        finish();
    }
}
