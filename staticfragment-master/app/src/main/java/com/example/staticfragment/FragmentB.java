package com.example.staticfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    private TextView tvfrb;
    private  static final String TAG="StaticFragmentB";
    private static  final String SIZE = "Size";
    private  static  final String TEXT = "Text";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //infla la vista le fragment b
        View v = inflater.inflate(R.layout.fragmenteb,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvfrb=view.findViewById(R.id.tvfrb);
    }


    /**Metodo que actualiza el texto y tamaño en el Textview de la interfaz*/
    public void setMessageSize(String text, int size) {
        tvfrb.setText(text);
        tvfrb.setTextSize(size);

    }
/******************Metodos que se llaman para guardar el estado del fragment*********************
 * *************************************************************************************/
    //Metodo que guarda el estado dinamico del fragment dentro de un objeto Bundle
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(SIZE,tvfrb.getTextSize());
        outState.putString(TEXT,tvfrb.getText().toString());
        Log.d(TAG,"fragmenteA--> onSaveInstanceState()");

    }

    /*Metodo que restaura el estado del Fragment si ha sido reiniciado
    * despues de un cambio de configuracion(osea un volteado).
    * 1-Se llama siempre con lo cual hay que comprobar si es en la creacion o se ha restauraado (saveInstanceState != null)
    * 2- Hay que guardar el texto ya que el tvfrb se ha incializado a taves del metodo setText, no por que el usuario haya introducido datos*/
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null) {
            tvfrb.setText(TEXT);
            tvfrb.setTextSize(savedInstanceState.getFloat(SIZE));
        }
    }
/***********************************************************************************************/





}
