package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyRepository {

    private List<Dependency> list;
    private  static  DependencyRepository repository;

    //Como inicializar en el ambito static una variable estatica
    /*Se inicializa en el siguiente bloque todas las propiedades estaticas de la clase
    * sin tener que realizarlo en un metodo.
    * Este bloque si inicia antes que el constructor y cualquier otro bloque (y evita comprobar si es null)*/
    static {
        repository=new DependencyRepository();
    }


    //Constructor Privado ya que solo exite una  instancia/objeto de Repository
    private  DependencyRepository(){
        list = new ArrayList<>();
        inicialice();
    }

    /*Este metodo es privado y siempre se inicializa la lista cuando se crea la instancia del repositorio en el bloque static*/
    private void inicialice() {
        list.add(new Dependency("Ciclo formativo Grado Superior","2FGS","Aula de informatica","/img/img1.png"));
        list.add(new Dependency("Ciclo formativo Grado Superior","1FGS","Aula de informatica","/img/img1.png"));
        list.add(new Dependency("2ºE.S.O","2ºA","Aula de informatica","/img/img1.png"));
        list.add(new Dependency("2ºE.S.O","2ºB","Aula de informatica","/img/img1.png"));
        list.add(new Dependency("3ºE.S.O","3ºA","Aula de informatica","/img/img1.png"));
        list.add(new Dependency("3ºE.S.O","3ºB","Aula de informatica","/img/img1.png"));
    }

    /*Este metodo devuelve u na lista de Dependency*/
    public  List<Dependency>getList(){
        return  list;

    }

    /*Este metodo devuelve la instancia del Repository que siempre se inicializa ene l bloque static*/
    public static DependencyRepository getInstance(){
        return  repository;
    }


}
