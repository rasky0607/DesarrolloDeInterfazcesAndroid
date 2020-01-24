package es.pablolopez.InventoryJetPack.layout.dependency;

import java.util.List;

import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.repository.DependencyRepository;

public class DependencyListPresenterAsynkTask implements DependencyListContract.Presenter {

    private DependencyListContract.View view;

    public DependencyListPresenterAsynkTask(DependencyListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Dependency dependency) {
        if (DependencyRepository.getInstance().delete(dependency)){
            if (!DependencyRepository.getInstance().getDependencies().isEmpty()){
                view.onSuccessDelete();
            }
            else{
                view.onSuccessDelete();
                view.showNoDependency();
            }
        }
        else {
            view.showError("No se ha podido eliminar la dependencia");
        }
    }

    @Override
    public void load() {
        view.showLoadingProgress();
        List<Dependency> dependencies = DependencyRepository.getInstance().getDependencies();
        if (dependencies.isEmpty()){
            view.showNoDependency();
        }else{
            view.showData(dependencies);
        }
        view.hideLoading();

    }

    @Override
    public void restore(Dependency dependency) {
        //DependencyRepository.getInstance().add(dependency);
        view.showData(DependencyRepository.getInstance().getDependencies());
    }
}
