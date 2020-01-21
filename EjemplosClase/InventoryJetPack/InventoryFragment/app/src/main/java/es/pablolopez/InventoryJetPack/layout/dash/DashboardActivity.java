package es.pablolopez.InventoryJetPack.layout.dash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.layout.dependency.DependencyActivity;
import es.pablolopez.InventoryJetPack.layout.sector.SectorActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnDependency, btnSectors, btnInventory, btnProducts, btnProfile, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
    }

    private void initialize() {
        btnDependency = findViewById(R.id.btnDashDependency);
        btnDependency.setOnClickListener(this);
        btnSectors = findViewById(R.id.btnDashSectors);
        btnSectors.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDashDependency:
                startActivity(new Intent(DashboardActivity.this, DependencyActivity.class));
                break;
            case R.id.btnDashSectors:
                startActivity(new Intent(DashboardActivity.this, SectorActivity.class));
                break;
                default:
                    break;
        }
    }
}
