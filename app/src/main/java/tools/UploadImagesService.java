package tools;

/**
 * Created by Cristhian on 5/22/16.
 */
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;



/**
 * Created by Cristhian on 4/03/15.
 * Upload File in to Web Server
 */
public class UploadImagesService extends IntentService {

    // TAG del servicio
    private static final String TAG = "UploadImagesService";
    FTPManager ftp_manager;
    String directory_img;
    String uploadFileName;




    //CONSTRUCTOR
    public UploadImagesService() {
        super(UploadImagesService.class.getName());


    }

    //EXECUTE SERVICE
    @Override
    protected void onHandleIntent(Intent intent) {

        directory_img = Environment.getExternalStorageDirectory().toString();
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        uploadFileName = globalVariable.getUrlima_fot();

        Log.d(TAG, "Service Started!");

        try {
            // Crear objeto FTPManager
            ftp_manager =  new FTPManager(this);
            Log.i("UploadImagesService:","Objeto FtpManager Creado");

            //Definir directorio del servidor
            ftp_manager.change_directory(""); // imei del usuario


            // Definir directorio imagenes
            ftp_manager.Set_local_Directory(directory_img);
            Log.i("UploadImagesService:",ftp_manager.uploadFilePath);

            // Cargar y Subir imagenes al servidor
            SendImages();

            //Cerramos la conexion
            ftp_manager.Close_connection();
        }
        catch (Exception e){
            Log.i("IntenService Error :" ,e.toString());
        }

        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }


    public void SendImages(){



        Log.i("UploadImagesService:" , "Send from -> "+ftp_manager.uploadFilePath);
        //Creo el array de tipo File con el contenido de la carpeta
        ftp_manager.SubirArchivo(uploadFileName);



    }





}
