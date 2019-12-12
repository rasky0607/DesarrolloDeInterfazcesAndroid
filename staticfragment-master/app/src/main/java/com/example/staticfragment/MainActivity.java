package com.example.staticfragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentA.OnSetTextSizeListener {

    private String TAG=" Mi MAINACTIVITY staticfragment";
    //Ya esta inicializado
    private Fragment fragmentb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Activity ->onCreate()");

        fragmentb=getSupportFragmentManager().findFragmentById(R.id.fragmentB);
    }

    //**Metodo que modifica el texto y tamaño del texto del fragmentB de su TextView*/
    @Override
    public void onSetmessageTextSize(String text, int size) {
        Toast.makeText(this,"Muestra mensaje"+text+" tamaño de SeekBar "+size,Toast.LENGTH_SHORT).show();
        /*la activity puede realizar cualquier operacion o comprobacion de los datos antes de pasrselo al Fragment,es decir es una clase controladora*/
        ((FragmentB)fragmentb).setMessageSize(text,size);
    }
}
