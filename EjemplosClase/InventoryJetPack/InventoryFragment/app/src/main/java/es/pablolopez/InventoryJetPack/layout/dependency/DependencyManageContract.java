package es.pablolopez.InventoryJetPack.layout.dependency;

import es.pablolopez.InventoryJetPack.layout.base.BaseView;
import es.pablolopez.InventoryJetPack.data.model.Dependency;

public interface DependencyManageContract {
    interface View extends BaseView<Presenter> {
        void showNameEmptyError(String error);
        void showShortNameEmptyError(String error);
        void showDescriptionEmptyError(String error);

        void clearNameError();
        void clearShortnameError();
        void clearDescriptionError();
    }

    interface Presenter{
        void edit(Dependency dependency);

        void add(Dependency dependency);

        void delete(Dependency dependency);

        boolean isValidDependency(String name, String shortName, String description);
    }
}
