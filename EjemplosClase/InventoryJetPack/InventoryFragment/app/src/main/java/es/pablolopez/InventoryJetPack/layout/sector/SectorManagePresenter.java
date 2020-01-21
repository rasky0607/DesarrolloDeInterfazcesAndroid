package es.pablolopez.InventoryJetPack.layout.sector;

import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.model.Sector;
import es.pablolopez.InventoryJetPack.data.repository.DependencyRepository;
import es.pablolopez.InventoryJetPack.data.repository.SectorRepository;

public class SectorManagePresenter implements SectorManageContract.Presenter {

    private SectorManageContract.View view;

    public SectorManagePresenter(SectorManageContract.View view) {
        this.view = view;
    }

    @Override
    public void loadSpinner() {
        if(DependencyRepository.getInstance().getDependencies().isEmpty()){
            view.onErrorLoadSpinner("No hay dependencias");
        }else {
            view.onSuccessLoadSpinner(DependencyRepository.getInstance().getDependencies());
        }
    }

    @Override
    public boolean validate(Sector sector) {
        return checkName(sector.getName()) & checkShortName(sector.getShortname()) & checkDescription(sector.getDescription());
    }

    @Override
    public void addSector(Sector sector) {
        if (SectorRepository.getInstance().addSector(sector)){
            view.onSuccess(null);
        }
        else {
            view.showError("No se ha podido añadir el sector");
        }
    }

    @Override
    public void editSector(Sector sector) {
        if (SectorRepository.getInstance().edit(sector)){
            view.onSuccess(null);
        }
        else {
            view.showError("No se ha podido editar el sector");
        }
    }

    @Override
    public int getPositionDependency(Dependency dependency) {
        return DependencyRepository.getInstance().getPositionDependency(dependency);

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
