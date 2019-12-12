package com.example.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Imports nuevos
import com.example.sendmessage.model.Message;
import com.example.sendmessage.model.SendMessageAplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * <h1>Ejmplo SendMessage</h1>
 * <p>En este ejercicio tenemos dos Activity. La primera de ellas manda un mensaje y la segunda lo muestra</p>
 * <p>Esta clase envia un objeto Bundle a traves de un Intent a otra Activity</p>
 * <h2>Conceptos aprendidos:Enviar informacion entre activitys</h2>
 * <ol>
 *     <li>Crea Comentarios Javadoc y generar la documentación</li>
 *     <li>Sobreescribir metodos de el cilo de vida de una activity</li>
 *     <li>Mensajes Toast</li>
 *     <li>Mensajes para el Logcat con la clase Log</li>
 *     <li>Reglas de negocio(Evitar que se envie un mensaje vacio)</li>
 *     <li>Implementacion de  una libreria de 3ºs logger</li>
 * </ol>
 * @author Pablo López Santana
 * @version 1.0
 * @see androidx.appcompat.app.AppCompatActivity
 * @see android.os.Bundle
 * @literal //github.com/orhanobut/logger*/

public class SendMessageActivity extends AppCompatActivity {

    private  String TAG ="SendMessage";
    //Declaracion de objetos
    private EditText edMessage;
    private Button btnEnviar;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG,"DESTRUIDA");
        Logger.d("DESTRUIDA");
    }


    @Override
    protected void onStop() {
        super.onStop();
        
        //Log.d(TAG,"SedMessage-> STOP");
        Logger.d("SedMessage-> STOP");

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG,"SedMessage PAUSE ");
        Logger.d("SedMessage PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Log.d(TAG,"SedMessage REANUDAR ");
        Logger.d("SedMessage REANUDAR");

    }


    @Override
    protected void onStart() {
        super.onStart();
        //Log.d(TAG,"ARRANCA SedMessage");
        Logger.d("ARRANCA SedMessage");
        //Mensaje indicando el usuario que ha iniciado la aplicacion.
        mostrarMensajeInfo(((SendMessageAplication)getApplication()).getUser().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);


        //Buscar el objeto dentro dela vista y asignarlo a nuestro objeto
        edMessage=findViewById(R.id.edMessage);
        btnEnviar=findViewById((R.id.btnEnviar));

        //region REFACTORIZACION
        //Registrar un listener/escuchador -> se llamara setOn.. lo que sea
        //Creo una clase anomina que implementa la interfaz view.OnclickListener,
        //que contiene el metodo/evento onClick()
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mensajeVacio())//Reglas de negocio:Comprobar que el mensaje no este vacio
                {
                   EnviarMensaje();
                }
                else
                {
                    mostrarError("No has escrito un mensaje.");
                }


                //----Implementacion de libreria de 3ºs logger implementation 'com.orhanobut:logger:2.2.0'-------------
                //Inicio  el adaptaer para crear mensajes  Log con formato
                Logger.addLogAdapter(new AndroidLogAdapter());

                //--------------------------//

            }
        });

        //endregion

        //region Ejemplo de  pulsacion larga
        /*
        btnEnviar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false; // Si consumimos este evento, debera devolver true, si no, false y pasara al siguiente componente de la jerarquia que tenga declarado este mismo evento
            }
        });*/
        //endregion

    }

    /**
     * Metodo que muestra un error al usuario
     * @param  msg mensage que se muestra al usuario*/
    private void mostrarError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }
    /**
     * Metodo que muestra un mensje informativo al usuario
     * @param  msg mensage que se muestra al usuario*/
    private void mostrarMensajeInfo(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    //Metodo que comprueba que el usuario a introducido un mensaje
    private boolean mensajeVacio() {
        return  edMessage.getText().toString().isEmpty();//
    }




    //region Ejemplo1: Metodo  Enviar un mensaje que se jecuta cuando se lanza el evento onClick de el xml android:onClick="EnviarMensaje"
    //public void EnviarMensaje(View view) {
        //region Ejmeplo1
        /**
         // Ejemplo de mala practica
        //DownCasting
        //"Basicamente es un casting de view a un objeto button para  obtener todas las propiedades de Button
        //Obligatoria hacer esta comprobacion, para asegurarnos que se trata de una instancia del button

       if (view instanceof  Button) {

            Button button = ((Button) view);
            button.setText("Hola");
        }*/

        //1-Crear objeto Bundle que contine los datos que se comparten entre actividades
        //Bundle cartita = new Bundle();
        //endregion

        //EnviarMensaje();

        //region Ejempo1
        //Llenamos la cartita de datos con el metodo put que corresponda con el tipo de dato que queremos guardar
       // cartita.putString("Author","Pablo López Santana");
        //cartita.putString("Mensaje",edMessage.getText().toString());//toString() ya que getText devuelve un objeto editable

        //2-Crear el objeto que envie el mensaje Intent explicito (donde se conoce la actividad de origen y la de destino)
        //Intent cartero = new Intent(SendMessageActivity.this,ViewMessageActivity.class);//necesita el contexto de donde provienen los datos que vamos  a enviar

        //3-Recoger los datos;
        //cartero.putExtras(cartita);//para recoger nuestro Bundle creado y no el que trae internamente el Intent

        //Arrancamos la siguiente activity
        //startActivity(cartero);
        //endregion
    //}
        //endregion
        //Metodo que Envia un mensaje
    public  void  EnviarMensaje() {

        //region REFACTORIZACION :
        //------------------------------------------------//
        //1
        Bundle cartita = new Bundle();
        Message mensaje = new Message("Pablo López Santana",edMessage.getText().toString());
        //Enviamos el objeto mensaje
        cartita.putParcelable("mensaje",mensaje);

        //2-Crear el objeto que envie el mensaje Intent explicito (donde se conoce la actividad de origen y la de destino)
        Intent cartero = new Intent(SendMessageActivity.this,ViewMessageActivity.class);//necesita el contexto de donde provienen los datos que vamos  a enviar

        //3-Recoger los datos;
        cartero.putExtras(cartita);//para recoger nuestro Bundle creado y no el que trae internamente el Intent

        //Arrancamos la siguiente activity
        startActivity(cartero);

        //endregion -------------FIN REFACTORIZACION--------------------//

    }



}


