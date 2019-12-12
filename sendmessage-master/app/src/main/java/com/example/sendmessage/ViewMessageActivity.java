package com.example.sendmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sendmessage.model.Message;

/**
 * <h1>ViewMessageActivity</h1>
 * <p>Esta clase recibe un mensaje de SendMessageActivity</p>
 * <h2>Conceptos aprendidos</h2>
 * <ol>
 *     <li>Envio de informacion entre activitys</li>
 *     <li>Concepto de clase modelo/modelo de datos o pojo</li>
 * </ol>
 * @author Pablo López Santana
 * @version 1.0
 * @see androidx.appcompat.app.AppCompatActivity
 * @see android.os.Bundle*/
public class ViewMessageActivity extends AppCompatActivity {

    private TextView tvAuthor;
    private  TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmessage);

        //Recoger el Intent que nos ha enviado  la Activity que ha iniciado
        //Intent cartero = getIntent();//Quien me ha iniciado

        //Recoger el objeto Bundle
        //Es lo mismo Bundle cartita = getIntent().getExtras() que:
        //Bundle cartita = cartero.getExtras();

        //REFACTORIZACION :
        //-----------------------------------------------
        //referenciar componentes con objetos de la clase
        tvAuthor=findViewById(R.id.tvAuthor);
        tvMessage=findViewById(R.id.tvMessage);

        //Recojemos el bundle
        Message mensaje = getIntent().getExtras().getParcelable("mensaje");
        tvAuthor.setText(mensaje.getAuthor());//Misma clave que indicamos al enviar los datos en cada una d elas cadenas
        tvMessage.setText(mensaje.getMessage());
        //--------------------FIN REFACTORIZACION----------------

        //2 Asginar las cadenas a los componentes view
        //tvAuthor=findViewById(R.id.tvAuthor);
        //tvMessage=findViewById(R.id.tvMessage);
        //tvAuthor.setText(cartita.getString("Author"));//Misma clave que indicamos al enviar los datos en cada una d elas cadenas
        //tvMessage.setText(cartita.getString("Mensaje"));



    }
}
