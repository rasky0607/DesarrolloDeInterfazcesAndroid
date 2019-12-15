package com.pablolopez.DinamicFragmentYReciclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Fragment listfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el objeto de la clase que controla controla los fragment
        FragmentManager fragmentManager=getSupportFragmentManager();

        //Iniciamos el objeto de la clase que realiza la transaccion de fragmentos
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        //Iniciamos el fragmento Listfragment que viene a ser el " listfragment.xml "
        listfragment = new Listfragment();
        //[Añadimos] el fragmento " listfragment " a la transaccion.
        fragmentTransaction.add(android.R.id.content,listfragment,Listfragment.TAG);
        //Realizamos el commit, para finalizar la transaccion.
        fragmentTransaction.commit();

    }
}
