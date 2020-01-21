package es.pablolopez.InventoryJetPack.layout.dependency;

import android.os.AsyncTask;

import java.util.List;

import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.data.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {

    private DependencyListContract.View view;

    public DependencyListPresenter(DependencyListContract.View view) {
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
        new AsyncTask<Void,Void, List<Dependency>>(){

            @Override
            protected List<Dependency> doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return DependencyRepository.getInstance().getDependencies();
            }

            @Override
            protected void onPreExecute() {
                view.showLoadingProgress();
            }

            @Override
            protected void onPostExecute(List<Dependency> dependencies) {
                super.onPostExecute(dependencies);
                view.hideLoading();
                if (!dependencies.isEmpty()){
                    view.showData(dependencies);
                }else {
                    view.showNoDependency();
                }
            }


        }.execute();

    }

    @Override
    public void restore(Dependency dependency) {
        DependencyRepository.getInstance().add(dependency);
        view.showData(DependencyRepository.getInstance().getDependencies());
    }
}
