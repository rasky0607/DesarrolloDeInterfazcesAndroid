package es.pablolopez.InventoryJetPack.layout.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.layout.login.LoginActivity;

public class SplashLayout extends AppCompatActivity {

    private TextView tvSplash;
    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        goToLogin();
    }

    private void goToLogin() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(SplashLayout.this, LoginActivity.class);
                SplashLayout.this.startActivity(mainIntent);
                SplashLayout.this.finish();
            }
        }, WAIT_TIME);


    }

    private void initialize() {
        tvSplash = findViewById(R.id.tvSpashInventory);
        tvSplash.setTypeface(Typeface.createFromAsset(getAssets(),"font/Karla-Regular.ttf"));
    }
}
