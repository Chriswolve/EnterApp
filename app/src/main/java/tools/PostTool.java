package tools;


import android.os.StrictMode;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Cristhian on 5/21/16.
 *
 * In biuld.gradle (Module: app) verified httpcomponents
 *
 *
 dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.3.0'
    compile 'com.android.support:support-v4:23.3.0'
    compile 'com.android.support:recyclerview-v7:23.3.0'


    compile 'org.apache.httpcomponents:httpcore:4.4.1'
    compile 'org.apache.httpcomponents:httpclient:4.5'
 }
 */


public class PostTool {

    private String url;
    private ArrayList<String[]> parameters;

    private String[] result;



    public PostTool(){
        this.url = "" ;
        this.parameters= new ArrayList<String[]>();

    }

    public void AddParameter(String id,String value){
        String[] parameter = {id,value};
        this.parameters.add(parameter);
    }


    public void SetUrl ( String posturl){
        this.url = posturl;
    }

    private BasicNameValuePair getParameter(String[] pair)
    {
        Log.d("Posttool: ","Parametro: "+pair[0]+","+pair[1]);
        return new BasicNameValuePair(pair[0],pair[1]);
    }

    private List<NameValuePair> AddPararms(){
        List<NameValuePair> params= new ArrayList<NameValuePair>();
        for (int i =0 ; i<parameters.size(); i++)

            params.add(getParameter(parameters.get(i)));

        return params;
    }
    public boolean MakePost(){

        // ="http://coneisc.pe/WebService/Validate.php";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            HttpClient httpc=new DefaultHttpClient();
            HttpPost httppost=new HttpPost(this.url);
            httppost.setEntity((new UrlEncodedFormEntity(this.AddPararms())));
            Log.d("Posttool: ","Parametros agregados");

            HttpResponse resp=httpc.execute(httppost);
            HttpEntity ent=resp.getEntity();
            String text= EntityUtils.toString(ent);

            Log.d("Posttool:","Return Server: "+text);

            if(text.equals("0") ){
                throw new Exception();
            }


        }catch (Exception e){
            Log.d("Posttool:",e.toString());
            return false;
        }



        return true;


    }


}
