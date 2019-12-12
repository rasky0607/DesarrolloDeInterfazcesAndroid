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
        /*El Result que manda el resultado final calculado por el Model o Modelo(AlCuadradoModel) a la Vista o View(AlCuadradoView)
        * AlCuadradoModel -> AlCuadradoPresenter -> AlCuadradoView*/
        void showReulst(String result);
        /* El Presenter Recoge el dato a calcular de la vista para mandarselo a el modelo
         AlCuadradoView -> AlcuadradoPresenter -> AlCuadradoModel*/
        void alCuadrado(String data);
        /*El Presenter informa a la visata de que el Model no pudo realizar el calculo al producirse un error
        * AlCuadradoModel -> AlCuadradoPresenter -> AlCuadradoView*/
        void showError(String error);
    }

    interface  Model{
        /*El Modelo Calcula el cuadrado de el dato recibido y lo carga en el presenter para que se lo mande a la vista, es decir usara el showResult del Presenter
        * AlCuadradoModel -> AlCuadradoPresenter -> AlCuadradoView*/
        void alCuadrado(String data);
    }
}
