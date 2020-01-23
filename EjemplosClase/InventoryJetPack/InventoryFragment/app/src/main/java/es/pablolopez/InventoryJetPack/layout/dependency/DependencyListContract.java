package es.pablolopez.InventoryJetPack.layout.dependency;

import java.util.Collection;
import java.util.List;

import es.pablolopez.InventoryJetPack.data.model.Dependency;
import es.pablolopez.InventoryJetPack.layout.base.BaseView;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void showLoadingProgress();
        void hideLoading();
        void showNoDependency();
        void showData(List<Dependency> dependencies);
        void onSuccessDelete();

    }

    interface Presenter{
        void delete(Dependency dependency);
        void load();
        void restore(Dependency dependency);
    }
}
