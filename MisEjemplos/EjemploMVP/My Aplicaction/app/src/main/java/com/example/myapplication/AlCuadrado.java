package com.example.myapplication;

/*1º que creamos para el patron MVP:
* -Creamos la distintas interfaces que vamso a usar en la diferentes calses:
*   -View --> interfaz para la vista.
*   -Presenter --> Interfaz para el presenter que se comunicara con el modelo y la vista
*   -Model --> Interfaz para el modelo de datos que se comunicara con el presente*/

public interface AlCuadrado {

    interface  View{
        /*Muestra el dato de el resultado final en la vista*/
        void showResult(String result);
        /*Muestra el mensaje de error en la vista*/
        void showError(String error);
    }

    interface  Presenter{

        /* El Presenter Recoge el dato a calcular de la vista para mandarselo a el modelo
         AlCuadradoView -> AlcuadradoPresenter -> AlCuadradoModel*/
        void alCuadrado(String data);



    }

}
