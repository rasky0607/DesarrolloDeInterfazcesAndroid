package com.example.customspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customspinner.adapter.SocialAdapter;
import com.example.customspinner.model.Social;
import com.example.customspinner.model.SocialComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spSocial;
    private List<Social> list;
    private  SocialAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1.Crear spinner /vinculamos spiner
        spSocial= findViewById(R.id.spSocial);

        //2. Vincular adapter a la  vista
        adapter = new SocialAdapter(this,getListSocial());

        //3.Personalizar spinner
        //Sin este setDropDownViewResource colocara todos los elementos juntos
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSocial.setAdapter(adapter);

        //Antes de arrancar el spinner no selecionara el primer elemento por defecto
        spSocial.setSelection(0,false);
        //[Ejemplo1 Evento spinner]
        spSocial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ((Social)adapterView.getItemAtPosition(i)).getName(), Toast.LENGTH_SHORT).show();
                // Si cambiamos el nombre de los parametros adapterView por parent y el parameto i , por position
                // Toast.makeText(parent.getContext(), ((Social)(parent.getItemAtPosition(position))).getName(), Toast.LENGTH_SHORT).show();
            }

            //Cuando ningun item fue selecionado
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    private List<Social> getListSocial() {
        list = new ArrayList<>();
        list.add(new Social(R.drawable.facebook,getString(R.string.a),700,2000000));
        list.add(new Social(R.drawable.facebook,getString(R.string.facebook),300,2000000));
        list.add(new Social(R.drawable.google,getString(R.string.google),400,3205000));
        list.add(new Social(R.drawable.facebook,getString(R.string.b),200,6200000));
        list.add(new Social(R.drawable.twitter,getString(R.string.twitter),400,3200000));
        //[Ejemplo1 interfaz Comparable] Ordenamos la lista por orden natural
        Collections.sort(list);
        //[Ejemplo2 interfaz Comparator]Ordenar  por varios criterios usando Comparator y pasandole este como parametro
        Collections.sort(list,new SocialComparator());
        return  list;
    }
}
