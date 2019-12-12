package com.example.recicleview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recicleview.Model.User;
import com.example.recicleview.R;
import com.example.recicleview.Repository.RepositoryUser;
import java.util.ArrayList;

/**
 * Nuestra clase hereda de RecyclerView.Adapter en concreto de <vh> es decir ViewHolder */
public class RecyclerUserAdapter extends RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder> {

    private  Context context;
    private ArrayList<User> list;

    //[Evento reciclerView] Obcion 1: Listener
    private  OnItemClickListener listener;

    //[Evento reciclerView]Opcion 2: Definir un Listener propio
    private  OnUserClickListener onUserListener;

    /*
    * La clase que quiera escuchar el evento Onclick del recyclerView debe implementar
    * la siguiente interfaz.
    * Opcion 1: Heredar de Vire.onClickListener
    * */

    //[Evento reciclerView] Obcion 1: Declaracion de la interfaz
    public  interface  OnItemClickListener extends  View.OnClickListener{
        @Override
        void onClick(View view);
    }

    //[Evento reciclerView]Opcion 2: Definir un Listener propio
    public  interface  OnUserClickListener{
        void onClick(User user);
    }

    //[Evento reciclerView]Opcion 2: Se establece el listener OnuserClickListener mediante el siguiente metodo
    public  void setOnUserClickListener(OnUserClickListener userListener){
        this.onUserListener = userListener;

    }


    /*Evento reciclerView] Obcion 1: le añadimos el parametro OnItemClickListener listener para indicarle el listener que va escuchar el evento.
    (Lo ponemos en el constructor por que solo tenemos un listener,
    si no necesitaremos un SetOnclickListener, para poder tener varios listener diferentes*/
    //Constructor
    public RecyclerUserAdapter(Context context, OnItemClickListener listener) {
        this.context=context;
        //Inicializamos el listener (ERROR TONTO que me rayo mucho)
        this.listener=listener;
        //hacemos el casting EXPLICITO por que en la clase RepositoyUser  devolvemos un List que es la clase padre de ArrayList
        list = (ArrayList<User>) RepositoryUser.getInstance().getList();
    }

    /**
     * Infla tantos elementos y crea tantos objetos ViewHolder como se necesite*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**Gracias a esto no necesitamos hacer referencia a ninguna  activity ni a nada,ya que a través del contexto inflamos la vista necesaria
         * con los parametros del el View parent de la vista que pasamos "user_item", que es la vista del ReciclerView,
         * y el false indica que no pintara nada hasta quen o cargue todos los elementos, de forma que no duplicare la vista de user_item una y otra vez,
         * esperara a cargar todos y pintar los ViewHolder que crea que es necesario para que entren en la pantalla*/
        View v= LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);

        /**[Evento reciclerView] Obcion 1: IMPORTANTE :Se ha aprovechado  que e Listener OnclickListener existe en todos los  objetos view*/
        //[Evento reciclerView] Obcion 1: asignamos el listener al componente view
        v.setOnClickListener(listener);

        //ViewHolder miVholder = new ViewHolder(v);
        return  new ViewHolder(v);
    }

    /**
     * Enlaza los datos a cada componente del ViewHolder cuando se produce el desplazamiento en
     * el ReciclerView
    * @param holder
     * @param position
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //asociamos vista con su dato
        holder.tvUser.setText(list.get(position).getName());
        holder.tvEmail.setText(list.get(position).getEmail());

        //[Evento reciclerView]Opcion 2: vincular al componente listener
        holder.bind(position,onUserListener);
        if(onUserListener != null)
            holder.bind(position,onUserListener);

    }

    /**
     * Devuelve el numero de elementos/datos del dataSource*/
    @Override
    public int getItemCount() {

        return list.size();
    }

    /**Esta clase almacena  y recicla las vistas, es decir los objetos View/item que componen el RecyclerView
     * es decir el rectangulito que contiene toda la informacion de un regsitro.*/
    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvUser;
        TextView tvEmail;

        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /**Dentro de la vista que me pasas buscar el elemento
             * Es decir estamos creando los rectangulitos de cada uno
             * de los elementos que vamos a listar en el ReciclerView*/

            tvUser=itemView.findViewById(R.id.tvUser);
            tvEmail=itemView.findViewById(R.id.tvEmail);
        }

        /*[Evento reciclerView]Opcion 2: Este metodo establece el listener a un evento de uno de los componentes
         del View como por ejemplo a uno de los TextView que forman el View del Holder*/
        public void bind(final int position,final OnUserClickListener onUserListener) {
            // elemento view que contiene el view holder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserListener.onClick(list.get(position));
                }
            });
        }
    }


}
