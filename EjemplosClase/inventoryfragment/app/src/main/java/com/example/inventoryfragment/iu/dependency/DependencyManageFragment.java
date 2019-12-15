package com.example.inventoryfragment.iu.dependency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.inventoryfragment.data.model.Dependency;
import com.example.inventoryfragment.R;
import com.example.inventoryfragment.iu.base.BaseView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DependencyManageFragment extends Fragment{

    public static final String TAG = "DependencyManageFragment";
    private TextInputEditText edShortName;
    private TextInputEditText edName;
    private TextInputEditText edDescription;
    private TextInputLayout tilShortName;
    private TextInputLayout tilName;
    private TextInputLayout tilDescription;
    private Spinner spInventory;

    private OnDependencyManageFragmentListener listener;

    public interface OnDependencyManageFragmentListener {
        void onFragmentInteraction(Uri uri);
    }

    //Constructor
    public DependencyManageFragment() {

    }


    public static Fragment newInstance(Bundle bundle) {
        DependencyManageFragment fragment = new DependencyManageFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setRetainInstance(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dependency_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Asociamos  componentes de layout con la clase
        edShortName = view.findViewById(R.id.edShortName);
        edName = view.findViewById(R.id.edName);
        edDescription = view.findViewById(R.id.edDescription);
        spInventory = view.findViewById(R.id.spInventory);

        if (getArguments() != null){

            Dependency d  = getArguments().getParcelable(Dependency.TAG);
            Log.d("paso 4","DependencyManagerFragment "+d.getName());
            edShortName.setText(d.getShortName());
            edName.setText(d.getName());
            edDescription.setText(d.getDescription());
            switch (d.getInventory()){
                case "2018":
                    spInventory.setSelection(0,true);
                    break;
                case "2019":
                    spInventory.setSelection(1,true);
                    break;
                case "2020":
                    spInventory.setSelection(2,true);
                    break;
            }
        }
    }

    //TODO PUEDE QUE ESTO NO
    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed() {
        if (listener != null) {
            listener.onFragmentInteraction();
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }




}
