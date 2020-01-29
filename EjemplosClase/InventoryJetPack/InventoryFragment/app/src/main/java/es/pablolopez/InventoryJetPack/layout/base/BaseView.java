package es.pablolopez.InventoryJetPack.layout.base;

import es.pablolopez.InventoryJetPack.data.model.Dependency;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showError(String error);
    void onSuccess(String message);

}
