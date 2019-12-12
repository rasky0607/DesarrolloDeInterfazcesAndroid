package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*Este ejemplo pretende explicar de forma resumida y lo más simplificada posible
* el uso del patron MVP.
* "El objetivo de este ejemplo es calcula el cuadrado d eu n numero introducido por le usuario"
* La mayor parte de la explicación de cada método implementado esta en la interfaz AlCuadrado*/

public class AlCuadradoView extends AppCompatActivity implements AlCuadrado.View {


    private TextView tvAlCuadrado;
    private EditText edAlCuadrado;
    private Button btnCalcular;

    private  AlCuadrado.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignación de componentes graficos con la clase
        tvAlCuadrado = findViewById(R.id.tvAlCuadrado);
        edAlCuadrado = findViewById(R.id.edAlCuadrado);
        btnCalcular = findViewById(R.id.btnCalcular);

        /*Inicializamos e presenter para poder establecer la comunicación entre este y la vista o View*/
        presenter=new AlcuadradoPresenter(this);

        //Evento Click del Boton calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular(v);
            }
        });


    }


    //Metodo para el evento click del boton Calcular
    public void calcular(View view) {
        presenter.alCuadrado(edAlCuadrado.getText().toString());
    }

    /*Metodo implementado por la i nterfaz, que muestra el resultado de el cuadrado de un numero*/
    @Override
    public void showResult(String result) {
        tvAlCuadrado.setText(result);
    }
    /*Metodo implementado por la i nterfaz,
    que muestra un mensaje de error cuando se intruce un string vacio*/
    @Override
    public void showError(String error) {

        tvAlCuadrado.setText(error);
    }
}
