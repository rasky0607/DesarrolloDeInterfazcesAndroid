package com.example.dinamicfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  FragmentA.OnSetTextSizeListener {

    private Fragment fragmenta;
    private Fragment fragmentb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos el fragmentManager (Gestor de los Fragment)
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmenta= fragmentManager.findFragmentByTag(FragmentA.TAG);

        //Cambio de configuracion o paso entre un fragment y otro fragment
        if(fragmenta == null)//Impedimos que cree otro fragment si ya existe uno, para  no mostrar dos fragment iguales uno encima de otro
        {
            //iniciamos la transaccion
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            //Inicializamos el FragmentA
            //fragmenta= new FragmentA();
            fragmenta = FragmentA.newInstance(null);
            //Aañadir fragmentA a la transsaccion
            fragmentTransaction.add(android.R.id.content, fragmenta, FragmentA.TAG);
            //Añadir a la pila de fragmento
            fragmentTransaction.addToBackStack(null);//cuando este en el fragmentB y demos al boton atras pasara al ultimo fragment que llamo al fragmentBes decir al A

          //hacer  commit para  finalizar la transsacion
            fragmentTransaction.commit();
        }


    }


    @Override
    public void onSetmessageTextSize(String message, int size) {

        //iniciamos la transaccion
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Inicializamos el FragmentB
       //fragmentb= new FragmentB();


        //pasamos los datos del fragment A al B
        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE", message);
        bundle.putInt("SIZE",size);

        //fragmentb.setArguments(bundle);
        /**En lugar de  "//Inicializamos el FragmentB" como haciamos arriba, usamos el patron Factoria de google*/
        fragmentb = FragmentB.newInstance(bundle);//PROBLEMA AQUI
        Log.d("AQUI ES-->",message);

        //iniciamos la transaccion
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Aañadir fragmentB a la transsaccion
        fragmentTransaction.replace(android.R.id.content, fragmentb, FragmentB.TAG);
        //Añadir a la pila de fragmento que se guarda a su vez en la activity
        fragmentTransaction.addToBackStack(null);//cuando est en el fragmentB y demos al boton atras pasara al ultimo fragment que llamo al fragmentBes decir al A
        //hacer  commit para  finalizar la transsacion
        fragmentTransaction.commit();
    }
}
