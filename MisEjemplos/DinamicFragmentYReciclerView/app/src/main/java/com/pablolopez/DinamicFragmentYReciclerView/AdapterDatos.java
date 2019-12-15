package com.pablolopez.DinamicFragmentYReciclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*Esta clase hereda de la clase interna que creamos  " ViewHolderDatos " */
public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> listDatos;

    //Constructor
    public  AdapterDatos(ArrayList<String> listDatos)
    {
        this.listDatos=listDatos;
    }

    /*Enlaza el adapter con el archivo itemlistfragment.xml, es decir inflamos la vista del xml */
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistfragment,null,false);
        return new ViewHolderDatos(v);
    }

    /*Establece la comunicacion entre el Adapter y la clase ViewGolderDatos*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    /*Devuelve el numero de elementos de la lista*/
    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    /*Clase interna que hereda de " RecyclerView.ViewHolder"
     en la que se asignan los distintos componentes que forman cada uno de los los elementos de nuestro ReciclerView,
     * es decir cada uno de los componentes que encontramos en el itemlistfragment.xml donde se define la composicion de cada elemento de la lista del ReciclerView*/
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvTituloNumero;
        //TODO No usamos el resto de componentes aun para no complicar el ejemplo (por ahora)
        TextView tvNumero;
        TextView tvtituloNombre;
        TextView tvNombre;

        //Constructor
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //Enlace de componentes de layout itemlistfragment.xml
            tvTituloNumero = itemView.findViewById(R.id.tvTituloNumero);
            /*tvNumero = itemView.findViewById(R.id.tvNumero);
            tvtituloNombre = itemView.findViewById(R.id.tvtituloNombre);
            tvNombre = itemView.findViewById(R.id.tvNombre);*/

        }

    /*Asigna el dato que va mostrar al componente,
     * tambien podemos realizarlo en el propio metodo onBindViewHolder directamente*/
        public  void  asignarDatos(String tituloNumero){
            tvTituloNumero.setText(tituloNumero);
            /*tvNumero.setText(numero);
            tvtituloNombre.setText(tituloNombre);
            tvNombre.setText(nombre);*/
        }


    }


}
