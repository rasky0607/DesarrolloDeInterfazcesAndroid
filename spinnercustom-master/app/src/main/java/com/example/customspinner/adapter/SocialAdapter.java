package com.example.customspinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customspinner.R;
import com.example.customspinner.model.Social;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**Esta clase tiene  internamente un List, que se inicializa en la llamada del super(context, R.layout.item_social,list);*/
public class SocialAdapter extends ArrayAdapter<Social> {

    //Constructor que nos obliga a tener por heredar de ArrayAdapter<Social>
    public SocialAdapter(@NonNull Context context, List<Social> list) {
        super(context, R.layout.item_social,list);
    }

    /*Como va mostrar los datos*/
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        ViewHolder vHolder;
        //Si es distinto de null es que se esta reciclando el item de la lista, si si lo es (no es un tiem reciclado es uno nuevo creado
        if(v==null){
            vHolder = new ViewHolder();
            //Inflamos la vista (plantilla) con la que mostraremos cada item de la lista del spinner
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_social,parent,false);
            //Asociamos los elementos que componen el item de la lista con los de la clase
            vHolder.icon=v.findViewById(R.id.icon);
            vHolder.tvName=v.findViewById(R.id.tvName);
            //Lo marcamos el nuevo creado como vHolder
            v.setTag(vHolder);

        }
        else//Si distinto d enulo, se recicla pasandole el tag a la nueva vista
            vHolder=(ViewHolder) v.getTag();

        //Añadimos los datos  a los elementos que componen el item de la lista
        vHolder.icon.setImageResource(getItem(position).getIcon());
        vHolder.tvName.setText(getItem(position).getName());

        return v;
    }

    //Esta no extends a diferencia de ReciclerView por que esta es mas antigua y no existia aun, viewHolder(la llamaos igual por consenso de la clase)
    private  static class ViewHolder{

        private ImageView icon;
        private TextView tvName;


    }
}
