package es.pablolopez.InventoryJetPack.layout.base;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showError(String error);
    void onSuccess(String message);
}
