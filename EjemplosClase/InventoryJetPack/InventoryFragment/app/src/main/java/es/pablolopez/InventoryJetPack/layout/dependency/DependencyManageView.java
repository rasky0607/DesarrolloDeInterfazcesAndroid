package es.pablolopez.InventoryJetPack.layout.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import es.pablolopez.InventoryJetPack.InventoryApplication;
import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.data.model.Dependency;

/** class dependency Create and Edit*/
public class DependencyManageView extends Fragment implements DependencyManageContract.View{

    private onSaveListener listener;
    private DependencyManageContract.Presenter presenter;

    private TextInputEditText tiledShortName, tiledName, tiledDescription;
    private TextInputLayout tilShortName, tilName, tilDescription;
    private Spinner spinner;
    private FloatingActionButton fabSave;
    //Id o flag para notificacion que puede lanzar esta vista
    private  final int ID_NOTIFICACION=25;

    public DependencyManageView() {
        // Required empty public constructor
    }

    public static String TAG = "DependencyManageView";

    public static Fragment newInstance(Bundle bundle) {
        DependencyManageView fragment = new DependencyManageView();
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
        tiledShortName = view.findViewById(R.id.tiledDependencyManageShort);
        tiledName = view.findViewById(R.id.tiledDependencyManageName);
        tiledDescription = view.findViewById(R.id.tiledDependencyManageDescription);
        tilName = view.findViewById(R.id.tilDependencyManageName);
        tilShortName = view.findViewById(R.id.tilDependencyManageShort);
        tilDescription = view.findViewById(R.id.tilDependencyManageDescription);
        spinner = view.findViewById(R.id.spinnerDependencyManageInventory);
        if (getArguments()!= null){
            tiledShortName.setEnabled(false);
        }
        fabSave = view.findViewById(R.id.fabDependencyManageSave);
        fabSave.setOnClickListener(v -> {
            if (presenter.isValidDependency(tiledName.getText().toString(),tiledShortName.getText().toString(),tiledDescription.getText().toString())){
                Dependency d = getDependency();
                if (getArguments() != null){
                    presenter.edit(d);
                }else {
                    presenter.add(d);
                }
            }

        });


        if (getArguments() != null){
            Dependency d  = getArguments().getParcelable(Dependency.TAG);
            tiledShortName.setText(d.getShortname());
            tiledName.setText(d.getName());
            tiledDescription.setText(d.getDescription());
            switch (d.getInventory()){
                case "2018":
                    spinner.setSelection(0,true);
                    break;
                case "2019":
                    spinner.setSelection(1,true);
                    break;
                case "2020":
                    spinner.setSelection(2,true);
                    break;
            }
        }
    }

    private Dependency getDependency() {
        Dependency dependency = new Dependency();
        dependency.setName(tiledName.getText().toString());
        dependency.setShortname(tiledShortName.getText().toString());
        dependency.setDescription(tiledDescription.getText().toString());
        dependency.setInventory(spinner.getSelectedItem().toString());
        return dependency;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSaveListener) {
            listener = (onSaveListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onSaveListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    @Override
    public void setPresenter(DependencyManageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(),error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onSuccess(String message,Dependency dependency) {
        listener.onSaveListener(message);

           /*Un pendingIntent tiene un objeto Intent en su interior
        que define lo que se quiere ejecutar cuando se pulse la notificacion*/
        Intent intent =new Intent(getActivity(),DependencyActivity.class);
        intent.putExtra("NOTIFICATION",true);
        intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
        Bundle bundle = new Bundle();
        bundle.putParcelable(Dependency.TAG,dependency);
        //Se crea el objeto pendinIntent
        PendingIntent pendingIntent =PendingIntent.getActivity(getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //Lanzamos la notificacion
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.inventory)
                .setContentText("mi notificacion")
                .setContentTitle("My notification")
                .setContentIntent(pendingIntent);

        /*Cuando se pulse sobre la notificacion, se debe abrir la  activity dependency "DependencyActivity
        * que de alguna manera determinara que debe iniciar el fragment DependencyManagerFragment*/

        NotificationManagerCompat notificationManagerCompat=  NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(ID_NOTIFICACION,builder.build());
        getActivity().onBackPressed();

        /*Cuando tenemos un fragment que es candiadato a crear mas de una notificacion, usamos BaseFragment*/




    }

    @Override
    public void showNameEmptyError(String error) {
        tilName.setError(error);
    }

    @Override
    public void showShortNameEmptyError(String error) {
        tilShortName.setError(error);
    }

    @Override
    public void showDescriptionEmptyError(String error) {
        tilDescription.setError(error);
    }

    @Override
    public void clearNameError() {
        tilName.setError(null);
    }

    @Override
    public void clearShortnameError() {
        tilShortName.setError(null);
    }

    @Override
    public void clearDescriptionError() {
        tilDescription.setError(null);
    }

    public interface onSaveListener {
        // TODO: Update argument type and name
        void onSaveListener(String message);
    }
}
