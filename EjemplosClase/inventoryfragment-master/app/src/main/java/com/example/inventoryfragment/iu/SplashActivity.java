package com.example.inventoryfragment.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Handler;

import com.example.inventoryfragment.R;

public class SplashActivity extends AppCompatActivity {
    private  static  final long WAIT_TIME=2000;//tiempo de espera

    TextView tvnombreApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvnombreApp= findViewById(R.id.tvnombreApp);

        Typeface fuente=Typeface.createFromAsset(getAssets(),"Madhen.ttf");
        tvnombreApp.setTypeface(fuente);
        tvnombreApp.setTextSize(50F);
        tvnombreApp.setTextColor(0xffffffff);

    }
    //region Ejemplo1 "Con try catch y usando Thread" y Ejemplo2 "Sin Try Catch y usando Handler
    /*Este objeto  ejecuta el codigo  del metoro  run fuera del hilo de la IU (Interfaz grafica)*/

    @Override
    protected void onStart() {
        super.onStart();
        //EJEMPLO2
        //Manejador
        Handler handler = new Handler();
        //Objeto de la interfaz Runnable  para hilos
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                //region EJEMPLO1
               /* try {*/
                    //Thread.sleep(WAIT_TIME);
                //-------------------------//
                    InitLogin();
                //endregion

                //region Ejemplo1
                /*} catch (InterruptedException e) { //EJEMPLO1
                    e.printStackTrace();
                }*/
                //endregion
            }
        };

        //Ejecutamos el objeto runnable a traves de Thread para ejecutar en un  hilo aparte

        //region EJEMPLO1
        //iniciariamos la siguiente interfaz:
        //new Thread(runnable).start();
        //endregion

        //region EJEMPLO2
        //Metodo del manejador que provoca un retardo
        handler.postDelayed(runnable,WAIT_TIME);
        //endregion

        //region EJEMPLO1
         /*Mal usar el metodo run() de la interfaz de Runnable
        * runnable.run();
        * Ya que ejecutariamos el hilo en el mismo que la interfaz grafica, por lo que la interfaz no se pinta
        **/
         //endregion
    }
    //endregion

/**
 * Este metodo arranca la siguiente activity*/
    private void InitLogin() {
        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        /*
        * Esta activida  va ejecutar OnDestroy al llamar a finish()
        * En este caso lo llamamos ya que esta ventana de splash, no nos hace falta para nada mas
        * despues de iniciar la App no volveremos a ella.
        * */
        finish();
    }
}
