package com.pabloLopez.servicedownloadimage;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceDownload extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Método que se ejecutará cuando se inicie el servicio
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Se ejecuta  el codigo de un Asyntask
        new  DowloadImageTask().execute(intent.getExtras().getString("urlpath"));//le pasamos ala url donde esta la imagen que queremos descargar
        Toast.makeText(this, "Servicio iniciado.", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    //Clase interna descarga la imagen en un proceso Asynck, es decir en un proceso distinto a la de la App
    private  class DowloadImageTask extends AsyncTask<String,Long,Long>{

        @Override
        protected Long doInBackground(String... url) {
            return ImageManager.DownloadFromUrl(url[0],"imagen.jpg");
        }

        @Override
        protected void onPostExecute(Long time) {
            Toast.makeText(getApplicationContext(),"Descarga  finalizada en "+time+"milisegundos",Toast.LENGTH_SHORT);
            stopSelf();//para parar la tarea una vez termina
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servicio parado.", Toast.LENGTH_SHORT).show();
    }
}
