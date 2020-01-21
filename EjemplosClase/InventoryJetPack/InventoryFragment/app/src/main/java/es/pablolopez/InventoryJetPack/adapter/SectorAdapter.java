package es.pablolopez.InventoryJetPack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.data.model.Sector;

public class SectorAdapter extends RecyclerView.Adapter<SectorAdapter.SectorAdapterViewHolder> {

    private ArrayList<Sector> sectors;

    private SectorAdapterListener viewListener;

    public SectorAdapter() {
        this.sectors = new ArrayList<>();
        //this.sectors = SectorRepository.getInstance().getSectors();
    }

    @NonNull
    @Override
    public SectorAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SectorAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sector_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectorAdapterViewHolder holder, int position) {
        holder.ivIcon.setLetter(sectors.get(position).getUriImage());
        holder.tvName.setText(sectors.get(position).getName());
        holder.tvDependency.setText(sectors.get(position).getDependencia().getShortname());

        holder.bind(viewListener,sectors.get(position));
    }

    @Override
    public int getItemCount() {
        return sectors.size();
    }

    public void setViewListener(SectorAdapterListener viewListener){
        this.viewListener = viewListener;
    }

    public void clear() {
        this.sectors.clear();
    }

    public void addAll(ArrayList<Sector> sectors) {
        this.sectors.addAll(sectors);
    }

    public void deleteSector(Sector sector) {
        this.sectors.remove(sector);
    }

    public class SectorAdapterViewHolder extends RecyclerView.ViewHolder {

        MaterialLetterIcon ivIcon;
        TextView tvName, tvDependency;

        public SectorAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.sector_iconItem);
            tvName = itemView.findViewById(R.id.sector_tvName);
            tvDependency = itemView.findViewById(R.id.sector_tvDependency);
        }

        public void bind(final SectorAdapterListener listener, final Sector sector){
            itemView.setOnClickListener(v -> listener.onClickSectorAdapter(sector));

            itemView.setOnLongClickListener(v-> {
                listener.onLongClickSectorAdapter(sector);
                return true;
            });
        }

    }

    public interface SectorAdapterListener{
        void onClickSectorAdapter(Sector sector);
        void onLongClickSectorAdapter(Sector sector);
    }
}
