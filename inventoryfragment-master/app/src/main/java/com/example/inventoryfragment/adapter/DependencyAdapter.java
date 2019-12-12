package com.example.inventoryfragment.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryfragment.R;
import com.example.inventoryfragment.data.model.Dependency;
import com.example.inventoryfragment.data.repository.DependencyRepository;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;


public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    /**Los datos se van a obtener directamente desde el Repository*/
    private ArrayList<Dependency> list;

    //Escuchador
    private onManageDependencyListener listener;

    public interface onManageDependencyListener{
        void onEditDependencyListener(Dependency dependency);
        void onDeleteDependencyListener(Dependency d);
    }

    //Constructor
    public  DependencyAdapter(){
        list = (ArrayList<Dependency>) DependencyRepository.getInstance().getList();

    }


    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item,parent,false);
        return new ViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {

        holder.icon.setLetter(list.get(position).getName());
        holder.tvName.setText(list.get(position).getName());
        //holder.description.setText(list.get(position).getDescription());
        holder.bind(list.get(position),listener);

    }

    public void setOnManageDependencyListener(onManageDependencyListener listener){
        this.listener = listener;
    }



    //Numero de lementos que tiene o holders que tiene nuestro RecyclerView
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Clase intertna
    class ViewHolder extends RecyclerView.ViewHolder{
        MaterialLetterIcon icon;
        TextView tvName;
        TextView description;

        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignamos/Asociamos componentes del dependency_item al holder del recyclerView fragment_dependency_list
            tvName= itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
            //description= itemView.findViewById(R.id.edDescription);
        }

        //Eventos de pulsacion larga y corta
        public void bind(final Dependency dependency, final onManageDependencyListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Paso 1","DependencyAdapter "+dependency.getName());
                    listener.onEditDependencyListener(dependency);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onDeleteDependencyListener(dependency);
                    return true;
                }
            });
        }

    }
}
