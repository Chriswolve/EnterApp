package com.example.cristhian.enterapp;

/**
 * Created by Cristhian on 5/17/16.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//clase definida para las animaciones
public class RootActivity extends AppCompatActivity {
    //flag
    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onStartCount = 1;
        //Se verifica que se la primera vez que inicia la actividad
        //si la instancia guardada es nula
        //caso contrario la bandera sera 2
        if (savedInstanceState == null)
        {
            //sobreescribir transicion
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);
        } else
        {
            //transicion invertida
            onStartCount = 2;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.anim_slide_in_right,
                    R.anim.anim_slide_out_right);

        } else if (onStartCount == 1) {
            onStartCount++;
        }

    }


}