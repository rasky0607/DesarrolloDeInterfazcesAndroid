package com.example.inventory.iu.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inventory.R;
import com.example.inventory.adapter.DependencyAdapter;

public class DependencyListActivity extends AppCompatActivity {

    private RecyclerView rvDependency;
    private DependencyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency_list);

       //1Crear adapter
        adapter= new DependencyAdapter();

        //2. Crear diseño del RecyclerView
        LinearLayoutManager llManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        //Vincular la vista al modelo
        rvDependency=findViewById(R.id.rvDependency);

        //Vincular la vista al modelo (RecyclerView al Adapter
        rvDependency.setAdapter(adapter);
        rvDependency.setLayoutManager(llManager);
    }
}
