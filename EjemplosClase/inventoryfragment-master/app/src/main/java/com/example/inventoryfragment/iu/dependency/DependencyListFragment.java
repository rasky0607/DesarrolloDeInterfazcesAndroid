package com.example.inventoryfragment.iu.dependency;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryfragment.data.model.Dependency;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.inventoryfragment.R;
import com.example.inventoryfragment.adapter.DependencyAdapter;



public class DependencyListFragment extends  Fragment {



    public static final String TAG = "DependencyListFragment";
    private RecyclerView rvDependency;
    private DependencyAdapter adapter;
    private final int SPAN_COUNT = 3;
    private FloatingActionButton fab;

    private onManageDependencyListener listener;
    private DependencyAdapter.onManageDependencyListener listenerAdapter;
    /**
     * Interfaz que comunica al listener que se ha pulsado el botón Add
     */
    interface onManageDependencyListener {
        void onManageDependency(Dependency dependency);
    }

    public static Fragment newInstance(Bundle bundle) {
        DependencyListFragment fragment = new DependencyListFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onManageDependencyListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implementat onManageDependencyListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dependency_list, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDependency = view.findViewById(R.id.rvDependency);

        listenerAdapter = new DependencyAdapter.onManageDependencyListener() {
            @Override
            public void onEditDependencyListener(Dependency dependency) {
                listener.onManageDependency(dependency);
                Log.d("Paso 3","DependencyListFragment "+dependency.getName());
            }

            @Override
            public void onDeleteDependencyListener(Dependency dependency) {
                Toast.makeText(getContext(),"Delete "+dependency.getName(),Toast.LENGTH_SHORT).show();
            }
        };

        initRvDependency();

        fab=view.findViewById(R.id.fab);
        initFab();


    }

    /**
     * Método que inicializa el click en el FloatingActionButton
     */
    private void initFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onManageDependency(null);
            }
        });
    }

//TODO AQUI DIFIERE en la parte final de su metodo onViewCreated
    /*
     Método que inicializa el RecyclerView que muestra todas las dependencias
     */
    private void initRvDependency() {

        //1. Crear adapter
        adapter = new DependencyAdapter();

        //añadido
        adapter.setOnManageDependencyListener(listenerAdapter);

        //2. Crear diseño del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        rvDependency.setLayoutManager(linearLayoutManager);

        //3. Vincular la vista al modelo (RecyclerView al Adapter)
        rvDependency.setAdapter(adapter);
    }




}
