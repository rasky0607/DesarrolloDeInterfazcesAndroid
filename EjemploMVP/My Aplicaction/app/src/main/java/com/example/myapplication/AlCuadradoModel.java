package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/*Esta clase esta solo en contacto con el presenter*/
public class AlCuadradoModel implements AlCuadrado.Model {

    /*Declaracion de interfaz del Presenter para que el Model pueda realiza la comunicacion con este
     accediendo  a través de sus interface que implementa la clase AlCuadradoPresenter*/
    private  AlCuadrado.Presenter presenter;

    private double resultado;

    //Cosntrucor de la clase que recibe un presenter, ya que es este siempre el que lo va llamar e iniciar
    public  AlCuadradoModel (AlCuadrado.Presenter presenter)
    {
        this.presenter = presenter;
    }


/*Metodo implementado por la interfaz del Model que calcula el cuadrado de un numero recibido*/
    @Override
    public void alCuadrado(String data) {
        if(data.equals(""))
            presenter.showError("Error no has introducido un valor.");
        else {            //Toast.makeText(Context, "NO has introducido nada", Toast.LENGTH_SHORT).show();
            //Convertimos String a Double y hacemos el cuadrado
            resultado = Double.valueOf(data) * Double.valueOf(data);
            //Convertimos el resultado a String y lo enviamos al Presenter
            presenter.showReulst(String.valueOf(resultado));
        }
    }
}
