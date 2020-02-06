package com.pablolopez.intentservicedownload;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

//Importante que la clase sea publica
public class IntentServicerDownload extends IntentService {

    public IntentServicerDownload() {
        super("IntentServiceDownload");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        sendMessage(intent);

    }

    private void sendMessage(Intent intent) {
        //1.Recogemos el mensajero
        Messenger messager= intent.getParcelableExtra("MESSAGER");
        //Se crea el mensaje
        Message message=Message.obtain();
        message.arg1=MainActivity.RESULT_OK;
        message.obj="/emulated/0/fichero.txt";
        try {
            messager.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
