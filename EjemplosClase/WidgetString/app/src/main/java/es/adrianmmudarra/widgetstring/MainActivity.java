package es.adrianmmudarra.widgetstring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton rb_Empresa, rb_Particular;
    AutoCompleteTextView autoCompleteTextView;
    View empresa, particular;
    String[] month = {"Enero","Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        rb_Empresa = findViewById(R.id.rb_Empresa);
        rb_Particular = findViewById(R.id.rbParticular);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        empresa = findViewById(R.id.include_emp);
        particular = findViewById(R.id.include_part);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, month);
        autoCompleteTextView.setAdapter(adapter);

        rb_Empresa.setOnClickListener(this);
        rb_Particular.setOnClickListener(this);
        empresa.setVisibility(View.GONE);
        particular.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (rb_Empresa.isChecked()){
            empresa.setVisibility(View.VISIBLE);
            particular.setVisibility(View.GONE);
        }else{
            empresa.setVisibility(View.GONE);
            particular.setVisibility(View.VISIBLE);
        }
    }
}
