package com.example.inventory.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.inventory.R;
import com.example.inventory.iu.dependency.DependencyListActivity;

public class DashBoardActivity extends AppCompatActivity {

    //Botones para enlazar con la activity del interfaz
    private ImageButton btninventario;
    private ImageButton btndependencias;
    private ImageButton btnseccion;
    private ImageButton btnproducto;
    private ImageButton btnperfil;
    private ImageButton btnajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        //Asociacion de componentes de la activyti a los objetos de la clase.
        btninventario = findViewById(R.id.btninventario);
        btndependencias = findViewById(R.id.btndependencias);
        btnseccion = findViewById(R.id.btnseccion);
        btnproducto = findViewById(R.id.btnproducto);
        btnperfil = findViewById(R.id.btnperfil);
        btnajustes = findViewById(R.id.btnajustes);

        inicialice();


    }

    private void inicialice() {
        btndependencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDependency();
            }
        });
    }

    /*Metodo que inicializa una actividad*/
    private  void startDependency(){
        Intent intent = new Intent(this, DependencyListActivity.class);
        startActivity(intent);
        finish();

    }
}
