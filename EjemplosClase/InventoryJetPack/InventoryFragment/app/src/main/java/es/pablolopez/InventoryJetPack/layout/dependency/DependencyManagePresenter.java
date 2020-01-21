package es.pablolopez.InventoryJetPack.layout.dependency;

import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.repository.DependencyRepository;

public class DependencyManagePresenter implements DependencyManageContract.Presenter {

    DependencyManageContract.View view;

    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
    }

    @Override
    public void edit(Dependency dependency) {
        if (DependencyRepository.getInstance().edit(dependency))
            view.onSuccess("Dependencia Editada: "+dependency.getShortname());
        else
            view.showError("No se ha podido editar la dependencia");
    }

    @Override
    public void add(Dependency dependency) {
        if (DependencyRepository.getInstance().add(dependency)){
            view.onSuccess("Dependencia Añadida: "+ dependency.getShortname());
        }else {
            view.showError("No se puede añadir la dependencia.");
        }
    }

    @Override
    public void delete(Dependency dependency) {
        DependencyRepository.getInstance().delete(dependency);
    }

    @Override
    public boolean isValidDependency(String name, String shortName, String description) {

        return checkName(name) & checkShortName(shortName) & checkDescription(description);

    }

    private boolean checkName(String name){
        if (name.isEmpty()){
            view.showNameEmptyError("El campo nombre no puede estar vacio");
            return false;
        }
        view.clearNameError();
        return true;
    }

    private  boolean checkShortName(String shortname){
        if (shortname.isEmpty()){
            view.showShortNameEmptyError("El campo nombre corto no puede estar vacio");
            return false;
        }
        view.clearShortnameError();
        return true;
    }

    private  boolean checkDescription(String desciption){
        if (desciption.isEmpty()){
            view.showDescriptionEmptyError("El campo descripcion no puede estar vacio");
            return false;
        }
        view.clearDescriptionError();
        return true;
    }
}
