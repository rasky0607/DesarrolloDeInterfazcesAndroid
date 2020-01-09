package es.adrianmmudarra.socialspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.adrianmmudarra.socialspinner.adapter.SocialAdapter;
import es.adrianmmudarra.socialspinner.model.Social;
import es.adrianmmudarra.socialspinner.model.SocialComparator;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList list;

    private SocialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialeze();
    }

    private void initialeze() {
        spinner = findViewById(R.id.spinner);
        list = new ArrayList<Social>(){{add(new Social(R.drawable.ic_twitter,"Twitter",1000,200));add(new Social(R.drawable.ic_instagram,"Instagram",500,100));}};
        Collections.sort(list,new SocialComparator());
        adapter = new SocialAdapter(this, R.layout.social_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
