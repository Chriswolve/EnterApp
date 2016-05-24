package com.example.cristhian.enterapp;


import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;


import tools.FTPManager;
import tools.GlobalClass;
import tools.PostTool;
import tools.UploadImagesService;


public class PhotoActivity extends RootActivity {



    private Uri output;
    private String foto;
    private File file;

    String Image_name="IMA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

    }


    public void cargar_click(View view)
    {
        getCamara();
    }

    private String get_random(){
        int min = 0;
        int max = 999999;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;

        Random r2 = new Random();
        int i2 = r.nextInt(max - min + 1) + min;

        return i1+""+i2;
    }

    private void getCamara(){


        Image_name += ""+get_random()+".jpg";

        foto = Environment.getExternalStorageDirectory() +"/"
                +Image_name;
        file=new File(foto);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        output = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
        startActivityForResult(intent, 1);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        globalVariable.setUrlima_fot("Image_name.jpg");
    }



    public void finalizar_click(View view){



           final ProgressDialog ringProgressDialog = ProgressDialog.show(PhotoActivity.this, "Please wait ...", "Downloading Image ...", true);

            ringProgressDialog.setCancelable(true);

            new Thread(new Runnable() {

                @Override

                public void run() {


                        try {

                            FTPManager ftp_manager = new FTPManager(PhotoActivity.this);
                            // Definir Objeto FTPClient

                            Log.i("Service", "ftp obj Created");


                            // Definir Directorio servidor
                            ftp_manager.change_directory(""); // imei del usuario
                            Log.i("Service", "Directorio Cambiado");

                            //Subir Nombre del archivo
                            String uploadFileName = Image_name;
                            Log.i("Service", "name define : " + uploadFileName);
                            //Definir ruta del archivo
                            String directory_img = "/";
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



                    ringProgressDialog.dismiss();

                }

            }).start();




        /*
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        PostTool post_tool = new PostTool();
        post_tool.SetUrl("http://www.enterappperu.com/upload.php");
        post_tool.AddParameter("query","CALL INSERTAR_REPORTE_CCO ("+globalVariable.getCCOData()+");");
        boolean result = post_tool.MakePost();
        if (result)
        {
            Toast.makeText(getApplicationContext(),
                    "Enviado", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),
                    "Hubo un problema: Revisa tu conexion...", Toast.LENGTH_SHORT).show();

        //String user_id = ((EditText) findViewById(R.id.login_dni)).getText().toString();
        //String user_pass = ((EditText) findViewById(R.id.login_pass)).getText().toString();

*/
        }
    }


