package com.pablolopez.DinamicFragmentYReciclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Listfragment extends Fragment {

    public static String TAG ="Dinamic Fragment Listfragment";

    //Componente de este fragment
    RecyclerView miRecicler;

    //Primer metodo que se ejecuta, donde inflamos la vista del fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listfragment,container,false);
        return v;
    }

    //Segundo metodo que se ejecuta, donde realizaremos la asociacion de id con los componentes de la vista del fragment listfragment.xml
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Asignamos el componente
        miRecicler= view.findViewById(R.id.miRecicler);
        //Indicamos con que formato se va mostrar el ReciclerView
        miRecicler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //Cargamos la lista de informacion para mostrarla ( esto en teoria seria de la clase Model o Pojo
        /*----------------------------------------------*/
        ArrayList<String> listDatos = new ArrayList<String>();
        for(int i=0;i<=30;i++)
        {
            listDatos.add("Dato "+i);
        }
        /*----------------------------------------------*/
        /*Pasamos la lista de datos al Adapter para que enlace y organice con el fichero y los componentes correspondientes de itemlistfragment.xml*/
        AdapterDatos adapter = new AdapterDatos(listDatos);
        /*Cargamos el Adapter en nuestro ReciclerView*/
        miRecicler.setAdapter(adapter);


    }


    //region Parte aun que no entiendo del todo
/*TODO Aun no entiendo exactamente para que son estos dos metodos*/
    /*--------------------------------------------------------------------------------*/
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    //Metodo contrario al onAttach
    @Override
    public void onDetach() {
        super.onDetach();
    }

    //TODO No entiendo por que usamos este metodo en de el ejemplo DinamicFragment de el que  nos ayudamos para hacer este.

    /*    public  static  Fragment newInstance(Bundle bundle){

        FragmentA fragmentA = new FragmentA();

        if(bundle != null)
            fragmentA.setArguments((bundle));

            return  fragmentA;

    }*/
/*------------------------------------------------------------------*/
    //endregion

    //Ciclo de vida del fragment ( igual que la de una activity)
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"Listfragment -> onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Listfragment -> onResume()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Listfragment -> onCreate()");
    }
}
