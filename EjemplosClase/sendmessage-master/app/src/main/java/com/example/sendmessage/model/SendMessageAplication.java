package com.example.sendmessage.model;

import android.app.Application;
import android.util.Log;

/**
 * <h1>Como usar el objeto Singleton de la clase Application</h1>
 *<ol>
 *     <li>Creamos una clase que  herede de la super clase Application
 *     <li>Añadimos al Manifiest la clase android:name=".model.SendMessageAplication"</li>
 *</ol>
 * @author Pablo López Santana
 * @version 1.0
 * @see android.app.Application
*/
public class SendMessageAplication extends Application {

    private  User user;

    public User getUser() {
        return user;
    }

    private  String TAG ="SendMessageAplication";
    /*Este evento se ejecuta siempre al iniciar la aplicacion, es lo primero que hace el sistema generar la instancia de APPLICATION  */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"APLICATION onCreate()");
        user  = new User("Pablo Lopez","123");
    }

    /*A diferencia de la  documentacion de Google, el cual dice que este evento se ejecuta al terminar la aplicacion.
    No es cierto  hasta dia de hoy no se ejecuta.*/
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG,"APLICATION onTerminate()");
    }
}
