package com.example.fragmentlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvxtexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos componente grafico con objeto del codigo
        tvxtexto = findViewById(R.id.tvxtexto);
        //Recogemos la fuente
        Typeface fuente = Typeface.createFromAsset(getAssets(),"Madhen.ttf");
        //aplicamos la fuente al texto del textview "tvxtexto"
        tvxtexto.setTypeface(fuente);
    }
    /**
    * Metodo que cambia la visibilidad del texto
     * @param  view
    * */
    public void changeVisibility(View view)
    {
        //Hacemos uso del If ternario//El valor de la visibilidad es un numero entero que va desde 0 a 4 GONE= es ademas de i nvisible que no ocupa espacio
        tvxtexto.setVisibility((View.VISIBLE==tvxtexto.getVisibility())?View.INVISIBLE:View.VISIBLE);

        //Es lo mismo que esto:
        /*  if (tvxtexto.getVisibility() == View.VISIBLE) {
            tvxtexto.setVisibility(view.INVISIBLE);
        } else {
            tvxtexto.setVisibility(view.VISIBLE);
        }
*/

    }
}
