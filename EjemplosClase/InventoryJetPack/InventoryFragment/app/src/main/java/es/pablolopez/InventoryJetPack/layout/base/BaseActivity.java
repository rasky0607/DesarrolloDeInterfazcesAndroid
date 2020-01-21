package es.pablolopez.InventoryJetPack.layout.base;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import es.pablolopez.InventoryJetPack.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dependency,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnMenuSearch:
                Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnMenuAboutMe:
                Toast.makeText(this, "About Me", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnMenuHelp:
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnMenuSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
}
