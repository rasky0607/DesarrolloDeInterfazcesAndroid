package com.pablolopez.intentservicedownload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG="intentservicedownload";
    private Button btInit;
    private static final  int REQUEST_CODE_EXTERNAL_STORAGE=546;

    //Este manejador se utiliza para recoger el mensaje del servidor
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            String path=(String) msg.obj;
            if(msg.arg1==RESULT_OK)
                Toast.makeText(MainActivity.this, "Descargado"+path, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Descargado"+path, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btInit=findViewById(R.id.btInit);

        btInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dowloadFile();
            }

        });

        //1. comprobar si se tiene permisos para escribir en la SDK
        if(checkPermison())
        {
            if(!DirectoryHelper.isExternalStorageAvailable() ||DirectoryHelper.isExternalStoregeReadOnly()) {
                Toast.makeText(this, "Nose puede escribir en la SD", Toast.LENGTH_SHORT).show();
                btInit.setEnabled(false);
            }
        }else//2. Comprobar que la SDK no sea de solo lectura.
            requestPermissions();

    }

    //Solicita los permisos
    private void requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            Toast.makeText(this,"Escribir en la SD es necesario para poder descargar la imagen",Toast.LENGTH_SHORT).show();
        else
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_EXTERNAL_STORAGE);

    }

    //Comprueba que  se tienen los permisos adecuados
    private boolean checkPermison() {
        if(PackageManager.PERMISSION_GRANTED== ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            return  true;

        return false;

    }

    private void dowloadFile() {
        if(checkPermison()) {
            //3. Creo el directorio de la descarga del fichero
            DirectoryHelper.createDirectory(this);
            //4. Creo el objeto Messenger para que el srvicio se comunique con la Activity
            Intent intent = new Intent(MainActivity.this,IntentServicerDownload.class);
            Messenger messager=new Messenger(handler);
            intent.putExtra("MESSAGER",messager);
            intent.putExtra("urlpath","https://www.w3.org/TR/png/iso_8859-1.txt");
            //5. Se inicia el servicio
            startService(intent);
        }else
            requestPermissions();//volvemos a pedir los permisos


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CODE_EXTERNAL_STORAGE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    Log.d("AQUI","Permiso aceptado");
                else
                    Log.d("AQUI","Permiso Denegador");
                break;


        }

    }
}
