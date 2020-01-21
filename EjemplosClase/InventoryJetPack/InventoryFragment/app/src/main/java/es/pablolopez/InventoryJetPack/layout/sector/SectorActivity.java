package es.pablolopez.InventoryJetPack.layout.sector;

import android.os.Bundle;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.data.model.Sector;
import es.pablolopez.InventoryJetPack.layout.base.BaseActivity;

public class SectorActivity extends BaseActivity implements SectorListView.OnSectorListViewListener, SectorManageView.OnSectorManageListener{

    private SectorListView sectorListView;
    private SectorManageView sectorManageView;

    private SectorListPresenter sectorListPresenter;
    private SectorManagePresenter sectorManagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector);
        initialice();
    }

    private void initialice() {
        sectorListView = (SectorListView) getSupportFragmentManager().findFragmentByTag(SectorListView.TAG);
        if (sectorListView == null){
            sectorListView = SectorListView.newInstance(null);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,sectorListView,SectorListView.TAG)
                    .commit();
        }

        sectorListPresenter = new SectorListPresenter(sectorListView);
        sectorListView.setPresenter(sectorListPresenter);
    }

    @Override
    public void onAddEditSector(Sector sector) {
        Bundle bundle = null;
        sectorManageView = (SectorManageView) getSupportFragmentManager().findFragmentByTag(SectorManageView.TAG);
        if (sectorManageView == null){
            setTitle("Añadir sector");
            if (sector !=null){
                setTitle("Modificar sector");
                bundle = new Bundle();
                bundle.putParcelable("sector",sector);
            }
            sectorManageView = SectorManageView.newInstance(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content,sectorManageView,SectorManageView.TAG)
                    .addToBackStack(null)
                    .commit();
        }
        sectorManagePresenter = new SectorManagePresenter(sectorManageView);
        sectorManageView.setPresenter(sectorManagePresenter);
    }

    @Override
    public void onSave() {
        onBackPressed();
    }
}
