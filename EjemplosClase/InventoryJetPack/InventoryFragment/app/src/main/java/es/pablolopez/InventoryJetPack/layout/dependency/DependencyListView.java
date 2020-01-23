package es.pablolopez.InventoryJetPack.layout.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;
import java.util.List;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.adapter.DependencyAdapter;
import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.layout.base.BaseDialogFragment;

public class DependencyListView extends Fragment implements DependencyListContract.View, BaseDialogFragment.OnBaseDialogListener{

    private RecyclerView recyclerDependency;
    private DependencyAdapter adapter;
    private FloatingActionButton fabAdd;
    private ImageView imageView;

    private onManageDependencyListener listenerActivity;
    private DependencyListContract.Presenter listenerPresenter;
    private DependencyAdapter.onManageDependencyListener listenerAdapter;
    private BaseDialogFragment baseDialogFragment;

    private View loadingProgress;

    public static String TAG = "DependencyListView";
    private Dependency deleted;

    public static Fragment newInstanced(Bundle bundle){
        DependencyListView fragment = new DependencyListView();
        if (bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        listenerPresenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerActivity = (onManageDependencyListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dependency_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerDependency = view.findViewById(R.id.recyclerDependency);
        fabAdd = view.findViewById(R.id.fabDependencyListAdd);
        fabAdd.setOnClickListener(v -> listenerActivity.onManageDependency(null));
        imageView = view.findViewById(R.id.ivDependencyList);
        loadingProgress = view.findViewById(R.id.fragmentLoadingDependencyList);

        listenerAdapter = new DependencyAdapter.onManageDependencyListener() {
            @Override
            public void onEditDependencyListener(Dependency dependency) {
                listenerActivity.onManageDependency(dependency);
            }

            @Override
            public void onDeleteDependencyListener(Dependency d) {
                showDialogDelete(d);
            }
        };

        adapter = new DependencyAdapter();
        adapter.setOnManageDependencyListener(listenerAdapter);

        recyclerDependency.setAdapter(adapter);
        recyclerDependency.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
    }

    private void showDialogDelete(Dependency d) {
        Bundle b = new Bundle();
        b.putString(BaseDialogFragment.TITLE,"Borrar Dependencia");
        b.putString(BaseDialogFragment.MESSAGE,"¿Estas seguro de que quieres borrar esta dependencia? -> "+d.getShortname());
        baseDialogFragment = BaseDialogFragment.getInstance(b);
        baseDialogFragment.setTargetFragment(DependencyListView.this,300);
        baseDialogFragment.show(getFragmentManager(),BaseDialogFragment.TAG);
        deleted = d;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dependency_list,menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Listado de dependencias");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingProgress() {
        loadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showNoDependency() {
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<Dependency> dependencies) {
        Log.d("AQUI"," elementos:"+dependencies.get(0));
        adapter.clear();
        adapter.addAll(dependencies);
        adapter.notifyDataSetChanged();
        imageView.setVisibility(View.GONE);
        deleted = null;
    }

    @Override
    public void onSuccessDelete() {
        adapter.delete(deleted);
        adapter.notifyDataSetChanged();
        Snackbar.make(getView(),"Dependencia Eliminada",Snackbar.LENGTH_SHORT).setAction("ANULAR", v -> {
            listenerPresenter.restore(deleted);
        }).show();
    }

    @Override
    public void setPresenter(DependencyListContract.Presenter presenter) {
        this.listenerPresenter = presenter;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(),error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {
    }

    @Override
    public void onAccept() {
        listenerPresenter.delete(deleted);
    }

    @Override
    public void onCancel() {
        baseDialogFragment.dismiss();
    }

    interface onManageDependencyListener {
        void onManageDependency(Dependency dependency);
    }
}
