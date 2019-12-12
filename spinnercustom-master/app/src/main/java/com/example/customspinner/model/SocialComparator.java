package com.example.customspinner.model;

import java.util.Comparator;

/*Se utiliza un comparator para ordenar
 dos objetos mediante uno o varios criterios que no sea el orden natural
 */
public class SocialComparator implements Comparator<Social> {

    @Override
    public int compare(Social s1, Social s2) {
        if(s1.getTotaluser()<s2.getTotaluser())
            return -1;
        else if(s1.getTotaluser() == s2.getTotaluser()){//Si son iguales se compara por otro criterio

            //Lo encapsulamos en la clase Integer ya que esta clase nos aporta algunos  metodos que nos pueden ser utiles como compareTo()
            return  (Integer.compare(s1.getDollars(),s2.getDollars()));
        }else
            return  1;
    }
}
