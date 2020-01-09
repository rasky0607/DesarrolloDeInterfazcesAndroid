package es.adrianmmudarra.socialspinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.List;

import es.adrianmmudarra.socialspinner.R;
import es.adrianmmudarra.socialspinner.model.Social;

public class SocialAdapter extends ArrayAdapter<Social> {


    public SocialAdapter(@NonNull Context context, int resource, @NonNull List<Social> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        if (v == null){
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_item,parent,false);
            viewHolder.img = v.findViewById(R.id.icon);
            viewHolder.tv = v.findViewById(R.id.tvName);
            v.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) v.getTag();
        viewHolder.img.setImageResource(getItem(position).getIcon());
        viewHolder.tv.setText(getItem(position).getName());
        return v;
    }

    private static class ViewHolder{
        private ImageView img;
        private TextView tv;
    }
}
