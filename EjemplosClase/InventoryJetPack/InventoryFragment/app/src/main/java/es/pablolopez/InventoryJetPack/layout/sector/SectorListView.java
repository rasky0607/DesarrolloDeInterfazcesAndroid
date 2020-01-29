package es.pablolopez.InventoryJetPack.layout.sector;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.adapter.SectorAdapter;
import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.model.Sector;
import es.pablolopez.InventoryJetPack.layout.base.BaseDialogFragment;

public class SectorListView extends Fragment implements SectorListContract.View, BaseDialogFragment.OnBaseDialogListener{

    public static final String TAG = "SectorListView";

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private View progressView;

    private OnSectorListViewListener activityListener;
    private SectorAdapter sectorAdapter;
    private BaseDialogFragment baseDialogFragment;

    private SectorListContract.Presenter presenter;

    private Sector deleted;

    public SectorListView() {
    }

    public static SectorListView newInstance(Bundle b) {
        SectorListView fragment = new SectorListView();
        if (b != null)
            fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sector_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Listado sectores");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerSectorList);
        fabAdd = view.findViewById(R.id.fabSectorList);
        progressView = view.findViewById(R.id.fragmentLoadingSectorList);

        fabAdd.setOnClickListener(v -> activityListener.onAddEditSector(null));

        sectorAdapter = new SectorAdapter();
        sectorAdapter.setViewListener(new SectorAdapter.SectorAdapterListener() {
            @Override
            public void onClickSectorAdapter(Sector sector) {
                activityListener.onAddEditSector(sector);
            }

            @Override
            public void onLongClickSectorAdapter(Sector sector) {
                showDialogDelete(sector);
            }
        });

        recyclerView.setAdapter(sectorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSectorListViewListener) {
            activityListener = (OnSectorListViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSectorListViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    private void showDialogDelete(Sector s) {
        Bundle b = new Bundle();
        b.putString(BaseDialogFragment.TITLE,"Borrar Sector");
        b.putString(BaseDialogFragment.MESSAGE,"¿Estas seguro de que quieres borrar esta sector? -> "+s.getShortname());
        baseDialogFragment = BaseDialogFragment.getInstance(b);
        baseDialogFragment.setTargetFragment(SectorListView.this,300);
        baseDialogFragment.show(getFragmentManager(),BaseDialogFragment.TAG);
        deleted = s;
    }

    @Override
    public void showProgressBar() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void refeshData(ArrayList<Sector> sectors) {
        sectorAdapter.clear();
        sectorAdapter.addAll(sectors);
        sectorAdapter.notifyDataSetChanged();
        deleted = null;
    }

    @Override
    public void onSuccessDelete(Sector sector) {
        sectorAdapter.deleteSector(sector);
        sectorAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoSectors(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteMessage(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).setAction(android.R.string.cancel, v -> presenter.undoDelete(deleted)).show();
    }

    @Override
    public void setPresenter(SectorListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {

    }


    @Override
    public void onAccept() {
        presenter.deleteSector(deleted);
    }

    @Override
    public void onCancel() {
        baseDialogFragment.dismiss();
    }


    public interface OnSectorListViewListener {
        void onAddEditSector(Sector sector);
    }
}
