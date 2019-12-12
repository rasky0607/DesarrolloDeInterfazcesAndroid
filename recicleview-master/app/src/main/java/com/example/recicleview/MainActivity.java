package com.example.recicleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recicleview.Model.User;
import com.example.recicleview.adapter.RecyclerUserAdapter;

//[Evento reciclerView] Obcion 1: Implentamos el interfaz para comunicar el RecyclerUserAdapter.OnItemClickListener Que realmente esta hederando de onClick
public class MainActivity extends AppCompatActivity implements RecyclerUserAdapter.OnItemClickListener {

    private RecyclerView rvUser;
    private RecyclerUserAdapter userAdapter;

    //[Evento reciclerView]Opcion 2: Definir un Listener
    private  RecyclerUserAdapter.OnUserClickListener userClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buscamos el componente
        rvUser=findViewById(R.id.rvUser);

        //Inicializamos el manager en el componente
        LinearLayoutManager llManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvUser.setLayoutManager(llManager);

        /**Creamos el objeto RecyclerUserAdapter que hace referencia a nuestra clase personalizada RecyclerUserAdapter
         *  que hereda de (RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder>)
         *  y le pasamos el contexto que es lo unico que necesita el constructor que le creamos.
         *  IMPORTANTE ->(En esta clase se llena la lista de elementos que va mostrar el recicleview)
         *  y cada uno de los metos que hay que implementar de la calse de la que heredamos, para dar comportamientoa los Holder
         *  es decir a cada item de la lista (o rectangulito) que podremos seleccionar
         *
         *  */
        userAdapter = new RecyclerUserAdapter(this,this);
        //Añadimos el ReciclerView el adapter donde encuentra los datos
        rvUser.setAdapter(userAdapter);

        //Evento reciclerView]Opcion 2:] metodo que inicializa todos los listener de la activity
        inicialiceListener();
    }

    /**Evento reciclerView]Opcion 2:] Metodo donde se inicializan todos los Listener/delegados de la Activity*/
    private void inicialiceListener() {
        userClickListener = new RecyclerUserAdapter.OnUserClickListener() {
            @Override
            public void onClick(User user) {
                Toast.makeText(MainActivity.this,"EL 2 Se ha pulsado una opcion",Toast.LENGTH_SHORT).show();
            }
        };

        //Evento reciclerView]Opcion 2:]La activity se apunta/subcribe al evento onuserClickListener
        userAdapter.setOnUserClickListener(userClickListener);

    }

    //[Evento reciclerView] Obcion 1: Evento que implementamos por la interfaz RecyclerUserAdapter.OnItemClickListener
    /*Este es el metodo que se ejecuara automaticamente cuando se hace elcick en un elemento del RecyclerView*/
    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Se ha pulsado una opcion",Toast.LENGTH_SHORT).show();
        Log.e("PULSACION","Me has pulsado");
        //TODO Inflar vista del  usuario clicado






    }



}
