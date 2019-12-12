package com.example.myapplication;

/*Esta tan en contacto tanto con la vista como  con el modelo*/
public class AlcuadradoPresenter implements AlCuadrado.Presenter {

    /*Declaracion de interfaz de la vista y del modelo para que el Presenter
    * pueda realiza la comunicacion entre estas dos accediendo a estas a través de sus interfaces*/
    private  AlCuadrado.View view;
    private  AlCuadrado.Model model;

    //Constructor de la clase
    public  AlcuadradoPresenter(AlCuadrado.View view){
        this.view=view;
        model= new AlCuadradoModel(this);


    }

    @Override
    public void showReulst(String result) {
        if(view!=null)
        {
            //Llamamos al metodo que hay dentro de la vista, es dedcir que implementa la vista, pero a traves de la interfaz es decir  AlCuadrado.View
            view.showResult(result);
        }


    }

    /*El Presenter comunica el dato a calcular a AlcuadradoModel a través tambien de la interfaz que implementa "AlCuadrado.Model"  como en el metodo showReuslts(String result)*/
    @Override
    public void alCuadrado(String data) {

        if(view!=null)
        {
          model.alCuadrado(data);
        }
    }
/*El presente comunica el Error al calcula producido en el Modelo(AlCuadradoModel) a la vista(AlCuadradoView)*/
    @Override
    public void showError(String error) {
        if(view!=null)
            view.showError(error);
    }
}
