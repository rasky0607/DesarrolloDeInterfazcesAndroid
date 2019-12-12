package com.example.inventoryfragment.iu.dependency;

import com.example.inventoryfragment.data.model.Dependency;

public class DependencyAddPresenter implements DependencyAddContract.Presenter{

    private DependencyAddContract.View view;


    public DependencyAddPresenter(DependencyAddContract.View view) {
        this.view=view;
    }

    /**
     * Método que añade una dependencia al Repositorio y realiza todas las comprobaciones
     * pertinentes
     * @param dependency
     */
    @Override
    public void add(Dependency dependency) {

        //Si todo es correcto
        view.onSuccess();
        //EOC
        view.showError("Mensaje de error");

    }

}
