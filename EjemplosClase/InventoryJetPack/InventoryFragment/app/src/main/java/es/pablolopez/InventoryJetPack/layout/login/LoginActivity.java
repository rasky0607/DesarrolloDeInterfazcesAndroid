package es.pablolopez.InventoryJetPack.layout.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.pablolopez.InventoryJetPack.R;
import es.pablolopez.InventoryJetPack.layout.dash.DashboardActivity;
import es.pablolopez.InventoryJetPack.layout.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize() {
        btnLogin = findViewById(R.id.btnLoginLogin);
        btnLogin.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnLoginRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginLogin:
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                break;
            case R.id.btnLoginRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
}
