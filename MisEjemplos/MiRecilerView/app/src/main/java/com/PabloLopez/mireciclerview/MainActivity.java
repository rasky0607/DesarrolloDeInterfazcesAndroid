package com.PabloLopez.mireciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>listDatos;
    RecyclerView reciclerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignamos el componente
        reciclerId=findViewById(R.id.reciclerId);

        //Indicamos conq ue formato se va mostrar el ReciclerView
        reciclerId.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        /*Esto deberia hacerse en  una clase Repository de donde se recogerian los datos de la lista*/
        listDatos=new ArrayList<String>();

        for(int i=0;i<=60;i++)
        {
            listDatos.add("Dato "+i);
        }
        /*------------------------------------------------------------------*/

        /*Pasamos la lista de datos al Adapter para que enlace y organice con el fichero y los componentes correspondientes de item_list.xml*/
        AdapterDatos adapter = new AdapterDatos(listDatos);
        /*Cargamos ene l Recicler de la vista el adaptardor correspondiente para que cargue los datos*/
        reciclerId.setAdapter(adapter);
    }
}
