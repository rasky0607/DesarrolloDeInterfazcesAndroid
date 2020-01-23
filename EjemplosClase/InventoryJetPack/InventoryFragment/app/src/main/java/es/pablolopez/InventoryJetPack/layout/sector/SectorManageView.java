package es.pablolopez.InventoryJetPack.layout.sector;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.model.Sector;

public class SectorManageView extends Fragment implements SectorManageContract.View{

    public static final String TAG = "SectorManageView";

    private TextInputLayout tilName, tilShort, tilDesc;
    private TextInputEditText tiledName, tiledShort, tiledDesc;
    private Spinner spinner;
    private FloatingActionButton fabSave;

    private OnSectorManageListener activityListener;
    private SectorManageContract.Presenter presenter;

    private ArrayAdapter<Dependency> arrayAdapter;
    private Dependency dependency;

    public SectorManageView() {
    }

    public static SectorManageView newInstance(Bundle b) {
        SectorManageView fragment = new SectorManageView();
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
        presenter.loadSpinner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sector_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tilName = view.findViewById(R.id.tilSectorManageName);
        tilShort = view.findViewById(R.id.tilSectorManageShort);
        tilDesc = view.findViewById(R.id.tilSectorManageDescription);
        tiledName = view.findViewById(R.id.tiledSectorManageName);
        tiledShort = view.findViewById(R.id.tiledSectorManageShort);
        tiledDesc = view.findViewById(R.id.tiledSectorManageDescription);

        spinner = view.findViewById(R.id.spinnerSectorManageInventory);
        fabSave = view.findViewById(R.id.fabSectorManageSave);
        fabSave.setOnClickListener(v -> {
            if (presenter.validate(getSector())) {
                if (getArguments() != null){
                    presenter.editSector(getSector());
                }else {
                    presenter.addSector(getSector());
                }
            }
        });

        if (getArguments() != null){
            Sector sector = getArguments().getParcelable("sector");
            dependency = sector.getDependencia();
            tilShort.setEnabled(false);
            tiledName.setText(sector.getName());
            tiledShort.setText(sector.getShortname());
            tiledDesc.setText(sector.getDescription());
            spinner.setSelection(presenter.getPositionDependency(dependency),true);
        }

        arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSectorManageListener) {
            activityListener = (OnSectorManageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSectorManageListener");
        }
    }

    private Sector getSector(){
        Sector sector = new Sector();
        sector.setName(tiledName.getText().toString());
        sector.setShortname(tiledShort.getText().toString());
        sector.setDescription(tiledDesc.getText().toString());
        sector.setDependencia((Dependency) spinner.getSelectedItem());
        return sector;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    @Override
    public void onSuccessLoadSpinner(List<Dependency> dependencies) {
        arrayAdapter.clear();
        arrayAdapter.addAll(dependencies);
        arrayAdapter.notifyDataSetChanged();
        if (getArguments()!= null){
            spinner.setSelection(presenter.getPositionDependency(dependency),true);
        }
    }

    @Override
    public void onErrorLoadSpinner(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorValidate(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNameEmptyError(String s) {
        tilName.setError(s);
    }

    @Override
    public void clearNameError() {
        tilName.setError(null);
    }

    @Override
    public void showShortNameEmptyError(String s) {
        tilShort.setError(s);
    }

    @Override
    public void clearShortnameError() {
        tilShort.setError(null);
    }

    @Override
    public void showDescriptionEmptyError(String s) {
        tilDesc.setError(s);
    }

    @Override
    public void clearDescriptionError() {
        tilDesc.setError(null);
    }

    @Override
    public void setPresenter(SectorManageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {
        activityListener.onSave();
    }

    public interface OnSectorManageListener {
        void onSave();
    }
}
