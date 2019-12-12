package com.example.inventory.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inventory.R;

public class LoginActivity extends AppCompatActivity {

    private Button btSignIn;
    private Button btSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btSignIn =findViewById(R.id.btSignIn);


        //Arrancamosla vista del DashBoard
       btSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Validar();

           }
       });

        btSignUp=findViewById(R.id.btSignUp);

        btSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent =new Intent(LoginActivity.this, SingUpActivity.class);
              startActivity((intent));
          }
      });


    }

    /*Metodo que comprueba la validez de estos dos campos Usiario y Password*/
    private void Validar() {
        Intent intent =new Intent(LoginActivity.this,DashBoardActivity.class);
        startActivity((intent));
        finish();
    }

}
