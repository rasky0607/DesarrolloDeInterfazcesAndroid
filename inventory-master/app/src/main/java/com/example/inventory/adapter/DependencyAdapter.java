package com.example.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;


public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    /**Los datos se van a obtener directamente desde el Repository*/
    private ArrayList<Dependency> list;
    //Constructor
    public  DependencyAdapter(){
        list = (ArrayList<Dependency>) DependencyRepository.getInstance().getList();
    }


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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Clase intertna
    class ViewHolder extends RecyclerView.ViewHolder{
        MaterialLetterIcon icon;
        TextView tvName;

        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
