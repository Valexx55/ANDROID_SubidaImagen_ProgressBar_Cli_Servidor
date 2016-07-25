package com.example.vale.subidaimagenprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vale on 13/07/16.
 */
public class UpLoadImage extends AsyncTask<Bitmap, Float, String> {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;


    private static final String SERVIDOR = "192.168.1.204";
    private static final String PUERTO = "8080";
    private static final String APP = "CICERemote";
    private static final String SERVLET = "SubirImagen";


    public UpLoadImage (Context context, ProgressBar pb, TextView textView)
    {
        this.context = context;
        this.progressBar = pb;
        this.textView = textView;
    }


    @Override
    protected void onProgressUpdate(Float ... values) {
        //super.onProgressUpdate(values);

        int valor = Math.round(values[0]);
        progressBar.setProgress(valor);
        textView.setText("PROGRESO " + valor + "/100");
    }

    private String codificarImagen (Bitmap bitmap)
    {
        String imagend_coded = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        imagend_coded = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return imagend_coded;

    }


    private void enviarComoCadenaCodificadaProgress (HttpURLConnection httpURLConnection, Bitmap bitmap) throws IOException {

        OutputStreamWriter osw = null;

        try
            {

                String encodedImage = codificarImagen(bitmap);

                int escrito = 0;
                int tamanio = encodedImage.length();
                int bloque = 1024;
                int faltan = tamanio;

                osw = new OutputStreamWriter(httpURLConnection.getOutputStream());

                while (faltan >= bloque)
                {

                    osw.write(encodedImage, escrito, bloque);
                    faltan = faltan - bloque;
                    escrito = escrito + bloque;
                    publishProgress(new Float((escrito*100))/tamanio);
                }
                if (faltan > 0)
                    osw.write(encodedImage, escrito, faltan);
            }
            catch (Exception e)
            {
                Log.d(getClass().getCanonicalName(), "ERROR tx ", e);
                throw e;
            }
            finally
            {
                if (null != osw)
                    try
                    {
                        osw.close();
                    }
                    catch (Exception e)
                    {
                        Log.e(getClass().getCanonicalName(), "Error al cerrar el fichero de salida");
                    }
            }

    }



    private void enviarComoCadenaCodificadaSinProgress (HttpURLConnection httpURLConnection, Bitmap bitmap) throws IOException {

        OutputStreamWriter osw = null;

        try

        {
            String encodedImage = codificarImagen(bitmap);
            osw = new OutputStreamWriter(httpURLConnection.getOutputStream());
            osw.write(encodedImage);
        }
        catch (Exception e)
        {
            Log.d(getClass().getCanonicalName(), "ERROR tx ", e);
            throw e;
        }
        finally
        {

            if (null != osw)
                try
                {
                    osw.close();
                }
                catch (Exception e)
                {
                    Log.e(getClass().getCanonicalName(), "Error al cerrar el fichero de salida");
                }
        }
    }


    @Override
    protected String doInBackground(Bitmap... params) {

        String strdev = null;
        URL serverUrl = null;
        HttpURLConnection httpCon = null;
        FileOutputStream fos = null;


        Log.d(getClass().getCanonicalName(), "ENTRANDO EN doInBackGround  . . .");

        try

        {

            String cadena_servicio = "http://"+SERVIDOR+":"+PUERTO+"/"+APP+"/"+SERVLET;

            serverUrl = new URL(cadena_servicio);
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setRequestMethod("POST");

            Bitmap bm = params[0];

            Log.d(getClass().getCanonicalName(), "Enviando imangen  . . .");
            enviarComoCadenaCodificadaProgress(httpCon, bm);
            //enviarComoCadenaCodificadaSinProgress(httpCon, bm);

            Log.d(getClass().getCanonicalName(), "Imange enviada  . . .");

            int resp_code = httpCon.getResponseCode();

            Log.d(getClass().getCanonicalName(), "Respuesta servidor = " + resp_code);
            strdev = (resp_code == 200) ? "OK" : null;

        }
        catch (Exception e)
        {
            Log.e("Error ",  e.getMessage());
        }
        finally
        {
            if (httpCon!= null)
            {
                httpCon.disconnect();
            }

        }

        return strdev;

    }

    @Override
    protected void onPostExecute(String s) {
        Log.d (getClass().getCanonicalName(), "FIN");
        progressBar.setProgress(100);

        if (null != s)
        {
            textView.setText("COMPLETADO 100/100");
        } else
        {
            textView.setText("ERROR EN LA SUBIDA");
        }
    }
}