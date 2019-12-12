package com.PabloLopez.mireciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> listDatos;

    //Constructor
    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    /*Enlaza el adapter con el archivo Item_list.xml, es decir inflamos la vista del xml*/
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        return new ViewHolderDatos(view);
    }

    /*Establece la comunicacion entre el Adapter y la clase ViewGolderDatos*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    /*Optine el numero de elementos de nuestra lista*/
    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    /*Clase interna en la que se asignan los distintos componentes que forman cada uno de los los elementos de nuestro ReciclerView,
    * es decir cada unod e los componentes que encontramos en el item_list.xml donde se define la composicion de cada elemento de la lista del ReciclerView*/
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        //Constructor
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //Enlazando componentes
            dato= itemView.findViewById(R.id.idDato);
        }

        /*Asigna el dato que va mostrar al componente,
        * tambien podemos realizarlo en el propio metodo onBindViewHolder directamente*/
        public void asignarDatos(String s) {
            dato.setText(s);
        }
    }
}
