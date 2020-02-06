package com.pabloLopez.servicedownloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btStart, btStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceDownload.class);
                //pasamos la URL de descarga
                intent.putExtra("urlpath", "https://image01.oneplus.net/shop/201901/08/492/28fcfe0730f3216e6da52a70eb0458aa.png");
                startService(intent);

            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, ServiceDownload.class));
            }
        });
    }
}
