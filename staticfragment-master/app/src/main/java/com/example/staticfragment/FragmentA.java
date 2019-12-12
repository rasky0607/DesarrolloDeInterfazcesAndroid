package com.example.staticfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentA extends Fragment {
    private String TAG ="MI FRAGMENT A";

    private EditText tvfra;
    private SeekBar sbfra;
    private Button btnfra;

    private  OnSetTextSizeListener listener;

    /***
     * Esta interfaz servira de contrato entre el fragment y su clase contenedora (Activity)
     */
    public  interface  OnSetTextSizeListener{
        void onSetmessageTextSize(String text, int size);
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
        tvfra=view.findViewById(R.id.tvfra);
        btnfra=view.findViewById(R.id.btnfra);
        sbfra=view.findViewById(R.id.sbfra);//SeekBar del fragmento A

        btnfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSetmessageTextSize(tvfra.getText().toString(),sbfra.getProgress());
            }
        });
    }
}
