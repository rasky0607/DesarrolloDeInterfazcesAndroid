package com.example.inventoryfragment.iu.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.inventoryfragment.R;
import com.example.inventoryfragment.data.model.Dependency;


public class DependencyActivity extends AppCompatActivity implements DependencyListFragment.onManageDependencyListener {

    //Fragment que controlará la Activity
  private  DependencyListFragment dependencyListFragment;

  private DependencyManageFragment dependencyManageFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency);
        showListFragment();


    }

    /**
     * Este método muestra el Fragmente DependencyListFragment
     */
    private void showListFragment() {

        //Iniciamos el fragmentManager y añadimos el fragment de DependencyListFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyListFragment = (DependencyListFragment) fragmentManager.findFragmentByTag(DependencyListFragment.TAG);
        if (dependencyListFragment == null) {
            dependencyListFragment = (DependencyListFragment) DependencyListFragment.newInstance(null);
            fragmentManager.beginTransaction().add(android.R.id.content, dependencyListFragment, DependencyListFragment.TAG).commit();
        }

    }

    /**
     * Este método muestra el Fragment DependencyManageFragment
     */
    @Override
    public void onManageDependency(Dependency dependency) {
        showDependencyManagerFragment(dependency);
    }




    /**
     * Este método muestra el Fragmente DependencyManageFragment
     */
    private void showDependencyManagerFragment(Dependency dependency) {

        Bundle bundle=null;

        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyManageFragment = (DependencyManageFragment) fragmentManager.findFragmentByTag(DependencyManageFragment.TAG);
        if (dependencyManageFragment == null) {

            if(dependency !=null)
            {
                Log.d("Paso 2","DependencyActivity "+ dependency.getName());
                bundle= new Bundle();
                bundle.putParcelable(Dependency.TAG,dependency);
            }

            dependencyManageFragment = (DependencyManageFragment) DependencyManageFragment.newInstance(bundle);

        }

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, dependencyManageFragment, DependencyManageFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
