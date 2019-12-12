package com.example.dinamicfragment;

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

    
    public static final String TAG="Dinamic fragment B";
    private static  final String SIZE = "SIZE";
    private  static  final String MESSAGE = "MESSAGE";
    private TextView tvMessage;
    
    //añadido al ejemplo de retener estado Fragment
    private  int size;
    private  String message;



    /**
     * Este metodo-Patron surge de google llamado tambien [Patrón factoria]
     * Metodo que va a crear un objeto de la propia clase FagmentB
     * garantizando que se llama al metodo setArguments inmediatamente despues de la creacion
     *
     * @param bundle
     * @return Fragment
     * */
    public  static  Fragment newInstance(Bundle bundle)
    {
      FragmentB  fragmentb= new FragmentB();
      if(bundle != null) {
          fragmentb.setArguments(bundle);
          Log.d("AQUI ES-->","ENTRO!");
      }

      return fragmentb;

    }

    /**
     * En este fragment se retiene su estado en una re-creación o cambio de configuración de la Activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(TAG,"FragmentB --> onCreate()");
    }

//Añadido al ejemplo de retener estado Fragment: En onCreateView, este fragment  se retiene su estado en una re-creacion o cambio de configuracion de la Activity

    //Cuando estamos creando la vista
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //infla la vista le fragment b
        View v = inflater.inflate(R.layout.fragmenteb,container,false);
        Log.d(TAG,"FragmentB --> onCreateView()");
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMessage = view.findViewById(R.id.tvMessage);

        //Se comprueba si el fragment tiene argumentos. Se podría usar lo siguiente:
        //Bundle bundle = getArguments() y luego en el if se mira la condicion bundle != null
        if(savedInstanceState == null) //añadido al ejemplo de retener estado Fragment
        {
            //Hay que comprobar si el fragment tiene o no argumentos
            Bundle bundle = getArguments();
            if(bundle != null) {
                /*tvfrb.setText(bundle.getString(TEXT));
                tvfrb.setTextSize(bundle.getInt(SIZE));*/

                //añadido al ejemplo de retener estado Fragment
                message = bundle.getString(MESSAGE);
                size = bundle.getInt(SIZE);
            }

            Log.d("AQUI ES -->","FragmentB --> onViewCreated()"+message);
            tvMessage.setText(message);
            tvMessage.setTextSize(size);
            Log.d(TAG,"FragmentB --> onViewCreated()");

        }

    }






    //Comentados para aplicar el ejemplo de Retener el estado "//añadido al ejemplo de retener estado Fragment"
/******************Metodos que se llaman para guardar el estado del fragment*********************
 * *************************************************************************************/
    //Metodo que guarda el estado dinamico del fragment dentro de un objeto Bundle
   /* @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(SIZE,tvfrb.getTextSize());
        outState.putString(TEXT,tvfrb.getText().toString());
        Log.d(TAG,"fragmenteA--> onSaveInstanceState()");

    }*/

    /*Metodo que restaura el estado del Fragment si ha sido reiniciado
    * despues de un cambio de configuracion(osea un volteado).
    * 1-Se llama siempre con lo cual hay que comprobar si es en la creacion o se ha restauraado (saveInstanceState != null)
    * 2- Hay que guardar el texto ya que el tvfrb se ha incializado a taves del metodo setText, no por que el usuario haya introducido datos*/
   /* @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null) {
            tvfrb.setText(TEXT);
            tvfrb.setTextSize(savedInstanceState.getFloat(SIZE));
        }
    }*/
/***********************************************************************************************/





}
