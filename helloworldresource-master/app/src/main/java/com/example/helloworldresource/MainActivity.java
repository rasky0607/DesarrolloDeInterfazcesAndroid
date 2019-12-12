package com.example.helloworldresource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
/*
En este proyecto realizamos:
1º como crear una activiti ladeada con land.
Creando la carpeta layout-land dentro de "/app/src/main/res/" y dentro de esta una activity

2º texto para activitys lands, creamos una carpeta values-en-land
"en es solo el idioma", puede ser values-land y dentro de este el xml de los recursos
3º Traducciones
creamos una carpeta

4ºCreamos la acarpeta Assets para recurso externos en "/app/src/main”
y añadimos dentro el recurso de la fuente descargada
  */
public class MainActivity extends AppCompatActivity {
    private TextView txvPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvPhrase=findViewById(R.id.txvPhrase);
        //Se accede a un recurso de android
        txvPhrase.setTextColor(ContextCompat.getColor(this,android.R.color.darker_gray));

        //Accedemos a un recurso propio nuestro que tenemos en Asset

        Typeface font= Typeface.createFromAsset(getAssets(),"creative_a_s.ttf");//createFromAsset() crea un recurso,getAssets()-> obtiene el recurso
        //Añadimos la fuente al componente grafico
        txvPhrase.setTypeface(font);

    }
}
