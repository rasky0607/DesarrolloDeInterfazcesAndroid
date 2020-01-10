package com.example.myapplication;

/*Esta tan en contacto tanto con la vista como  con el modelo*/
public class AlcuadradoPresenter implements AlCuadrado.Presenter {

    /*Declaracion de interfaz de la vista y del modelo para que el Presenter
    * pueda realiza la comunicacion entre estas dos accediendo a estas a través de sus interfaces*/
    private  AlCuadrado.View view;


    //Constructor de la clase
    public  AlcuadradoPresenter(AlCuadrado.View view){
        this.view=view;

    }






    /*El Presenter comunica el dato a calcular a AlcuadradoModel a través tambien de la interfaz que implementa "AlCuadrado.Model"  como en el metodo showReuslts(String result)*/
    @Override
    public void alCuadrado(String data) {

        if(data == null)
            view.showError("ERROR");

        //calculo

    }


}
