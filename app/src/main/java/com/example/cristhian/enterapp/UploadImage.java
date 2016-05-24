package com.example.cristhian.enterapp;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import tools.FTPManager;

/**
 * Created by Cristhian on 5/23/16.
 */
public class UploadImage extends Service {


    int mStartMode;       // indicates how to behave if the service is killed

    boolean mAllowRebind; // indicates whether onRebind should be used


    @Override
    public void onCreate() {
        // The service is being created

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()

        Log.i("Service","running");

        try {



                // Definir Objeto FTPClient
                FTPManager ftp_manager = new FTPManager(this);
                Log.i("Service", "ftp obj Created");


                // Definir Directorio servidor
                ftp_manager.change_directory(""); // imei del usuario
                Log.i("Service", "Directorio Cambiado");

                //Subir Nombre del archivo
                String uploadFileName = "ejemplo1.jpg";
                Log.i("Service", "name define : " + uploadFileName);
                //Definir ruta del archivo
                String directory_img = Environment.getExternalStorageDirectory().toString()+"/";
                ftp_manager.Set_local_Directory(directory_img);
                Log.i("Service", "Ruta definida :" + ftp_manager.uploadFilePath);
                //Subir DB al servidor
                ftp_manager.SubirArchivo(uploadFileName);
                Log.i("Service", "Archivo Subido :" + ftp_manager.uploadFilePath);
                // Cerrar conexion FTP
                ftp_manager.Close_connection();




        }
        catch (Exception e){
            Log.i("Error :", e.toString());
        }
        mStartMode = START_NOT_STICKY;
        return mStartMode;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return mAllowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}