package com.example.dinamicfragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentA extends Fragment {
    public static String TAG ="Dinamic Fragment A";

    private EditText edMessage;
    private SeekBar sbTamanio;
    private Button btnCambio;

    private  OnSetTextSizeListener listener;

    /***
     * Esta interfaz servira de contrato entre el fragment y su clase contenedora (Activity)
     */
    public  interface  OnSetTextSizeListener{
        void onSetmessageTextSize(String message, int size);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentea,container,false);
        //Solo despues de inflar la vista se puede hacer findbyid aun que siempre mejor hacerlo en el onViewCreated, pero siempre tras inflar la vista primero
        Log.d(TAG,"fragmenteA--> onCreateView()");

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        edMessage =view.findViewById(R.id.edMessage);
        btnCambio=view.findViewById(R.id.btnCambio);
        sbTamanio=view.findViewById(R.id.sbTamanio);//SeekBar del fragmento A

        btnCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSetmessageTextSize(edMessage.getText().toString(),sbTamanio.getProgress());
            }
        });
    }


    //Este metodo siempre se ejecuta,
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(OnSetTextSizeListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() + "must implement OnSetText");

        }
    }

    /*Metodo contrario al onAttach donde debemos poner
     a null o desitancia todo lo que forma parte de su estado como el listener*/
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        Log.d(TAG,"fragmenteA--> onDetach()");
    }


    public  static  Fragment newInstance(Bundle bundle){

        FragmentA fragmentA = new FragmentA();

        if(bundle != null)
            fragmentA.setArguments((bundle));

            return  fragmentA;

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"fragmentA -> onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"fragmentA -> onResume()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"fragmentA -> onCreate()");
    }



}
