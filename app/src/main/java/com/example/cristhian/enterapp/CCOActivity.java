package com.example.cristhian.enterapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tools.GlobalClass;

public class CCOActivity extends RootActivity {

    boolean exist_error= false;
    int errorview = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cco);


        // Spinner Drop down elements
        List<String> turnos = new ArrayList<String>();
        turnos.add("Mañana");
        turnos.add("Tarde");
        turnos.add("Noche");
        SetSpinner(R.id.spin_turno,turnos);

        // Spinner Drop down elements
        List<String> Areas = new ArrayList<String>();
        Areas.add("Produccion A :Linea 04");
        Areas.add("Produccion B :Linea 07");
        Areas.add("Produccion C :Linea 03");
        Areas.add("Produccion D :PROCESOS");
        Areas.add("Supply + Logistica");
        Areas.add("Aseguramiento de Calidad");
        Areas.add("Administracion");
        Areas.add("Mantenimiento");
        Areas.add("Contratistas");
        SetSpinner(R.id.spin_areori,Areas);
        SetSpinner(R.id.spin_areobs,Areas);



    }
    private void SetSpinner (int id ,List<String> values){

        // Spinner element
        Spinner spinner = (Spinner) findViewById(id);



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    private String getValue(int id, char type){
        if (type == 'e')
            return ((EditText) findViewById(id)).getText().toString();
        if (type == 's')
            return ((Spinner) findViewById(id)).getSelectedItem().toString();

        return "";
    }

    private void SetError(int id,String message){
      ((EditText) findViewById(id)).setError( message );


    }
    private  void OnFoccus (int id){
        ((EditText) findViewById(id)).requestFocusFromTouch();
    }
    private boolean validateCheck (int id){
        return ((CheckBox) findViewById(id)).isChecked();
    }
    private String getnameCheck (int id){
        return ((CheckBox) findViewById(id)).getText().toString();
    }

    private String ValidateEPP()  {
        String result = "";
        if(validateCheck(R.id.check_epp_nousa))
            result += getnameCheck(R.id.check_epp_nousa) +", ";
        if(validateCheck(R.id.check_epp_incor))
            result += getnameCheck(R.id.check_epp_incor)+", ";
        if(validateCheck(R.id.check_epp_deter))
            result += getnameCheck(R.id.check_epp_deter)+", ";
        if(validateCheck(R.id.check_epp_refle))
            result += getnameCheck(R.id.check_epp_refle);
        if(!result.equals("")){
            String cual = getValue(R.id.edit_epp_cual,'e');
            if (!cual.equals(""))
                result = "EPP: "+cual+ "," + result;
            else
            {
                errorview = R.id.edit_epp_cual;
                SetError(R.id.edit_epp_cual,"Debe ingresar este campo");
                exist_error = true;
            }
        }
        else
            SetError(R.id.edit_epp_cual,null);
        return result;
    }

    private String ValidateMaquina(){
        String result = "";
        if(validateCheck(R.id.check_maq_veloc))
            result += getnameCheck(R.id.check_maq_veloc) +", ";
        if(validateCheck(R.id.check_maq_disp))
            result += getnameCheck(R.id.check_maq_disp)+", ";
        if(validateCheck(R.id.check_maq_intro))
            result += getnameCheck(R.id.check_maq_intro)+", ";
        if(validateCheck(R.id.check_maq_opera))
            result += getnameCheck(R.id.check_maq_opera)+", ";
        if(validateCheck(R.id.check_maq_defec))
            result += getnameCheck(R.id.check_maq_defec);

        return result;
    }

    private String ValidateRiesgos(){
        String result = "";
        if(validateCheck(R.id.check_exp_golpe))
            result += getnameCheck(R.id.check_exp_golpe) +", ";
        if(validateCheck(R.id.check_exp_alcan))
            result += getnameCheck(R.id.check_exp_alcan)+", ";
        if(validateCheck(R.id.check_exp_atasc))
            result += getnameCheck(R.id.check_exp_atasc)+", ";
        if(validateCheck(R.id.check_exp_caida))
            result += getnameCheck(R.id.check_exp_caida)+", ";
        if(validateCheck(R.id.check_exp_quema))
            result += getnameCheck(R.id.check_exp_quema)+", ";
        if(validateCheck(R.id.check_exp_choque))
            result += getnameCheck(R.id.check_exp_choque)+", ";
        if(validateCheck(R.id.check_exp_inhalar))
            result += getnameCheck(R.id.check_exp_inhalar);

        return result;
    }

    private String ValidateHerramientas(){
        String result = "";
        if(validateCheck(R.id.check_her_impro))
            result += getnameCheck(R.id.check_her_impro) +", ";
        if(validateCheck(R.id.check_her_incor))
            result += getnameCheck(R.id.check_her_incor)+", ";
        if(validateCheck(R.id.check_her_inade))
            result += getnameCheck(R.id.check_her_inade)+", ";
        if(validateCheck(R.id.check_her_malest))
            result += getnameCheck(R.id.check_her_malest);


        return result;
    }

    private String ValidateBeneficio(){
        String result = "";
        if(validateCheck(R.id.check_ahorrotiempo))
            result += getnameCheck(R.id.check_ahorrotiempo) +", ";
        if(validateCheck(R.id.check_menosesfuer))
            result += getnameCheck(R.id.check_menosesfuer)+", ";
        if(validateCheck(R.id.check_mascomodidad))
            result += getnameCheck(R.id.check_mascomodidad)+", ";

        return result;
    }
    private String ValidateOtros(){
        String result = "";
        if(validateCheck(R.id.check_otro_jugar))
            result += getnameCheck(R.id.check_otro_jugar) +", ";
        if(validateCheck(R.id.check_otro_tabler))
            result += getnameCheck(R.id.check_otro_tabler)+", ";
        if(validateCheck(R.id.check_otro_extin))
            result += getnameCheck(R.id.check_otro_extin)+", ";
        if(validateCheck(R.id.check_otro_dormi))
            result += getnameCheck(R.id.check_otro_dormi)+", ";
        if(validateCheck(R.id.check_otro_hablar))
            result += getnameCheck(R.id.check_otro_hablar)+", ";

        if(validateCheck(R.id.check_otro_alcohol))
            result += getnameCheck(R.id.check_otro_alcohol)+", ";
        if(validateCheck(R.id.check_otro_electro))
            result += getnameCheck(R.id.check_otro_electro)+", ";
        if(validateCheck(R.id.check_otro_correr))
            result += getnameCheck(R.id.check_otro_correr)+", ";

        String otrodes = getValue(R.id.edit_otro_desvio,'e');
        if (!otrodes.equals(""))
            result += "Desvio no descrito  (" + otrodes+")"  ;


        return result;
    }

    private String ValidateTarea(){
        String result = "";
        if(validateCheck(R.id.check_manipulacion))
            result += getnameCheck(R.id.check_manipulacion) +",";
        if(validateCheck(R.id.check_inspeccion))
            result += getnameCheck(R.id.check_inspeccion)+",";
        if(validateCheck(R.id.check_operacion))
            result += getnameCheck(R.id.check_operacion)+",";
        if(validateCheck(R.id.check_mantenimiento))
            result += getnameCheck(R.id.check_mantenimiento)+",";
        if(validateCheck(R.id.check_lubricacion))
            result += getnameCheck(R.id.check_lubricacion)+",";
        if(validateCheck(R.id.check_desplazamiento))
            result += getnameCheck(R.id.check_desplazamiento)+",";
        if(validateCheck(R.id.check_limpieza))
            result += getnameCheck(R.id.check_limpieza)+",";
        if(validateCheck(R.id.check_rutinaria))
            result += getnameCheck(R.id.check_rutinaria);

        return result;
    }

    public void return_onclick(View view){

        exist_error = false;
        errorview = 0;
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        String value = "";

        try {



            value =  this.getValue(R.id.edit_nombre,'e');
                if (value.equals("")) {
                    SetError(R.id.edit_nombre, "Este campo es obligatorio");
                    exist_error = true;
                    if (errorview == 0)
                        errorview = R.id.edit_nombre;
                }

            globalVariable.setNomtra_rep(value);


            value = this.getValue(R.id.edit_sacdni,'e');
            if (value.equals("")) {
                SetError(R.id.edit_sacdni, "Este campo es obligatorio");
                exist_error = true;
                if (errorview == 0)
                    errorview = R.id.edit_sacdni;
            }


            globalVariable.setCodtra_rep(value);

            value = this.getValue(R.id.spin_turno,'s');
            globalVariable.setTurtra_rep(value);

            value = this.getValue(R.id.spin_areori,'s');
            globalVariable.setAreori_rep(value);

            value = this.getValue(R.id.spin_areobs,'s');
            globalVariable.setAreobs_rep(value);

            value = this.ValidateEPP();
            if (!value.equals("")) {
                globalVariable.setCatdes_cco("EPP |");
                globalVariable.setRazdes_cco(value+" | ");
            }

            value = this.ValidateMaquina();
            if (!value.equals("")) {
                globalVariable.setCatdes_cco(globalVariable.getCatdes_cco()+"Maquina/Equipo | ");
                globalVariable.setRazdes_cco(globalVariable.getRazdes_cco()+ "Maquina/Equipo: "+value+" | ");
            }

            value = this.ValidateRiesgos();
            if (!value.equals("")) {
                globalVariable.setCatdes_cco(globalVariable.getCatdes_cco()+"Exposicion a riesgos | ");
                globalVariable.setRazdes_cco(globalVariable.getRazdes_cco()+ "Exposicion a riesgos : "+value+" | ");
            }

            value = this.ValidateHerramientas();
            if (!value.equals("")) {
                globalVariable.setCatdes_cco(globalVariable.getCatdes_cco()+"Herramientas y equipos inadecuados | ");
                globalVariable.setRazdes_cco(globalVariable.getRazdes_cco()+ "Herramientas y equipos inadecuados : "+value+" | ");
            }

            value = this.ValidateOtros();
            if (!value.equals("")) {
                globalVariable.setCatdes_cco(globalVariable.getCatdes_cco()+"Otros ");
                globalVariable.setRazdes_cco(globalVariable.getRazdes_cco()+ "Otros : "+value);
            }




            value =  this.getValue(R.id.edit_descrip,'e');
                if (value.equals("")) {
                    SetError(R.id.edit_descrip, "Este campo es obligatorio");
                    exist_error = true;
                    if (errorview == 0)
                        errorview = R.id.edit_descrip;
                }
            globalVariable.setDes_rep(value);


            value = this.ValidateTarea();
            if (!value.equals("")) {
                globalVariable.setTarrea_cco(value);

            }

            value = this.ValidateBeneficio();
            if (!value.equals("")) {
                globalVariable.setBentra_cco(value);

            }

            value =  this.getValue(R.id.edit_accionsug,'e');
            globalVariable.setAccsug_cco(value);

            if(exist_error)
                throw new Exception("Hay campos incorrectos");

            Log.i("CCOActivity",globalVariable.getCCOData());

            Intent i =  new Intent(this, PhotoActivity.class);
            startActivity(i);

        }
        catch (Exception e){
            if (errorview != 0)
                OnFoccus(errorview);
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }




    }


}
